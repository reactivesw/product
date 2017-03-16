package io.reactivesw.product.infrastructure.util;

import io.reactivesw.product.application.model.InventoryEntryView;
import io.reactivesw.product.application.model.ProductVariantView;
import io.reactivesw.product.application.model.ProductView;
import io.reactivesw.product.application.model.ProductDataView;
import io.reactivesw.product.application.model.mapper.ProductVariantAvailabilityMapper;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Davis on 16/12/22.
 */
public final class ProductInventoryUtils {

  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(ProductInventoryUtils.class);

  /**
   * Instantiates a new ProductView inventory update.
   */
  private ProductInventoryUtils() {}

  /**
   * Merge inventory entry to product.
   *
   * @param inventoryEntries the inventory entries
   * @param product          the product
   * @return the product
   */
  public static ProductView mergeInventoryEntryToProduct(List<InventoryEntryView> inventoryEntries, ProductView
      product) {
    LOG.debug("enter mergeInventoryEntryToProduct, inventory number is : {}", inventoryEntries.size());
    ProductDataView currentData = product.getMasterData().getCurrent();
    ProductVariantView masterVariant = currentData.getMasterVariant();
    mergeInventoryEntryToVariant(inventoryEntries, masterVariant);

    if (currentData.getVariants() != null && !currentData.getVariants().isEmpty()) {
      currentData.getVariants().parallelStream().forEach(
          variant -> {
            mergeInventoryEntryToVariant(inventoryEntries, variant);
          }
      );
    }

    return product;
  }

  /**
   * merge InventoryEntryView to ProductVariantView.
   * @param inventories the InventoryEntryView
   * @param variant the ProductVariantView
   */
  private static void mergeInventoryEntryToVariant(List<InventoryEntryView> inventories,
                                                   ProductVariantView variant) {
    LOG.debug("enter mergeInventoryEntryToVariant, inventory size is : {}", inventories.size());

    inventories.stream().forEach(
        inventory -> {
          if (StringUtils.equals(inventory.getSku(), variant.getSku())) {
            variant.setAvailability(ProductVariantAvailabilityMapper.toModel(inventory));
          }
        }
    );
  }
}
