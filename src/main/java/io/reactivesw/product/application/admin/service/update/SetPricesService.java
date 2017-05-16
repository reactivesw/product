package io.reactivesw.product.application.admin.service.update;

import io.reactivesw.model.Updater;
import io.reactivesw.product.application.admin.model.actions.SetPrices;
import io.reactivesw.product.application.admin.model.mapper.PriceMapper;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.domain.model.ProductVariant;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;
import io.reactivesw.product.infrastructure.util.VariantUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * The type Set prices service.
 */
@Service(UpdateActionUtils.SET_PRICES)
public class SetPricesService implements Updater<Product, UpdateAction> {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(SetPricesService.class);

  /**
   * Set prices.
   *
   * @param product the product entity
   * @param updateAction the SetPrices action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    LOG.debug("Enter. ProductId: {}, update action: {}.", product.getId(), updateAction);

    SetPrices action = (SetPrices) updateAction;
    ProductVariant variant = VariantUtils.getStagedVariant(product, action.getVariantId());

    variant.setPrices(PriceMapper.toEntity(action.getPrices()));
    product.getMasterData().setStagedChanged(true);

    LOG.trace("Updated product: {}.", product);
    LOG.debug("Exit.");
  }
}
