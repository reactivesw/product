package io.reactivesw.product.application.admin.service.update;

import io.reactivesw.model.Updater;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import org.springframework.stereotype.Service;

/**
 * The type Set meta title service.
 */
@Service(UpdateActionUtils.SET_META_TITLE)
public class SetMetaTitleService implements Updater<Product, UpdateAction> {

  /**
   * Set meta title.
   *
   * @param product the product entity
   * @param updateAction the SetMetaTitle action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    // TODO: 17/5/10
  }
}
