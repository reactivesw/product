package io.reactivesw.product.application.admin.service.update;

import com.google.common.collect.Lists;

import io.reactivesw.model.Updater;
import io.reactivesw.product.application.admin.model.actions.AddPrice;
import io.reactivesw.product.application.model.mapper.PriceMapper;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.domain.model.ProductVariant;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;
import io.reactivesw.product.infrastructure.util.VariantUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * The type Add price service.
 */
@Service(UpdateActionUtils.ADD_PRICE)
public class AddPriceService implements Updater<Product, UpdateAction> {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(AddPriceService.class);

  /**
   * Add price.
   *
   * @param product the product entity
   * @param updateAction the AddPrice action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    LOG.debug("Enter. ProductId: {}, update action: {}.", product.getId(), updateAction);

    AddPrice action = (AddPrice) updateAction;
    ProductVariant variant = VariantUtils.getStagedVariant(product, action.getVariantId());

    if (variant.getPrices() == null || variant.getPrices().isEmpty()) {
      variant.setPrices(Lists.newArrayList(PriceMapper.toEntity(action.getPrice())));
    } else {
      variant.getPrices().add(PriceMapper.toEntity(action.getPrice()));
    }

    product.getMasterData().setStagedChanged(true);

    LOG.trace("Updated product: {}.", product);
    LOG.debug("Exit.");
  }
}
