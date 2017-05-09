package io.reactivesw.product.application.model.attribute;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * The Localized enum attribute type.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public final class LocalizedEnumAttributeType extends AbstractAttributeType {

  /**
   * Localized Enum Value list.
   */
  private List<LocalizedEnumValue> values;
}