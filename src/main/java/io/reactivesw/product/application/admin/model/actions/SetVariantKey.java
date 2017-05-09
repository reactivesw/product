package io.reactivesw.product.application.admin.model.actions;

import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Set variant key.
 */
@Setter
@Getter
public class SetVariantKey implements UpdateAction {

  /**
   * Variant Id for product.
   */
  private Integer variantId;

  /**
   * Key of variant.
   */
  private String key;

  /**
   * Get update service name.
   *
   * @return update service name
   */
  @Override
  public String getActionName() {
    return UpdateActionUtils.SET_VARIANT_KEY;
  }
}
