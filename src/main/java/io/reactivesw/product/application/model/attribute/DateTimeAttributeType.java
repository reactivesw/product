package io.reactivesw.product.application.model.attribute;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The Date time attribute type.
 */
public final class DateTimeAttributeType extends AbstractAttributeType {

  /**
   * private constructor.
   */
  private DateTimeAttributeType() {
    super();
  }

  /**
   * build DateTimeAttributeType from json data.
   *
   * @return DateTimeAttributeType
   */
  @JsonIgnore
  public static DateTimeAttributeType build() {
    return new DateTimeAttributeType();
  }
}

