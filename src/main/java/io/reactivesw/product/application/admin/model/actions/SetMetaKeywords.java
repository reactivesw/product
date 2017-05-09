package io.reactivesw.product.application.admin.model.actions;

import io.reactivesw.model.LocalizedString;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Set meta keywords.
 */
@Setter
@Getter
public class SetMetaKeywords implements UpdateAction {

  /**
   * The meta keywords.
   */
  private LocalizedString metaKeywords;

  /**
   * Get update service name.
   *
   * @return update service name
   */
  @Override
  public String getActionName() {
    return UpdateActionUtils.SET_META_KEYWORDS;
  }
}
