package io.reactivesw.product.application.admin.service.update;

import io.reactivesw.model.Updater;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import org.springframework.stereotype.Service;

/**
 * The type Set category order hint service.
 */
@Service(UpdateActionUtils.SET_CATEGORY_ORDER_HINT)
public class SetCategoryOrderHintService implements Updater<Product, UpdateAction> {

  /**
   * Set category orderHint.
   *
   * @param product the product entity
   * @param updateAction the SetCategoryOrderHint action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    // TODO: 17/5/10
  }
}
