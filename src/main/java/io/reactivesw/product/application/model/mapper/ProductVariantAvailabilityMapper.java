package io.reactivesw.product.application.model.mapper;

import io.reactivesw.product.application.model.InventoryEntryView;
import io.reactivesw.product.application.model.ProductVariantAvailabilityView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Davis on 16/12/22.
 */
public final class ProductVariantAvailabilityMapper {

  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(ProductVariantAvailabilityMapper.class);

  /**
   * Instantiates a new ProductView variant availability mapper.
   */
  private ProductVariantAvailabilityMapper() {
  }

  public static ProductVariantAvailabilityView toModel(InventoryEntryView entry) {
    LOG.debug("enter toModel, inventory entry is : {}", entry.toString());
    ProductVariantAvailabilityView model = new ProductVariantAvailabilityView();

    model.setRestockableInDays(entry.getRestockableInDays());
    model.setAvailableQuantity(entry.getAvailableQuantity());
    boolean isOnStock = getAvailable(entry);
    model.setIsOnStock(isOnStock);
    // TODO: 16/12/22 set channel
    model.setChannels(null);

    LOG.debug("end toModel, model is : {}", model.toString());
    return model;
  }

  /**
   * get inventory available.
   * @param entry the InventoryEntryView
   * @return boolean
   */
  public static boolean getAvailable(InventoryEntryView entry) {
    return entry.getAvailableQuantity() > 0 ? true : false;
  }

}
