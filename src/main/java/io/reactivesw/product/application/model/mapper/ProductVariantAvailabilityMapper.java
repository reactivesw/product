package io.reactivesw.product.application.model.mapper;

import io.reactivesw.product.application.model.InventoryEntryView;

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
   * Instantiates a new Product variant availability mapper.
   */
  private ProductVariantAvailabilityMapper() {
  }

  /**
   * get inventory available.
   *
   * @param entry the InventoryEntryView
   * @return boolean available
   */
  public static boolean getAvailable(InventoryEntryView entry) {
    return entry.getAvailableQuantity() > 0 ? true : false;
  }

}
