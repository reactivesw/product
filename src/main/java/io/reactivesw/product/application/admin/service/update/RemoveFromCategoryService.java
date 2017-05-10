package io.reactivesw.product.application.admin.service.update;

import io.reactivesw.model.Updater;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import org.springframework.stereotype.Service;

/**
 * The type Remove from category service.
 */
@Service(UpdateActionUtils.REMOVE_FROM_CATEGORY)
public class RemoveFromCategoryService implements Updater<Product, UpdateAction> {

  /**
   * Remove from category.
   *
   * @param product the product action
   * @param updateAction the RemoveFromCategory action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    // TODO: 17/5/10
  }
}
