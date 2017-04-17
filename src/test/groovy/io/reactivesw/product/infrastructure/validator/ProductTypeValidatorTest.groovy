package io.reactivesw.product.infrastructure.validator

import io.reactivesw.exception.ParametersException
import io.reactivesw.product.application.admin.model.ProductDraft
import io.reactivesw.product.application.model.ResourceIdentifierView
import org.apache.commons.lang3.StringUtils
import spock.lang.Specification

/**
 * Created by Davis on 17/4/11.
 */
class ProductTypeValidatorTest extends Specification {
    ProductTypeValidator validator = new ProductTypeValidator()

    def "Test1.1: validate that product type reference is not null"() {
        setup:
        ResourceIdentifierView productType = new ResourceIdentifierView(typeId: "producttype", id: "producttypeid")
        ProductDraft draft = new ProductDraft(productType: productType)

        when:
        ProductTypeValidator.validateReference(draft)

        then:
        true
    }

    def "Test1.2: validate that product type reference is not null, get null product type and throw ParametersException"() {
        setup:
        ProductDraft draft = new ProductDraft()

        when:
        ProductTypeValidator.validateReference(draft)

        then:
        thrown(ParametersException)
    }

    def "Test1.3: validate that product type reference is not null, product type id is blank and throw ParametersException"() {
        setup:
        ResourceIdentifierView productType = new ResourceIdentifierView(typeId: "producttype", id: "")
        ProductDraft draft = new ProductDraft(productType: productType)

        when:
        ProductTypeValidator.validateReference(draft)

        then:
        thrown(ParametersException)
    }
}