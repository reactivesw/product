package io.reactivesw.product.application.admin.service.update;

import io.reactivesw.model.Updater;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.infrastructure.update.UpdateAction;

import org.springframework.stereotype.Service;

/**
 * The type Set slug service.
 */
@Service
public class SetSlugService implements Updater<Product, UpdateAction> {

  /**
   * Set slug.
   *
   * @param product the product entity
   * @param updateAction the SetSlug action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    // TODO: 17/5/10
  }
}
