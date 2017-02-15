package io.reactivesw.product.application.model.attribute;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public final class EnumAttributeType extends AttributeTypeBase {
  private List<EnumValue> values;
}
