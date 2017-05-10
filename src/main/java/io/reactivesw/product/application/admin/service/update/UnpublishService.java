package io.reactivesw.product.application.admin.service.update;

import io.reactivesw.model.Updater;
import io.reactivesw.product.application.admin.model.actions.Unpublish;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * The type Unpublish service.
 */
@Service(UpdateActionUtils.UNPUBLISH)
public class UnpublishService implements Updater<Product, UpdateAction> {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(UnpublishService.class);

  /**
   * Un publish.
   *
   * @param product the product entity
   * @param updateAction the Unpublish action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    LOG.debug("Enter. ProductId: {}, update action: {}.", product.getId(), updateAction);

    if (updateAction instanceof Unpublish) {
      product.getMasterData().setCurrent(null);
    }

    LOG.trace("Updated product: {}.", product);
    LOG.debug("Exit.");
  }
}
