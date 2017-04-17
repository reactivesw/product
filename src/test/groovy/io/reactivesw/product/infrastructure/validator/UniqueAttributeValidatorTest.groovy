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
import io.reactivesw.product.domain.model.Product
import spock.lang.Specification

import java.time.ZonedDateTime

/**
 * Created by Davis on 17/4/11.
 */
class UniqueAttributeValidatorTest extends Specification {
    UniqueAttributeValidator validator = new UniqueAttributeValidator()

    def productDraft = new ProductDraft()
    ObjectMapper mapper = new ObjectMapper();

    AttributeDefinition noneAttribute = new AttributeDefinition()
    AttributeDefinition uniqueAttribute = new AttributeDefinition()
    AttributeDefinition combinationAttribute = new AttributeDefinition()
    AttributeDefinition sameAttribute = new AttributeDefinition()
    AttributeDefinition requireAttribute = new AttributeDefinition()
    List<AttributeDefinition> attributeDefinitions = Lists.newArrayList()

    def setup() {
        def name = new LocalizedString()
        name.addKeyValue("en", "cup")
        productDraft.name = name

        def slug = new LocalizedString()
        slug.addKeyValue("en", "cup-111111")
        productDraft.slug = slug

        noneAttribute = new AttributeDefinition()
        noneAttribute.attributeConstraint = AttributeConstraint.None
        noneAttribute.name = "none attribute"
        noneAttribute.isRequired = false

        uniqueAttribute = new AttributeDefinition()
        uniqueAttribute.attributeConstraint = AttributeConstraint.Unique
        uniqueAttribute.name = "unique attribute"
        uniqueAttribute.isRequired = false

        combinationAttribute = new AttributeDefinition()
        combinationAttribute.attributeConstraint = AttributeConstraint.CombinationUnique
        combinationAttribute.name = "combination unique attribute"
        combinationAttribute.isRequired = false

        sameAttribute = new AttributeDefinition()
        sameAttribute.attributeConstraint = AttributeConstraint.SameForAll
        sameAttribute.name = "same for all attribute"
        sameAttribute.isRequired = false

        requireAttribute = new AttributeDefinition()
        requireAttribute.attributeConstraint = AttributeConstraint.None
        requireAttribute.name = "require attribute"
        requireAttribute.isRequired = true
        attributeDefinitions = Lists.newArrayList(noneAttribute, uniqueAttribute, combinationAttribute, sameAttribute, requireAttribute)
    }


    def "Test1.1: validate unique attribute"() {
        setup:
        JsonNode value = mapper.readTree("\"require attribute value\"");
        ProductVariantDraft masterVariant = new ProductVariantDraft()
        masterVariant.sku = "master sku"
        AttributeView attribute = new AttributeView(name: "require attribute", value: value)
        List<AttributeView> attributes = Lists.newArrayList(attribute)
        masterVariant.attributes = attributes

        productDraft.masterVariant = masterVariant


        when:
        UniqueAttributeValidator.validate(attributeDefinitions, productDraft)

        then:
        true
    }

    def "Test1.2: validate unique attribute, attribute has repeated value and throw ParametersException"() {
        setup:
        JsonNode value = mapper.readTree("\"require attribute value\"");
        ProductVariantDraft masterVariant = new ProductVariantDraft()
        masterVariant.sku = "master sku"
        AttributeView attribute = new AttributeView(name: "require attribute", value: value)
        AttributeView attribute1 = new AttributeView(name: "unique attribute", value: value)
        List<AttributeView> attributes = Lists.newArrayList(attribute, attribute1)
        masterVariant.attributes = attributes

        ProductVariantDraft variantDraft = new ProductVariantDraft()
        variantDraft.sku = "variant sku"
        AttributeView skuAttribute1 = new AttributeView(name: "require attribute", value: value)
        AttributeView skuAttribute2 = new AttributeView(name: "unique attribute", value: value)
        List<AttributeView> variantAttributes = Lists.newArrayList(skuAttribute1, skuAttribute2)
        variantDraft.attributes = variantAttributes
        List<ProductVariantDraft> variantDrafts = Lists.newArrayList(variantDraft)

        productDraft.masterVariant = masterVariant
        productDraft.variants = variantDrafts


        when:
        UniqueAttributeValidator.validate(attributeDefinitions, productDraft)

        then:
        thrown(ParametersException)
    }

    def "Test1.3: validate unique attribute, master variant is null"() {
        when:
        UniqueAttributeValidator.validate(attributeDefinitions, productDraft)

        then:
        true
    }

    def "Test1.4: validate unique attribute, master variant attribute is null"() {
        setup:
        ProductVariantDraft masterVariant = new ProductVariantDraft()
        masterVariant.sku = "master sku"
        productDraft.masterVariant = masterVariant

        when:
        UniqueAttributeValidator.validate(attributeDefinitions, productDraft)

        then:
        true
    }
}