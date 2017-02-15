package io.reactivesw.product.application.model.attribute;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class EnumValue {
  private String key;
  private String label;
}
