package io.reactivesw.product.infrastructure.validator

import com.google.common.collect.Lists
import io.reactivesw.exception.ConflictException
import io.reactivesw.exception.ParametersException
import io.reactivesw.product.application.admin.model.ProductDraft
import io.reactivesw.product.application.admin.model.ProductVariantDraft
import io.reactivesw.product.domain.model.Product
import io.reactivesw.product.domain.model.ProductCatalogData
import io.reactivesw.product.domain.model.ProductData
import io.reactivesw.product.domain.model.ProductVariant
import io.reactivesw.product.infrastructure.util.ProductDraftUtils
import spock.lang.Specification

/**
 * Created by Davis on 17/4/11.
 */
class SkuNameValidatorTest extends Specification {
    SkuNameValidator validator = new SkuNameValidator()

    ProductDraft draft = new ProductDraft()
    def masterSku = "master sku"
    def variantSku1 = "variant 1 sku"
    def variantSku2 = "variant 2 sku"

    ProductVariantDraft masterVariant = new ProductVariantDraft(sku: masterSku)
    ProductVariantDraft variant1 = new ProductVariantDraft(sku: variantSku1)
    ProductVariantDraft variant2 = new ProductVariantDraft(sku: variantSku2)
    List uniqueVariants = Lists.newArrayList(variant1, variant2)
    List repeatedVariants = Lists.newArrayList(variant1, variant1)

    def setup() {
        draft.masterVariant = masterVariant
        draft.variants = uniqueVariants
    }

    def "Test1.1: validate that sku name is difference in ProductDraft"() {
        when:
        SkuNameValidator.validate(draft)

        then:
        true
    }

    def "Test1.2: validate tha sku name is difference, get repeated sku name and throw ParametersException"() {
        setup:
        draft.variants = repeatedVariants

        when:
        SkuNameValidator.validate(draft)

        then:
        thrown(ParametersException)
    }

    def "Test2.1: validate tha sku name is exists"() {
        setup:
        List products = Lists.newArrayList()

        when:
        SkuNameValidator.validate(draft, products)

        then:
        true
    }

    def "Test2.2: validate that sku name is exists, get exist sku and throw ConflictException"() {
        setup:
        ProductVariant variant = new ProductVariant(sku: masterSku)
        ProductData currentProduct = new ProductData(masterVariant: variant)
        ProductCatalogData masterData = new ProductCatalogData(current: currentProduct)
        Product product = new Product(masterData: masterData)
        List products = Lists.newArrayList(product)

        when:
        SkuNameValidator.validate(draft, products)

        then:
        thrown(ConflictException)
    }
}
