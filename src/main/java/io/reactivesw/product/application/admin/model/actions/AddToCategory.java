package io.reactivesw.product.application.admin.model.actions;

import io.reactivesw.model.Reference;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Add to category.
 */
@Setter
@Getter
public class AddToCategory implements UpdateAction {

  /**
   * Category to add.
   */
  private Reference category;

  /**
   * OrderHint, number between 0 and 1.
   */
  private String orderHint;

  /**
   * get update service name.
   *
   * @return update service name
   */
  @Override
  public String getActionName() {
    return UpdateActionUtils.ADD_TO_CATEGORY;
  }
}
