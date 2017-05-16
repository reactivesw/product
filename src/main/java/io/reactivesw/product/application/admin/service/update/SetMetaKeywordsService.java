package io.reactivesw.product.application.admin.service.update;

import io.reactivesw.model.Updater;
import io.reactivesw.product.application.admin.model.actions.SetMetaKeywords;
import io.reactivesw.product.application.model.mapper.LocalizedStringMapper;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * The type Set meta keywords service.
 */
@Service(UpdateActionUtils.SET_META_KEYWORDS)
public class SetMetaKeywordsService implements Updater<Product, UpdateAction> {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(SetMetaKeywordsService.class);

  /**
   * Set meta keywords.
   *
   * @param product the product entity
   * @param updateAction the SetMetaKeywords action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    LOG.debug("Enter. ProductId: {}, update action: {}.", product.getId(), updateAction);

    SetMetaKeywords action = (SetMetaKeywords) updateAction;
    product.getMasterData().getStaged().setMetaKeywords(
        LocalizedStringMapper.toEntityDefaultNew(action.getMetaKeywords()));
    product.getMasterData().setStagedChanged(true);

    LOG.trace("Updated product: {}.", product);
    LOG.debug("Exit.");
  }
}
