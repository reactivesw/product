package io.reactivesw.product.infrastructure.util;

import io.reactivesw.product.application.model.attribute.AttributeConstraint;
import io.reactivesw.product.application.model.attribute.AttributeDefinition;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Attribute Utility class.
 */
public final class AttributeUtils {

  /**
   * private constructor.
   */
  private AttributeUtils() {
  }

  /**
   * Gets attribute name by constraint.
   *
   * @param attributeDefinitions the attribute definitions
   * @param attributeConstraint  the attribute constraint
   * @return the attribute name by constraint
   */
  public static List<String> getAttributeNameByConstraint(
      List<AttributeDefinition> attributeDefinitions,
      AttributeConstraint attributeConstraint) {

    return attributeDefinitions.stream().filter(
        attributeDefinition ->
            attributeDefinition.getAttributeConstraint().equals(attributeConstraint)
    ).map(attributeDefinition -> attributeDefinition.getName()
    ).collect(Collectors.toList());
  }
}
