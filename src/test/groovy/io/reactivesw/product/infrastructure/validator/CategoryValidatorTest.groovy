package io.reactivesw.product.infrastructure.validator

import io.reactivesw.exception.ParametersException
import io.reactivesw.model.Reference
import io.reactivesw.product.infrastructure.util.ReferenceTypes
import spock.lang.Specification

/**
 * Test for CategoryValidator.
 */
class CategoryValidatorTest extends Specification {
    CategoryValidator validator = new CategoryValidator()

    def "Test1: category is ok"() {
        given:
        Reference category = new Reference(ReferenceTypes.CATEGORY.getType(), "categoryId")

        when:
        CategoryValidator.validateCategory(category)

        then:
        true
    }

    def "Test2: category is null"() {
        given:
        Reference category = null

        when:
        CategoryValidator.validateCategory(category)

        then:
        thrown(ParametersException)
    }

    def "Test3: not a category reference"() {
        given:
        Reference category = new Reference(ReferenceTypes.CART.getType(), "categoryId")

        when:
        CategoryValidator.validateCategory(category)

        then:
        thrown(ParametersException)
    }

    def "Test4: category id is blank"() {
        given:
        Reference category = new Reference(ReferenceTypes.CART.getType(), "")

        when:
        CategoryValidator.validateCategory(category)

        then:
        thrown(ParametersException)
    }
}
