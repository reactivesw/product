package io.reactivesw.product.application.model.attribute;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The Number attribute type.
 */
public final class NumberAttributeType extends AbstractAttributeType {

  /**
   * private constructor.
   */
  private NumberAttributeType() {
    super();
  }

  /**
   * build NumberAttributeType from json data.
   *
   * @return NumberAttributeType
   */
  @JsonIgnore
  public static NumberAttributeType build() {
    return new NumberAttributeType();
  }
}