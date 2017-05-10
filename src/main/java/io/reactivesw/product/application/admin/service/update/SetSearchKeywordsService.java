package io.reactivesw.product.application.admin.service.update;

import io.reactivesw.model.Updater;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import org.springframework.stereotype.Service;

/**
 * The type Set search keywords service.
 */
@Service(UpdateActionUtils.SET_SEARCH_KEYWORDS)
public class SetSearchKeywordsService implements Updater<Product, UpdateAction> {

  /**
   * Set search keywords.
   *
   * @param product the product entity
   * @param updateAction the SetSearchKeywords action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    // TODO: 17/5/10
  }
}
