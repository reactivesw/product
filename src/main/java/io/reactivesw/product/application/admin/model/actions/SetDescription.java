package io.reactivesw.product.application.admin.model.actions;

import io.reactivesw.model.LocalizedString;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Set description.
 */
@Setter
@Getter
public class SetDescription implements UpdateAction {

  /**
   * Description for product.
   */
  private LocalizedString description;

  /**
   * Get update service name.
   *
   * @return update service name
   */
  @Override
  public String getActionName() {
    return UpdateActionUtils.SET_DESCRIPTION;
  }
}
