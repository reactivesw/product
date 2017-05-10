package io.reactivesw.product.application.admin.service.update;

import io.reactivesw.model.Updater;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import org.springframework.stereotype.Service;

/**
 * The type Add to category service.
 */
@Service(UpdateActionUtils.ADD_TO_CATEGORY)
public class AddToCategoryService implements Updater<Product, UpdateAction> {

  /**
   * Add to category.
   *
   * @param product the product entity
   * @param updateAction the AddToCategory action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    // TODO: 17/5/10
  }
}
