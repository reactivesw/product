package io.reactivesw.product.domain.service.update;

import io.reactivesw.model.Updater;
import io.reactivesw.product.application.admin.model.actions.SetKey;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * The type Set key service.
 */
@Service(UpdateActionUtils.SET_KEY)
public class SetKeyService implements Updater<Product, UpdateAction> {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(SetKeyService.class);

  /**
   * Handle.
   *
   * @param product the product
   * @param updateAction the update action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    LOG.debug("Enter. ProductId: {}.", product.getId());

    SetKey key = (SetKey) updateAction;
    product.setKey(key.getKey());

    LOG.trace("Updated product: {}.", product);
    LOG.debug("Exit.");
  }
}
