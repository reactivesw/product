package io.reactivesw.product.application.admin.service.update;

import io.reactivesw.model.Updater;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import org.springframework.stereotype.Service;

/**
 * The type Set prices service.
 */
@Service(UpdateActionUtils.SET_PRICES)
public class SetPricesService implements Updater<Product, UpdateAction> {

  /**
   * Set prices.
   *
   * @param product the product entity
   * @param updateAction the SetPrices action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    // TODO: 17/5/10
  }
}
