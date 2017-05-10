package io.reactivesw.product.application.admin.service.update;

import io.reactivesw.model.Updater;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import org.springframework.stereotype.Service;

/**
 * The type Add external image service.
 */
@Service(UpdateActionUtils.ADD_EXTERNAL_IMAGE)
public class AddExternalImageService implements Updater<Product, UpdateAction> {

  /**
   * Add external image.
   *
   * @param product the product entity
   * @param updateAction the AddExternalImage action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    // TODO: 17/5/10
  }
}
