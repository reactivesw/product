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
import io.reactivesw.product.infrastructure.util.AttributeUtils
import spock.lang.Specification

/**
 * Created by Davis on 17/4/11.
 */
class CombinationAttributeValidatorTest extends Specification {
    CombinationAttributeValidator validator = new CombinationAttributeValidator()

    def productDraft = new ProductDraft()
    ObjectMapper mapper = new ObjectMapper();

    AttributeDefinition combinationAttribute = new AttributeDefinition()
    List<AttributeDefinition> attributeDefinitions = Lists.newArrayList()

    def setup() {
        def name = new LocalizedString()
        name.addKeyValue("en", "cup")
        productDraft.name = name

        def slug = new LocalizedString()
        slug.addKeyValue("en", "cup-111111")
        productDraft.slug = slug

        combinationAttribute = new AttributeDefinition()
        combinationAttribute.attributeConstraint = AttributeConstraint.CombinationUnique
        combinationAttribute.name = "combination unique attribute"
        combinationAttribute.isRequired = false

        attributeDefinitions = Lists.newArrayList(combinationAttribute)
    }

    def "Test1.1: validate combination attribute"() {
        setup:
        JsonNode value = mapper.readTree("\"require attribute value\"");
        ProductVariantDraft masterVariant = new ProductVariantDraft()
        masterVariant.sku = "master sku"
        AttributeView attribute2 = new AttributeView(name: "combination unique attribute", value: value)
        List<AttributeView> attributes = Lists.newArrayList(attribute2)
        masterVariant.attributes = attributes

        ProductVariantDraft variantDraft = new ProductVariantDraft()
        variantDraft.sku = "variant sku"
        JsonNode value2 = mapper.readTree("\"require attribute value2\"");
        AttributeView skuAttribute3 = new AttributeView(name: "combination unique attribute", value: value2)
        List<AttributeView> variantAttributes = Lists.newArrayList(skuAttribute3)
        variantDraft.attributes = variantAttributes
        List<ProductVariantDraft> variantDrafts = Lists.newArrayList(variantDraft)

        productDraft.masterVariant = masterVariant
        productDraft.variants = variantDrafts

        when:
        CombinationAttributeValidator.validate(attributeDefinitions, productDraft)

        then:
        true
    }

    def "Test1.2: combination attribute definition is empty"() {
        setup:
        JsonNode value = mapper.readTree("\"require attribute value\"");
        ProductVariantDraft masterVariant = new ProductVariantDraft()
        masterVariant.sku = "master sku"
        AttributeView attribute = new AttributeView(name: "require attribute", value: value)
        AttributeView attribute1 = new AttributeView(name: "unique attribute", value: value)
        AttributeView attribute2 = new AttributeView(name: "combination unique attribute", value: value)
        List<AttributeView> attributes = Lists.newArrayList(attribute, attribute1, attribute2)
        masterVariant.attributes = attributes

        ProductVariantDraft variantDraft = new ProductVariantDraft()
        variantDraft.sku = "variant sku"
        AttributeView skuAttribute1 = new AttributeView(name: "require attribute", value: value)
        JsonNode value2 = mapper.readTree("\"require attribute value2\"");
        AttributeView skuAttribute2 = new AttributeView(name: "unique attribute", value: value2)
        AttributeView skuAttribute3 = new AttributeView(name: "combination unique attribute", value: value2)
        List<AttributeView> variantAttributes = Lists.newArrayList(skuAttribute1, skuAttribute2, skuAttribute3)
        variantDraft.attributes = variantAttributes
        List<ProductVariantDraft> variantDrafts = Lists.newArrayList(variantDraft)

        productDraft.masterVariant = masterVariant
        productDraft.variants = variantDrafts

        when:
        CombinationAttributeValidator.validate(Lists.newArrayList(), productDraft)

        then:
        true
    }

    def "Test1.3: product variants is null"() {
        setup:
        JsonNode value = mapper.readTree("\"require attribute value\"");
        ProductVariantDraft masterVariant = new ProductVariantDraft()
        masterVariant.sku = "master sku"
        AttributeView attribute = new AttributeView(name: "require attribute", value: value)
        AttributeView attribute1 = new AttributeView(name: "unique attribute", value: value)
        AttributeView attribute2 = new AttributeView(name: "combination unique attribute", value: value)
        List<AttributeView> attributes = Lists.newArrayList(attribute, attribute1, attribute2)
        masterVariant.attributes = attributes

        productDraft.masterVariant = masterVariant

        when:
        CombinationAttributeValidator.validate(attributeDefinitions, productDraft)

        then:
        true
    }

    def "Test1.4: combination attribute is not combination unique, throw ParametersException"() {
        setup:
        JsonNode value = mapper.readTree("\"require attribute value\"");
        ProductVariantDraft masterVariant = new ProductVariantDraft()
        masterVariant.sku = "master sku"
        AttributeView attribute2 = new AttributeView(name: "combination unique attribute", value: value)
        List<AttributeView> attributes = Lists.newArrayList(attribute2)
        masterVariant.attributes = attributes

        ProductVariantDraft variantDraft = new ProductVariantDraft()
        variantDraft.sku = "variant sku"
        AttributeView skuAttribute3 = new AttributeView(name: "combination unique attribute", value: value)
        List<AttributeView> variantAttributes = Lists.newArrayList(skuAttribute3)
        variantDraft.attributes = variantAttributes
        List<ProductVariantDraft> variantDrafts = Lists.newArrayList(variantDraft)

        productDraft.masterVariant = masterVariant
        productDraft.variants = variantDrafts

        when:
        CombinationAttributeValidator.validate(attributeDefinitions, productDraft)

        then:
        thrown(ParametersException)
    }

    def "Test1.5: combination attribute definition is empty"() {
        setup:
        combinationAttribute.attributeConstraint = AttributeConstraint.None

        when:
        CombinationAttributeValidator.validate(Lists.newArrayList(combinationAttribute), productDraft)

        then:
        true
    }

    def "Test1.6: "() {
        setup:
        JsonNode value = mapper.readTree("\"require attribute value\"");
        ProductVariantDraft masterVariant = new ProductVariantDraft()
        masterVariant.sku = "master sku"
        AttributeView attribute = new AttributeView(name: "require attribute", value: value)
        AttributeView attribute1 = new AttributeView(name: "unique attribute", value: value)
        AttributeView attribute2 = new AttributeView(name: "combination unique attribute1", value: value)
        List<AttributeView> attributes = Lists.newArrayList(attribute, attribute1, attribute2)
        masterVariant.attributes = attributes

        ProductVariantDraft variantDraft = new ProductVariantDraft()
        variantDraft.sku = "variant sku"
        AttributeView skuAttribute1 = new AttributeView(name: "require attribute", value: value)
        JsonNode value2 = mapper.readTree("\"require attribute value2\"");
        AttributeView skuAttribute2 = new AttributeView(name: "unique attribute", value: value2)
        AttributeView skuAttribute3 = new AttributeView(name: "combination unique attribute1", value: value2)
        List<AttributeView> variantAttributes = Lists.newArrayList(skuAttribute1, skuAttribute2, skuAttribute3)
        variantDraft.attributes = variantAttributes
        List<ProductVariantDraft> variantDrafts = Lists.newArrayList(variantDraft)

        productDraft.masterVariant = masterVariant
        productDraft.variants = variantDrafts

        when:
        CombinationAttributeValidator.validate(attributeDefinitions, productDraft)

        then:
        true
    }
}