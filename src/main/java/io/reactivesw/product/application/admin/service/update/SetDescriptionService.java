package io.reactivesw.product.application.admin.service.update;

import io.reactivesw.model.Updater;
import io.reactivesw.product.application.admin.model.actions.SetDescription;
import io.reactivesw.product.application.model.mapper.LocalizedStringMapper;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * The type Set description service.
 */
@Service(UpdateActionUtils.SET_DESCRIPTION)
public class SetDescriptionService implements Updater<Product, UpdateAction> {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(SetDescriptionService.class);

  /**
   * Set description.
   *
   * @param product the product entity
   * @param updateAction the SetDescription action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    LOG.debug("Enter. ProductId: {}, update action: {}.", product.getId(), updateAction);

    SetDescription action = (SetDescription) updateAction;
    product.getMasterData().getStaged().setDescription(
        LocalizedStringMapper.toEntityDefaultNew(action.getDescription()));
    product.getMasterData().setStagedChanged(true);

    LOG.trace("Updated product: {}.", product);
    LOG.debug("Exit.");
  }
}
