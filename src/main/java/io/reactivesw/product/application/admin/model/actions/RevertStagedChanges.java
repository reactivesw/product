package io.reactivesw.product.application.admin.model.actions;

import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

/**
 * The type Revert staged changes.
 */
public class RevertStagedChanges implements UpdateAction {

  /**
   * get update service name.
   *
   * @return update service name
   */
  @Override
  public String getActionName() {
    return UpdateActionUtils.REVERT_STAGED_CHANGES;
  }
}
