package io.reactivesw.product.application.model.attribute;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The Date attribute type.
 */
public final class DateAttributeType extends AbstractAttributeType {

  /**
   * private constructor.
   */
  private DateAttributeType() {
    super();
  }

  /**
   * build DateAttributeType from json data.
   *
   * @return DateAttributeType
   */
  @JsonIgnore
  public static DateAttributeType build() {
    return new DateAttributeType();
  }
}
