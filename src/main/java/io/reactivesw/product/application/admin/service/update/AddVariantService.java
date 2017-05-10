package io.reactivesw.product.application.admin.service.update;

import io.reactivesw.model.Updater;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import org.springframework.stereotype.Service;

/**
 * The type Add variant service.
 */
@Service(UpdateActionUtils.ADD_VARIANT)
public class AddVariantService implements Updater<Product, UpdateAction> {

  /**
   * Add variant.
   *
   * @param product the product entity
   * @param updateAction the AddVariant action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    // TODO: 17/5/10
  }
}
