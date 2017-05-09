package io.reactivesw.product.application.admin.model.actions;

import io.reactivesw.product.application.admin.model.PriceDraft;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Add price.
 */
@Setter
@Getter
public class AddPrice implements UpdateAction {

  /**
   * Variant Id for product.
   */
  private Integer variantId;

  /**
   * Price to add.
   */
  private PriceDraft price;

  /**
   * get update service name.
   *
   * @return update service name
   */
  @Override
  public String getActionName() {
    return UpdateActionUtils.ADD_PRICE;
  }
}
