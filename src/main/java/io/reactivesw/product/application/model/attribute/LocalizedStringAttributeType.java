package io.reactivesw.product.application.model.attribute;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The Localized string attribute type.
 */
public final class LocalizedStringAttributeType extends AbstractAttributeType {

  /**
   * private constructor.
   */
  private LocalizedStringAttributeType() {
    super();
  }

  /**
   * build LocalizedStringAttributeType from json data.
   *
   * @return LocalizedStringAttributeType
   */
  @JsonIgnore
  public static LocalizedStringAttributeType build() {
    return new LocalizedStringAttributeType();
  }
}