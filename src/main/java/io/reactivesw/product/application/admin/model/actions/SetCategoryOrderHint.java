package io.reactivesw.product.application.admin.model.actions;

import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Set category order hint.
 */
@Setter
@Getter
public class SetCategoryOrderHint implements UpdateAction {

  /**
   * Category Id.
   */
  private String categoryId;

  /**
   * The orderHint.
   * A number between 0 and 1.
   */
  private String orderHint;

  /**
   * get update service name.
   *
   * @return update service name
   */
  @Override
  public String getActionName() {
    return UpdateActionUtils.SET_CATEGORY_ORDER_HINT;
  }
}
