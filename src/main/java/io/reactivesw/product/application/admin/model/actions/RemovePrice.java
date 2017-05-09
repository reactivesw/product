package io.reactivesw.product.application.admin.model.actions;

import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Remove price.
 */
@Setter
@Getter
public class RemovePrice implements UpdateAction {

  /**
   * Id of the price.
   */
  private String priceId;

  /**
   * get update service name.
   *
   * @return update service name
   */
  @Override
  public String getActionName() {
    return UpdateActionUtils.REMOVE_PRICE;
  }
}
