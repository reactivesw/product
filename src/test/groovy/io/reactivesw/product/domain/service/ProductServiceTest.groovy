package io.reactivesw.product.domain.service

import com.google.common.collect.Lists
import com.google.common.collect.Sets
import io.reactivesw.exception.NotExistException
import io.reactivesw.product.domain.model.*
import io.reactivesw.product.infrastructure.repository.ProductRepository
import spock.lang.Specification

import java.time.ZonedDateTime

/**
 * Created by Davis on 17/3/29.
 */
class ProductServiceTest extends Specification {
    ProductRepository productRepository = Mock()

    def productService = new ProductService(productRepository)

    def product = new Product()
    def id = "product-111"
    def sku = "product-sku-111"
    def categoryId = "category111"

    def setup() {
        Set<LocalizedStringValue> name = Sets.newHashSet()
        name.add(new LocalizedStringValue(language: "en", text: "product-name-111"))

        ProductVariant masterVariant = new ProductVariant()
        masterVariant.sku = sku

        ProductData currentData = new ProductData()
        currentData.categories = Lists.newArrayList(categoryId)
        currentData.name = name
        currentData.masterVariant = masterVariant

        ProductCatalogData masterData = new ProductCatalogData();
        masterData.current = currentData

        product.masterData = masterData
        product.id = id
        product.productType = "productType111"
        product.createdAt = ZonedDateTime.now()
        product.lastModifiedAt = ZonedDateTime.now()
    }

    def "test 1.1: get product by category id"() {
        given:
        List<Product> products = Lists.newArrayList(product)
        productRepository.findAll() >> products

        when:
        def result = productService.queryProductByCategory(categoryId)

        then:
        result != null
        result.size() == products.size()
    }

    def "test 2.1: get product by sku"() {
        given:
        List<Product> products = Lists.newArrayList(product)
        productRepository.findAll() >> products

        when:
        def result = productService.getProductBySku(sku)

        then:
        result != null
        result.masterData.current.masterVariant.sku == sku
    }

    def "test 2.2: get product by sku and get null product, throw NotExistException"() {
        given:
        List<Product> products = Lists.newArrayList(product)
        productRepository.findAll() >> products
        def searchSku = "search-sku-111"

        when:
        def result = productService.getProductBySku(searchSku)

        then:
        thrown(NotExistException)
    }


    def "test 3.1: get Product by Id"() {
        given:
        productRepository.findOne(id) >> product

        when:
        def result = productService.getProductById(id)

        then:
        result != null
        result.id == id
    }

    def "test 3.2: get Product by Id and get null"() {
        given:
        productRepository.findOne(id) >> null

        when:
        def result = productService.getProductById(id)

        then:
        thrown(NotExistException)
    }
}
