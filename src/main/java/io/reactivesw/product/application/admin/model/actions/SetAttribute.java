package io.reactivesw.product.application.admin.model.actions;

import com.fasterxml.jackson.databind.JsonNode;

import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Set attribute.
 */
@Setter
@Getter
public class SetAttribute implements UpdateAction {

  /**
   * Variant Id for product.
   */
  private Integer variantId;

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
    return UpdateActionUtils.SET_ATTRIBUTE;
  }
}
