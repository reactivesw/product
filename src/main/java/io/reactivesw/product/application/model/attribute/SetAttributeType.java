package io.reactivesw.product.application.model.attribute;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The type Set attribute type.
 */
public final class SetAttributeType extends AbstractAttributeType {

  /**
   * attribute type.
   */
  private transient AttributeType elementType;

  /**
   * build from json data.
   *
   * @param elementType SetAttributeType
   */
  @JsonCreator
  private SetAttributeType(final AttributeType elementType) {
    super();
    this.elementType = elementType;
  }

  /**
   * Gets element type.
   *
   * @return the element type
   */
  public AttributeType getElementType() {
    return elementType;
  }

  /**
   * build from AttributeType.
   *
   * @param elementType the element type
   * @return the set attribute type
   */
  @JsonIgnore
  public static SetAttributeType build(final AttributeType elementType) {
    return new SetAttributeType(elementType);
  }
}