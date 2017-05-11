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
   * The previousOrderHint.
   * A number between 0 and 1.
   * Should not be blank.
   */
  private String previousOrderHint;

  /**
   * The nextOrderHint.
   * A number between 0 and 1.
   * If this is blank, means that change product to the last one.
   */
  private String nextOrderHint;

  /**
   * Get update service name.
   *
   * @return update service name
   */
  @Override
  public String getActionName() {
    return UpdateActionUtils.SET_CATEGORY_ORDER_HINT;
  }
}
