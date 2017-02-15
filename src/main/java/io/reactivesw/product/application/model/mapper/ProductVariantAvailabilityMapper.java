package io.reactivesw.product.application.model.mapper;

import io.reactivesw.product.application.model.InventoryEntryView;
import io.reactivesw.product.application.model.ProductVariantAvailabilityView;

/**
 * Created by Davis on 16/12/22.
 */
public final class ProductVariantAvailabilityMapper {
  /**
   * Instantiates a new ProductView variant availability mapper.
   */
  private ProductVariantAvailabilityMapper() {
  }

  public static ProductVariantAvailabilityView toModel(InventoryEntryView entry) {
    ProductVariantAvailabilityView model = new ProductVariantAvailabilityView();

    model.setRestockableInDays(entry.getRestockableInDays());
    model.setAvailableQuantity(entry.getAvailableQuantity());
    boolean isOnStock = entry.getAvailableQuantity() > 0 ? true : false;
    model.setIsOnStock(isOnStock);
    // TODO: 16/12/22 set channel
    model.setChannels(null);

    return model;
  }
}
