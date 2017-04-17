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
class SameForAllAttributeValidatorTest extends Specification {
    SameForAllAttributeValidator validator = new SameForAllAttributeValidator()

    def productDraft = new ProductDraft()
    ObjectMapper mapper = new ObjectMapper();

    AttributeDefinition sameAttribute = new AttributeDefinition()
    List<AttributeDefinition> attributeDefinitions = Lists.newArrayList()

    def setup() {
        def name = new LocalizedString()
        name.addKeyValue("en", "cup")
        productDraft.name = name

        def slug = new LocalizedString()
        slug.addKeyValue("en", "cup-111111")
        productDraft.slug = slug

        sameAttribute = new AttributeDefinition()
        sameAttribute.attributeConstraint = AttributeConstraint.SameForAll
        sameAttribute.name = "same for all attribute"
        sameAttribute.isRequired = false

        attributeDefinitions = Lists.newArrayList(sameAttribute)
    }

    def "Test1.1: validate same for all attribute"() {
        setup:
        JsonNode value = mapper.readTree("\"require attribute value\"");
        ProductVariantDraft masterVariant = new ProductVariantDraft()
        masterVariant.sku = "master sku"
        AttributeView attribute = new AttributeView(name: "require attribute", value: value)
        AttributeView attribute1 = new AttributeView(name: "unique attribute", value: value)
        AttributeView attribute2 = new AttributeView(name: "combination unique attribute", value: value)
        AttributeView attribute3 = new AttributeView(name: "same for all attribute", value: value)
        List<AttributeView> attributes = Lists.newArrayList(attribute, attribute1, attribute2, attribute3)
        masterVariant.attributes = attributes

        ProductVariantDraft variantDraft = new ProductVariantDraft()
        variantDraft.sku = "variant sku"
        AttributeView skuAttribute1 = new AttributeView(name: "require attribute", value: value)
        JsonNode value2 = mapper.readTree("\"require attribute value2\"");
        AttributeView skuAttribute2 = new AttributeView(name: "unique attribute", value: value2)
        AttributeView skuAttribute3 = new AttributeView(name: "combination unique attribute", value: value2)
        AttributeView skuAttribute4 = new AttributeView(name: "same for all attribute", value: value)
        List<AttributeView> variantAttributes = Lists.newArrayList(skuAttribute1, skuAttribute2, skuAttribute3, skuAttribute4)
        variantDraft.attributes = variantAttributes
        List<ProductVariantDraft> variantDrafts = Lists.newArrayList(variantDraft)

        productDraft.masterVariant = masterVariant
        productDraft.variants = variantDrafts


        when:
        SameForAllAttributeValidator.validate(attributeDefinitions, productDraft)

        then:
        true
    }

    def "Test1.2: validate same for all attribute, attribute is different and throw ParametersException"() {
        setup:
        JsonNode value = mapper.readTree("\"require attribute value\"");
        ProductVariantDraft masterVariant = new ProductVariantDraft()
        masterVariant.sku = "master sku"
        AttributeView attribute = new AttributeView(name: "require attribute", value: value)
        AttributeView attribute1 = new AttributeView(name: "unique attribute", value: value)
        AttributeView attribute2 = new AttributeView(name: "combination unique attribute", value: value)
        AttributeView attribute3 = new AttributeView(name: "same for all attribute", value: value)
        List<AttributeView> attributes = Lists.newArrayList(attribute, attribute1, attribute2, attribute3)
        masterVariant.attributes = attributes

        ProductVariantDraft variantDraft = new ProductVariantDraft()
        variantDraft.sku = "variant sku"
        AttributeView skuAttribute1 = new AttributeView(name: "require attribute", value: value)
        JsonNode value2 = mapper.readTree("\"require attribute value2\"");
        AttributeView skuAttribute2 = new AttributeView(name: "unique attribute", value: value2)
        AttributeView skuAttribute3 = new AttributeView(name: "combination unique attribute", value: value2)
        AttributeView skuAttribute4 = new AttributeView(name: "same for all attribute", value: value2)
        List<AttributeView> variantAttributes = Lists.newArrayList(skuAttribute1, skuAttribute2, skuAttribute3, skuAttribute4)
        variantDraft.attributes = variantAttributes
        List<ProductVariantDraft> variantDrafts = Lists.newArrayList(variantDraft)

        productDraft.masterVariant = masterVariant
        productDraft.variants = variantDrafts


        when:
        SameForAllAttributeValidator.validate(attributeDefinitions, productDraft)

        then:
        thrown(ParametersException)
    }

    def "Test1.3: validate same for all attribute, master variant attribute is null"() {
        setup:
        JsonNode value = mapper.readTree("\"require attribute value\"");
        ProductVariantDraft masterVariant = new ProductVariantDraft()
        masterVariant.sku = "master sku"
        List<AttributeView> attributes = Lists.newArrayList()
        masterVariant.attributes = attributes

        productDraft.masterVariant = masterVariant

        when:
        SameForAllAttributeValidator.validate(attributeDefinitions, productDraft)

        then:
        true
    }
}
