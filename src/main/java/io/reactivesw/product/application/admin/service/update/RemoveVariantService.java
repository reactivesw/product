package io.reactivesw.product.application.admin.service.update;

import io.reactivesw.model.Updater;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import org.springframework.stereotype.Service;

/**
 * The type Remove variant service.
 */
@Service(UpdateActionUtils.REMOVE_VARIANT)
public class RemoveVariantService implements Updater<Product, UpdateAction> {

  /**
   * Remove variant.
   *
   * @param product the product entity
   * @param updateAction the RemoveVariant action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    // TODO: 17/5/10
  }
}