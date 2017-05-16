package io.reactivesw.product.domain.service

import com.google.common.collect.Lists
import com.google.common.collect.Sets
import io.reactivesw.exception.ConflictException
import io.reactivesw.product.domain.model.*
import io.reactivesw.product.infrastructure.repository.ProductRepository
import spock.lang.Specification

import java.time.ZonedDateTime

/**
 * Test for SkuService.
 */
class SkuServiceTest extends Specification {
    ProductRepository productRepository = Mock()

    def skuService = new SkuService(productRepository: productRepository)

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

    def "Test1.1: validate sku name and throw ConflictException"() {
        given:
        List<Product> products = Lists.newArrayList(product)
        productRepository.findAll() >> products

        when:
        skuService.validateSkuName(sku)

        then:
        thrown(ConflictException)
    }

    def "Test1.2: sku name is not exist"() {
        given:
        List<Product> products = Lists.newArrayList(product)
        productRepository.findAll() >> products
        def skuName = "skuName"

        when:
        skuService.validateSkuName(skuName)

        then:
        true
    }
}
