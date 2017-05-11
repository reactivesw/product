package io.reactivesw.product.application.admin.service.update;

import io.reactivesw.model.Updater;
import io.reactivesw.product.application.admin.model.actions.SetSku;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.domain.model.ProductVariant;
import io.reactivesw.product.domain.service.SkuService;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;
import io.reactivesw.product.infrastructure.util.VariantUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Set sku service.
 */
@Service(UpdateActionUtils.SET_SKU)
public class SetSkuService implements Updater<Product, UpdateAction> {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(SetSkuService.class);

  /**
   * The Sku service.
   */
  @Autowired
  private transient SkuService skuService;

  /**
   * Set sku.
   *
   * @param product the product entity
   * @param updateAction the SetSku action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    LOG.debug("Enter. ProductId: {}, update action: {}.", product.getId(), updateAction);

    SetSku action = (SetSku) updateAction;

    skuService.validateSkuName(action.getSku());

    ProductVariant variant = VariantUtils.getStagedVariant(product, action.getVariantId());

    variant.setSku(action.getSku());
    // TODO: 17/5/10 what about there is inventory entry associated with this sku?
    // 1. change sku name and send an event about it.
    // 2. refuse this change.

    product.getMasterData().setStagedChanged(true);

    LOG.trace("Updated product: {}.", product);
    LOG.debug("Exit.");
  }
}
