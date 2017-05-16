package io.reactivesw.product.application.admin.service.update;

import io.reactivesw.model.Updater;
import io.reactivesw.product.application.admin.model.actions.SetMetaTitle;
import io.reactivesw.product.application.model.mapper.LocalizedStringMapper;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * The type Set meta title service.
 */
@Service(UpdateActionUtils.SET_META_TITLE)
public class SetMetaTitleService implements Updater<Product, UpdateAction> {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(SetMetaTitleService.class);

  /**
   * Set meta title.
   *
   * @param product the product entity
   * @param updateAction the SetMetaTitle action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    LOG.debug("Enter. ProductId: {}, update action: {}.", product.getId(), updateAction);

    SetMetaTitle action = (SetMetaTitle) updateAction;
    product.getMasterData().getStaged().setMetaTitle(
        LocalizedStringMapper.toEntityDefaultNew(action.getMetaTitle()));
    product.getMasterData().setStagedChanged(true);

    LOG.trace("Updated product: {}.", product);
    LOG.debug("Exit.");
  }
}
