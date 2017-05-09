package io.reactivesw.product.application.admin.model.actions;

import io.reactivesw.model.LocalizedString;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Set name.
 */
@Setter
@Getter
public class SetName implements UpdateAction {

  /**
   * Name for product.
   */
  private LocalizedString name;

  /**
   * Get update service name.
   *
   * @return update service name
   */
  @Override
  public String getActionName() {
    return UpdateActionUtils.SET_NAME;
  }
}
