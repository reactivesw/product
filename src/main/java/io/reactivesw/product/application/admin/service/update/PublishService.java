package io.reactivesw.product.application.admin.service.update;

import io.reactivesw.model.Updater;
import io.reactivesw.product.application.admin.model.actions.Publish;
import io.reactivesw.product.application.admin.model.mapper.ProductDataMapper;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * The type PublishService.
 */
@Service(UpdateActionUtils.PUBLISH)
public class PublishService implements Updater<Product, UpdateAction> {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(PublishService.class);

  /**
   * Publish.
   *
   * @param product the product entity
   * @param updateAction the Publish action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    LOG.debug("Enter. ProductId: {}, update action: {}.", product.getId(), updateAction);

    if (updateAction instanceof Publish) {
      product.getMasterData().setCurrent(
          ProductDataMapper.copyFrom(product.getMasterData().getStaged()));
      product.getMasterData().setStagedChanged(false);
      product.getMasterData().setPublished(true);
    }

    LOG.trace("Updated product: {}.", product);
    LOG.debug("Exit.");
  }
}
