package io.reactivesw.product.application.admin.service.update;

import io.reactivesw.model.Updater;
import io.reactivesw.product.application.admin.model.actions.SetMetaDescription;
import io.reactivesw.product.application.model.mapper.LocalizedStringMapper;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * The type Set meta description service.
 */
@Service(UpdateActionUtils.SET_META_DESCRIPTION)
public class SetMetaDescriptionService implements Updater<Product, UpdateAction> {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(SetMetaDescriptionService.class);

  /**
   * Set meta description.
   *
   * @param product the product entity
   * @param updateAction the SetMetaDescription action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    LOG.debug("Enter. ProductId: {}, update action: {}.", product.getId(), updateAction);

    SetMetaDescription action = (SetMetaDescription) updateAction;
    product.getMasterData().getStaged().setMetaDescription(
        LocalizedStringMapper.toEntityDefaultNew(action.getMetaDescription()));
    product.getMasterData().setStagedChanged(true);

    LOG.trace("Updated product: {}.", product);
    LOG.debug("Exit.");
  }
}
