package io.reactivesw.product.infrastructure.validator;

import io.reactivesw.exception.ParametersException;
import io.reactivesw.product.application.model.attribute.AttributeConstraint;
import io.reactivesw.product.application.model.attribute.AttributeDefinition;
import io.reactivesw.product.domain.model.Attribute;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.domain.model.ProductData;
import io.reactivesw.product.infrastructure.util.AttributeUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Davis on 16/12/20.
 */
public final class SameForAllAttributeValidator {

  /**
   * log.
   */
  private static final Logger LOG =
      LoggerFactory.getLogger(SameForAllAttributeValidator.class);

  /**
   * private constructor.
   */
  private SameForAllAttributeValidator() {
  }

  /**
   * Validate same for all attribute.
   *
   * @param attributeDefinitions the attribute definitions
   * @param product the Product entity
   */
  public static void validate(List<AttributeDefinition> attributeDefinitions, Product product) {
    List<String> attributeNames = AttributeUtils.getAttributeNameByConstraint(attributeDefinitions,
        AttributeConstraint.SameForAll);

    ProductData staged = product.getMasterData().getStaged();

    attributeNames.forEach(
        attributeName -> {
          validate(attributeName, staged);
        }
    );
  }

  /**
   * Validate same for all attribute.
   *
   * @param attributeName the attribute name
   * @param productData the ProductData entity
   */
  private static void validate(String attributeName, ProductData productData) {
    List<Attribute> masterAttributes = productData.getMasterVariant().getAttributes();
    // TODO: 16/12/19 attributeValue should be JsonNode type
    String attributeValue = "";
    if (masterAttributes != null) {
      List<String> masterAttributeValue = masterAttributes.stream().filter(
          attribute -> attribute.getName().equals(attributeName))
          .map(
              attribute -> {
                return attribute.getValue().toString();
              }
          ).collect(Collectors.toList());
      if (masterAttributeValue != null && !masterAttributeValue.isEmpty()) {
        attributeValue = masterAttributeValue.get(0);
      }
    }

    String finalAttributeValue = attributeValue;
    if (productData.getVariants() != null) {
      productData.getVariants().stream()
          .forEach(productVariantDraft -> {
            productVariantDraft.getAttributes().forEach(
                attribute -> {
                  if (attribute.getName().equals(attributeName)
                      && !attribute.getValue().toString().equals(finalAttributeValue)) {
                    LOG.debug("same for all attribute should have same value");
                    throw new ParametersException(
                        "same for all attribute should have same value");
                  }
                }
            );
          });
    }
  }
}
