package io.reactivesw.product.application.admin.service.update;

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
   * Set Key.
   *
   * @param product the product
   * @param updateAction the SetKey action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    LOG.debug("Enter. ProductId: {}, update action: {}.", product.getId(), updateAction);

    SetKey action = (SetKey) updateAction;
    product.setKey(action.getKey());

    LOG.trace("Updated product: {}.", product);
    LOG.debug("Exit.");
  }
}
