package io.reactivesw.product.application.admin.model.actions;

import io.reactivesw.product.application.model.SearchKeyword;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Set search keywords.
 */
@Setter
@Getter
public class SetSearchKeywords implements UpdateAction {

  /**
   * The SearchKeywords.
   */
  private SearchKeyword searchKeywords;

  /**
   * get update service name.
   *
   * @return update service name
   */
  @Override
  public String getActionName() {
    return UpdateActionUtils.SET_SEARCH_KEYWORDS;
  }
}
