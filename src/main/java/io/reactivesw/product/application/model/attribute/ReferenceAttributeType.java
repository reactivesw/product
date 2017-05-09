package io.reactivesw.product.application.model.attribute;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The Reference attribute type.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReferenceAttributeType extends AbstractAttributeType {

  /**
   * reference type id.
   */
  private String referenceTypeId;
}