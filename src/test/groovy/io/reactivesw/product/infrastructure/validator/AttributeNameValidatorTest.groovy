package io.reactivesw.product.infrastructure.validator

import com.google.common.collect.Lists
import io.reactivesw.exception.ParametersException
import io.reactivesw.product.application.admin.model.ProductDraft
import io.reactivesw.product.application.admin.model.ProductVariantDraft
import io.reactivesw.product.application.model.attribute.AttributeDefinition
import io.reactivesw.product.application.model.attribute.AttributeView
import org.spockframework.compiler.model.SetupBlock
import spock.lang.Specification

/**
 * Created by Davis on 17/4/11.
 */
class AttributeNameValidatorTest extends Specification {
    AttributeNameValidator validator = new AttributeNameValidator()

    def attributeName1 = "attribute 1 name"
    def attributeName2 = "attribute 2 name"
    AttributeView attribute1 = new AttributeView(name: attributeName1)
    AttributeView attribute2 = new AttributeView(name: attributeName2)
    List<AttributeView> attributes = Lists.newArrayList(attribute1, attribute2)
    ProductVariantDraft masterVariant = new ProductVariantDraft(attributes: attributes)
    ProductVariantDraft variant = new ProductVariantDraft(attributes: attributes)
    ProductDraft draft = new ProductDraft(masterVariant: masterVariant, variants: Lists.newArrayList(variant))
    AttributeDefinition attributeDefinition1 = new AttributeDefinition(name: attributeName1)
    AttributeDefinition attributeDefinition2 = new AttributeDefinition(name: attributeName2)
    List<AttributeDefinition> attributeDefinitions = Lists.newArrayList(attributeDefinition1, attributeDefinition2)

    def "Test1.1: validate that contains all attributes"() {
        when:
        AttributeNameValidator.validate(attributeDefinitions, draft)

        then:
        true
    }

    def "Test1.2: validate that contains all attributes, attribute not match and throw ParametersException"() {
        setup:
        List<AttributeDefinition> attributeDefinitions1 = Lists.newArrayList(attributeDefinition2)

        when:
        AttributeNameValidator.validate(attributeDefinitions1, draft)

        then:
        thrown(ParametersException)
    }

    def "Test1.3: validate that contains all attributes, master and variants is null"() {
        setup:
        draft = new ProductDraft()

        when:
        AttributeNameValidator.validate(attributeDefinitions, draft)

        then:
        true
    }
}
