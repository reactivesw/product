package io.reactivesw.product.application.admin.service.update;

import io.reactivesw.model.Updater;
import io.reactivesw.product.application.admin.model.actions.ChangePrice;
import io.reactivesw.product.application.admin.model.mapper.PriceMapper;
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
 * The type Change price service.
 */
@Service(UpdateActionUtils.CHANGE_PRICE)
public class ChangePriceService implements Updater<Product, UpdateAction> {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(ChangePriceService.class);

  /**
   * Change price.
   *
   * @param product the product entity
   * @param updateAction the ChangePrice action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    LOG.debug("Enter. ProductId: {}, update action: {}.", product.getId(), updateAction);

    ChangePrice action = (ChangePrice) updateAction;
    ProductVariant variant = VariantUtils.getStagedVariant(product, action.getVariantId());

    String priceId = action.getPriceId();

    if (variant.getImages() == null || variant.getImages().isEmpty()) {
      LOG.debug("Price is null, can not change price: {}.", priceId);
      LOG.debug("Exit.");
      return;
    }

    Predicate<Price> predicate = price -> price.getId().equals(priceId);
    Price price = variant.getPrices().stream().filter(predicate).findAny().orElse(null);

    if (price == null) {
      LOG.debug("Can not find price: {}.", priceId);
    } else {
      PriceMapper.merge(action.getPrice(), price);
      product.getMasterData().setStagedChanged(true);
    }

    LOG.trace("Updated product: {}.", product);
    LOG.debug("Exit.");
  }
}
