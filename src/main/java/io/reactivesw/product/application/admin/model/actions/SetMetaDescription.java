package io.reactivesw.product.application.admin.model.actions;

import io.reactivesw.model.LocalizedString;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Set meta description.
 */
@Setter
@Getter
public class SetMetaDescription implements UpdateAction {

  /**
   * The meta description.
   */
  private LocalizedString metaDescription;

  /**
   * Get update service name.
   *
   * @return update service name
   */
  @Override
  public String getActionName() {
    return UpdateActionUtils.SET_META_DESCRIPTION;
  }
}
