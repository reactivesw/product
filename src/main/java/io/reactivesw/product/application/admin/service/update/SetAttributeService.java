package io.reactivesw.product.application.admin.service.update;

import io.reactivesw.model.Updater;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import org.springframework.stereotype.Service;

/**
 * The type set attribute service.
 */
@Service(UpdateActionUtils.SET_ATTRIBUTE)
public class SetAttributeService implements Updater<Product, UpdateAction> {

  /**
   * Set attribute.
   *
   * @param product the product entity
   * @param updateAction the SetAttribute action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    // TODO: 17/5/10
  }
}
