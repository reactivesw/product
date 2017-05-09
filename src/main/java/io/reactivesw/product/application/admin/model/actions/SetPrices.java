package io.reactivesw.product.application.admin.model.actions;

import io.reactivesw.product.application.admin.model.PriceDraft;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * The type Set prices.
 */
@Setter
@Getter
public class SetPrices implements UpdateAction {

  /**
   * Variant Id for product.
   */
  private Integer variantId;

  /**
   * Prices to add.
   */
  private List<PriceDraft> prices;

  /**
   * get update service name.
   *
   * @return update service name
   */
  @Override
  public String getActionName() {
    return UpdateActionUtils.SET_PRICES;
  }
}
