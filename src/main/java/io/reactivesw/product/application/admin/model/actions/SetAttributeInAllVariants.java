package io.reactivesw.product.application.admin.model.actions;

import com.fasterxml.jackson.databind.JsonNode;

import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Set attribute in all variants.
 */
@Setter
@Getter
public class SetAttributeInAllVariants implements UpdateAction {

  /**
   * Attribute name.
   */
  private String name;

  /**
   * Attribute value.
   */
  private JsonNode value;

  /**
   * get update service name.
   *
   * @return update service name
   */
  @Override
  public String getActionName() {
    return UpdateActionUtils.SET_ATTRIBUTE_IN_ALL_VARIANTS;
  }
}
