package io.reactivesw.product.infrastructure.validator

import com.google.common.collect.Lists
import io.reactivesw.exception.ConflictException
import io.reactivesw.product.domain.model.Product
import io.reactivesw.product.domain.model.ProductCatalogData
import io.reactivesw.product.domain.model.ProductData
import spock.lang.Specification

/**
 * SlugValidator Test class.
 */
class SlugValidatorTest extends Specification {
    SlugValidator validator = new SlugValidator()

    def existSlug = "exist slug"
    Product product = null

    def setup() {
        ProductData currentProduct = new ProductData(slug: existSlug)
        ProductData stagedProduct = new ProductData(slug: existSlug)
        ProductCatalogData masterData = new ProductCatalogData(current: currentProduct, staged: stagedProduct)
        product = new Product(masterData: masterData)
    }

    def "Test1: validate that product slug not exist"() {
        setup:
        def slug = "test slug"

        when:
        SlugValidator.validate(slug, Lists.newArrayList(product))

        then:
        true
    }

    def "Test2: validate that product slug, slug has exist and throw ConflictException"() {
        when:
        SlugValidator.validate(existSlug, Lists.newArrayList(product))

        then:
        thrown(ConflictException)
    }

    def "Test3: product list is null"() {
        when:
        SlugValidator.validate(existSlug, null)

        then:
        true
    }
}