package io.reactivesw.product.application.admin.service.update;

import io.reactivesw.model.Updater;
import io.reactivesw.product.application.admin.model.actions.SetName;
import io.reactivesw.product.application.model.mapper.LocalizedStringMapper;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * The type Set name service.
 */
@Service(UpdateActionUtils.SET_NAME)
public class SetNameService implements Updater<Product, UpdateAction> {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(SetNameService.class);

  /**
   * Set name.
   *
   * @param product the product entity
   * @param updateAction the SetName action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    LOG.debug("Enter. ProductId: {}, update action: {}.", product.getId(), updateAction);

    SetName action = (SetName) updateAction;
    product.getMasterData().getStaged().setName(
        LocalizedStringMapper.toEntityDefaultNew(action.getName()));
    product.getMasterData().setStagedChanged(true);

    LOG.trace("Updated product: {}.", product);
    LOG.debug("Exit.");
  }
}
