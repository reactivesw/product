package io.reactivesw.product.application.admin.model.actions;

import io.reactivesw.product.application.admin.model.PriceDraft;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Change price.
 */
@Setter
@Getter
public class ChangePrice implements UpdateAction {

  /**
   * Variant Id for product.
   */
  private Integer variantId;

  /**
   * Price Id.
   */
  private String priceId;

  /**
   * The price draft.
   */
  private PriceDraft price;

  /**
   * Get update service name.
   *
   * @return update service name
   */
  @Override
  public String getActionName() {
    return UpdateActionUtils.CHANGE_PRICE;
  }
}
