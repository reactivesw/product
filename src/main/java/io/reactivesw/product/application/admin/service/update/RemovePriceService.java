package io.reactivesw.product.application.admin.service.update;

import io.reactivesw.model.Updater;
import io.reactivesw.product.application.admin.model.actions.RemovePrice;
import io.reactivesw.product.domain.model.Price;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.domain.model.ProductVariant;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;
import io.reactivesw.product.infrastructure.util.VariantUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

/**
 * The type Remove price service.
 */
@Service(UpdateActionUtils.REMOVE_PRICE)
public class RemovePriceService implements Updater<Product, UpdateAction> {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(RemovePriceService.class);

  /**
   * Remove price.
   *
   * @param product the product entity
   * @param updateAction the RemovePrice action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    LOG.debug("Enter. ProductId: {}, update action: {}.", product.getId(), updateAction);

    RemovePrice action = (RemovePrice) updateAction;
    ProductVariant variant = VariantUtils.getStagedVariant(product, action.getVariantId());

    String priceId = action.getPriceId();

    if (variant.getPrices() == null || variant.getPrices().isEmpty()) {
      LOG.debug("Price is null, can not remove this price: {}.", priceId);
    } else {
      Predicate<Price> predicate = price -> price.getId().equals(priceId);
      variant.getPrices().removeIf(predicate);
    }

    product.getMasterData().setStagedChanged(true);

    LOG.trace("Updated product: {}.", product);
    LOG.debug("Exit.");
  }
}
