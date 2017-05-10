package io.reactivesw.product.application.admin.service.update;

import io.reactivesw.model.Updater;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import org.springframework.stereotype.Service;

/**
 * The type Change master variant service.
 */
@Service(UpdateActionUtils.CHANGE_MASTER_VARIANT)
public class ChangeMasterVariantService implements Updater<Product, UpdateAction> {

  /**
   * Change master variant.
   *
   * @param product the product entity
   * @param updateAction the ChangeMasterVariant action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    // TODO: 17/5/10
  }
}
