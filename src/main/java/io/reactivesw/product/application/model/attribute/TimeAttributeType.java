package io.reactivesw.product.application.model.attribute;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The Time attribute type.
 */
public final class TimeAttributeType extends AbstractAttributeType {

  /**
   * private constructor.
   */
  private TimeAttributeType() {
    super();
  }

  /**
   * build a new object.
   *
   * @return TimeAttributeType
   */
  @JsonIgnore
  public static TimeAttributeType build() {
    return new TimeAttributeType();
  }
}