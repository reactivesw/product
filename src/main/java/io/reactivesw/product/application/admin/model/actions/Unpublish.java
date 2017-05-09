package io.reactivesw.product.application.admin.model.actions;

import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

/**
 * The type Unpublish.
 */
public class Unpublish implements UpdateAction {

  /**
   * get update service name.
   *
   * @return update service name
   */
  @Override
  public String getActionName() {
    return UpdateActionUtils.UNPUBLISH;
  }
}
