package io.reactivesw.product.application.model.attribute;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ReferenceAttributeType extends AttributeTypeBase {
  private String referenceTypeId;
}
