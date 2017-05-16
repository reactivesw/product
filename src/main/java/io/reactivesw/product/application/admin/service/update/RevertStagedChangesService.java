package io.reactivesw.product.application.admin.service.update;

import io.reactivesw.model.Updater;
import io.reactivesw.product.application.admin.model.actions.RevertStagedChanges;
import io.reactivesw.product.application.admin.model.mapper.ProductDataMapper;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * The type Revert staged changes service.
 */
@Service(UpdateActionUtils.REVERT_STAGED_CHANGES)
public class RevertStagedChangesService implements Updater<Product, UpdateAction> {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(RevertStagedChangesService.class);

  /**
   * Revert staged changes.
   *
   * @param product the product entity
   * @param updateAction the RevertStagedChanges action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    LOG.debug("Enter. ProductId: {}, update action: {}.", product.getId(), updateAction);

    if (updateAction instanceof RevertStagedChanges) {
      product.getMasterData().setStaged(
          ProductDataMapper.copyFrom(product.getMasterData().getCurrent()));
      product.getMasterData().setStagedChanged(false);
    }

    LOG.trace("Updated product: {}.", product);
    LOG.debug("Exit.");
  }
}
