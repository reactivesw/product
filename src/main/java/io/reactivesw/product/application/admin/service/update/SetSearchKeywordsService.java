package io.reactivesw.product.application.admin.service.update;

import io.reactivesw.model.Updater;
import io.reactivesw.product.application.admin.model.actions.SetSearchKeywords;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * The type Set search keywords service.
 */
@Service(UpdateActionUtils.SET_SEARCH_KEYWORDS)
public class SetSearchKeywordsService implements Updater<Product, UpdateAction> {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(SetSearchKeywordsService.class);

  /**
   * Set search keywords.
   *
   * @param product the product entity
   * @param updateAction the SetSearchKeywords action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    LOG.debug("Enter. ProductId: {}, update action: {}.", product.getId(), updateAction);

    SetSearchKeywords action = (SetSearchKeywords) updateAction;
    product.getMasterData().getStaged().setSearchKeyWords(
        action.getSearchKeywords().getText());
    product.getMasterData().setStagedChanged(true);

    LOG.trace("Updated product: {}.", product);
    LOG.debug("Exit.");
  }
}
