package io.reactivesw.product.application.model.attribute;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Type for product attributes which only allows {@code true} or {@code false} as value.
 */
public final class BooleanAttributeType extends AbstractAttributeType {

  /**
   * private constructor.
   */
  private BooleanAttributeType() {
    super();
  }

  /**
   * build BooleanAttributeType from json data.
   *
   * @return BooleanAttributeType
   */
  @JsonCreator
  public static BooleanAttributeType build() {
    return new BooleanAttributeType();
  }
}
