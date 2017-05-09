package io.reactivesw.product.application.model.attribute;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Enum value for EnumAttribute.
 */
@Data
@AllArgsConstructor
public final class EnumValue {

  /**
   * key.
   */
  private String key;

  /**
   * label.
   */
  private String label;
}
