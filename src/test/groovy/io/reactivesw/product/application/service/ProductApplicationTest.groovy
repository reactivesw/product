package io.reactivesw.product.application.service

import com.google.common.collect.Lists
import com.google.common.collect.Sets
import io.reactivesw.product.application.model.InventoryEntryView
import io.reactivesw.product.application.model.ProductTypeView
import io.reactivesw.product.domain.model.AssetDimensions
import io.reactivesw.product.domain.model.Image
import io.reactivesw.product.domain.model.LocalizedStringValue
import io.reactivesw.product.domain.model.MoneyValue
import io.reactivesw.product.domain.model.Price
import io.reactivesw.product.domain.model.Product
import io.reactivesw.product.domain.model.ProductCatalogData
import io.reactivesw.product.domain.model.ProductData
import io.reactivesw.product.domain.model.ProductVariant
import io.reactivesw.product.domain.service.ProductService
import spock.lang.Specification

import java.time.ZonedDateTime

/**
 * Created by Davis on 17/3/29.
 */
class ProductApplicationTest extends Specification {

    def productService = Mock(ProductService)
    def productRestClient = Mock(ProductRestClient)
    ProductApplication productApplication = new ProductApplication(productRestClient, productService)

    def productId = "product-111"
    def product = new Product()
    def sku = "product-sku-111"
    def categoryId = "category111"

    InventoryEntryView inventory = new InventoryEntryView()

    def setup() {
        Set<LocalizedStringValue> name = Sets.newHashSet()
        name.add(new LocalizedStringValue(language: "en", text: "product-name-111"))

        MoneyValue moneyValue = new MoneyValue()
        moneyValue.currencyCode = "USD"
        moneyValue.centAmount = Integer.valueOf(1000)
        Price price = new Price()
        price.setValue(moneyValue)

        Image image = new Image()
        image.setUrl("http://imageurl/")
        image.setDimensions(new AssetDimensions(width: 100, height: 200))

        ProductVariant masterVariant = new ProductVariant()
        masterVariant.sku = sku
        masterVariant.prices = Lists.newArrayList(price)
        masterVariant.images = Lists.newArrayList(image)

        ProductData currentData = new ProductData()
        currentData.categories = Lists.newArrayList(categoryId)
        currentData.name = name
        currentData.masterVariant = masterVariant

        ProductCatalogData masterData = new ProductCatalogData();
        masterData.current = currentData

        product.masterData = masterData
        product.id = productId
        product.productType = "productType111"
        product.createdAt = ZonedDateTime.now()
        product.lastModifiedAt = ZonedDateTime.now()

        inventory.id = "inventory-111"
        inventory.sku = sku
        inventory.availableQuantity = Integer.valueOf(100)
        inventory.quantityOnStock = Integer.valueOf(100)
    }

    def "Test 1: get cart product by id"() {
        given:
        productService.getProductById(_) >> product
        def variantId = 1

        when:
        def result = productApplication.getCartProductById(productId, variantId)

        then:
        result != null
        result.productId == product.id
    }

    def "Test 2.1: query CategoryProducts by category id"() {
        given:
        productService.queryProductByCategory(_) >> Lists.newArrayList(product)
        productRestClient.getInventoryBySkus(_) >> Lists.newArrayList(inventory)

        when:
        def result = productApplication.queryCategoryProducts(categoryId)

        then:
        result != null
    }

    def "Test 2.2: query CategoryProducts by category id and inventory is empty"() {
        given:
        productService.queryProductByCategory(_) >> Lists.newArrayList(product)
        productRestClient.getInventoryBySkus(_) >> Lists.newArrayList()

        when:
        def result = productApplication.queryCategoryProducts(categoryId)

        then:
        result != null
    }

    def "Test 2.3: query CategoryProducts by category id and inventory is null"() {
        given:
        productService.queryProductByCategory(_) >> Lists.newArrayList(product)
        productRestClient.getInventoryBySkus(_) >> null

        when:
        def result = productApplication.queryCategoryProducts(categoryId)

        then:
        result != null
    }

    def "Test 3.1 : get DetailProduct by sku"() {
        given:
        productService.getProductBySku(_) >> product
        productRestClient.getProductType(_) >> new ProductTypeView()
        productRestClient.getInventoryBySkus(_) >> Lists.newArrayList(inventory)

        when:
        def result = productApplication.getDetailProductBySku(sku)

        then:
        result != null
    }

    def "Test 3.2 : get DetailProduct by sku and inventory is null"() {
        given:
        productService.getProductBySku(_) >> product
        productRestClient.getProductType(_) >> new ProductTypeView()
        productRestClient.getInventoryBySkus(_) >> null

        when:
        def result = productApplication.getDetailProductBySku(sku)

        then:
        result != null
    }

    def "Test 3.3 : get DetailProduct by sku and inventory is empty"() {
        given:
        productService.getProductBySku(_) >> product
        productRestClient.getProductType(_) >> new ProductTypeView()
        productRestClient.getInventoryBySkus(_) >> Lists.newArrayList()

        when:
        def result = productApplication.getDetailProductBySku(sku)

        then:
        result != null
    }

    def "Test4.1: search product"() {
        given:
        productService.getAllProducts() >> Lists.newArrayList(product)
        def searchWords = "product-sku-11"

        when:
        def result = productApplication.searchProduct(searchWords)

        then:
        result != null
    }

    def "Test4.2: search product and products is null"() {
        given:
        productService.getAllProducts() >> null
        def searchWords = "product-sku-11"

        when:
        def result = productApplication.searchProduct(searchWords)

        then:
        result.isEmpty()
    }

    def "Test4.3: search product and products is empty"() {
        given:
        productService.getAllProducts() >> Lists.newArrayList()
        def searchWords = "product-sku-11"

        when:
        def result = productApplication.searchProduct(searchWords)

        then:
        result.isEmpty()
    }
}
