package io.reactivesw.product.infrastructure.validator

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.common.collect.Lists
import io.reactivesw.exception.ParametersException
import io.reactivesw.model.LocalizedString
import io.reactivesw.product.application.admin.model.ProductDraft
import io.reactivesw.product.application.admin.model.ProductVariantDraft
import io.reactivesw.product.application.model.attribute.AttributeConstraint
import io.reactivesw.product.application.model.attribute.AttributeDefinition
import io.reactivesw.product.application.model.attribute.AttributeView
import spock.lang.Specification

/**
 * Created by Davis on 17/4/11.
 */
class RequireAttributeValidatorTest extends Specification {
    RequireAttributeValidator validator = new RequireAttributeValidator()

    def productDraft = new ProductDraft()
    ObjectMapper mapper = new ObjectMapper();

    AttributeDefinition requireAttribute = new AttributeDefinition()
    List<AttributeDefinition> attributeDefinitions = Lists.newArrayList()

    def setup() {
        def name = new LocalizedString()
        name.addKeyValue("en", "cup")
        productDraft.name = name

        def slug = new LocalizedString()
        slug.addKeyValue("en", "cup-111111")
        productDraft.slug = slug

        requireAttribute = new AttributeDefinition()
        requireAttribute.attributeConstraint = AttributeConstraint.None
        requireAttribute.name = "require attribute"
        requireAttribute.isRequired = true
        attributeDefinitions = Lists.newArrayList(requireAttribute)
    }


    def "Test1.1: validate require attribute"() {
        setup:
        JsonNode value = mapper.readTree("\"require attribute value\"");
        ProductVariantDraft masterVariant = new ProductVariantDraft()
        masterVariant.sku = "master sku"
        AttributeView attribute = new AttributeView(name: "require attribute", value: value)
        List<AttributeView> attributes = Lists.newArrayList(attribute)
        masterVariant.attributes = attributes

        ProductVariantDraft variantDraft = new ProductVariantDraft()
        variantDraft.sku = "variant sku"
        JsonNode value2 = mapper.readTree("\"require attribute value2\"");
        AttributeView skuAttribute2 = new AttributeView(name: "require attribute", value: value2)
        List<AttributeView> variantAttributes = Lists.newArrayList(skuAttribute2)
        variantDraft.attributes = variantAttributes
        List<ProductVariantDraft> variantDrafts = Lists.newArrayList(variantDraft)

        productDraft.masterVariant = masterVariant
        productDraft.variants = variantDrafts

        when:
        RequireAttributeValidator.validate(attributeDefinitions, productDraft)

        then:
        true
    }

    def "Test1.2: require attribute definition is null"() {
        when:
        RequireAttributeValidator.validate(null, productDraft)

        then:
        true
    }

    def "Test1.3: master variant is null, throw ParametersException"() {
        when:
        RequireAttributeValidator.validate(attributeDefinitions, productDraft)

        then:
        thrown(ParametersException)
    }

    def "Test1.4: require attribute is empty, throw ParametersException"() {
        setup:
        JsonNode value = mapper.readTree("\"require attribute value\"");
        ProductVariantDraft masterVariant = new ProductVariantDraft()
        masterVariant.sku = "master sku"
        List<AttributeView> attributes = Lists.newArrayList()
        masterVariant.attributes = attributes

        productDraft.masterVariant = masterVariant

        when:
        RequireAttributeValidator.validate(attributeDefinitions, productDraft)

        then:
        thrown(ParametersException)
    }
}
