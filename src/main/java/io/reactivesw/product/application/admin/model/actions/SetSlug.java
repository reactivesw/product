package io.reactivesw.product.application.admin.model.actions;

import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Set slug.
 */
@Setter
@Getter
public class SetSlug implements UpdateAction {

  /**
   * Slug for product.
   */
  private String slug;

  /**
   * Get update service name.
   *
   * @return update service name
   */
  @Override
  public String getActionName() {
    return UpdateActionUtils.SET_SLUG;
  }
}
