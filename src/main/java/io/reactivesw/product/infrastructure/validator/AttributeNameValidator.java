package io.reactivesw.product.infrastructure.validator;

import com.google.common.collect.Lists;

import io.reactivesw.exception.ParametersException;
import io.reactivesw.product.application.model.attribute.AttributeDefinition;
import io.reactivesw.product.domain.model.Attribute;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.domain.model.ProductData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Davis on 16/12/20.
 */
public final class AttributeNameValidator {

  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(AttributeNameValidator.class);

  /**
   * private constructor.
   */
  private AttributeNameValidator() {
  }

  /**
   * validateNull attribute name.
   *
   * @param attributeDefinitions List of AttributeDefinition
   * @param product the Product entity
   */
  public static void validate(List<AttributeDefinition> attributeDefinitions, Product product) {
    List<String> attributeNames = getAttributeDefinitionNames(attributeDefinitions);

    ProductData staged = product.getMasterData().getStaged();

    List<List<String>> attributeDraftNames = getAllAttributeNames(staged);

    attributeDraftNames.stream().forEach(
        attribute -> {
          if (!attributeNames.containsAll(attribute)) {
            LOG.debug("attribute not match, producttype should contains all attributes");
            throw new ParametersException("AttributeView Not Match");
          }
        }
    );
  }

  /**
   * Gets attribute definition names.
   *
   * @param attributeDefinitions the attribute definitions
   * @return the attribute definition names
   */
  private static List<String> getAttributeDefinitionNames(List<AttributeDefinition>
      attributeDefinitions) {
    return attributeDefinitions.stream().map(
        attributeDefinition -> {
          return attributeDefinition.getName();
        }).collect(Collectors.toList());
  }

  /**
   * Gets all attribute names.
   *
   * @param productData the ProductData entity
   * @return the all attribute names
   */
  private static List<List<String>> getAllAttributeNames(ProductData productData) {
    List<List<String>> result = Lists.newArrayList();
    if (productData.getMasterVariant() != null) {
      result.add(getAttributeNames(productData.getMasterVariant().getAttributes()));
    }

    if (productData.getVariants() != null) {
      result.addAll(productData.getVariants().stream().map(
          productVariantDraft -> {
            return getAttributeNames(productVariantDraft.getAttributes());
          })
          .collect(Collectors.toList()));
    }

    return result;
  }

  /**
   * Gets attribute names.
   *
   * @param attributes the attributes
   * @return the attribute names
   */
  private static List<String> getAttributeNames(List<Attribute> attributes) {
    List<String> result = Lists.newArrayList();
    if (attributes != null) {
      result = attributes.stream().map(
          attribute -> {
            return attribute.getName();
          }
      ).collect(Collectors.toList());
    }
    return result;
  }
}
