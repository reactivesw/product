package io.reactivesw.product.application.admin.model.actions;

import io.reactivesw.model.Reference;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Remove from category.
 */
@Setter
@Getter
public class RemoveFromCategory implements UpdateAction {

  /**
   * Category to remove from.
   */
  private Reference category;

  /**
   * get update service name.
   *
   * @return update service name
   */
  @Override
  public String getActionName() {
    return UpdateActionUtils.REMOVE_FROM_CATEGORY;
  }
}
