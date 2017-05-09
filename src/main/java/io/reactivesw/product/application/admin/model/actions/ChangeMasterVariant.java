package io.reactivesw.product.application.admin.model.actions;

import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Change master variant.
 */
@Setter
@Getter
public class ChangeMasterVariant implements UpdateAction {

  /**
   * Variant Id for product.
   */
  private Integer variantId;

  /**
   * get update service name.
   *
   * @return update service name
   */
  @Override
  public String getActionName() {
    return UpdateActionUtils.CHANGE_MASTER_VARIANT;
  }
}
