package io.reactivesw.product.infrastructure.util;

import io.reactivesw.product.application.model.CategoryProductView;
import io.reactivesw.product.application.model.InventoryEntryView;
import io.reactivesw.product.application.model.mapper.ProductVariantAvailabilityMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Davis on 16/12/22.
 */
public final class InventoryUtils {

  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(InventoryUtils.class);

  /**
   * Instantiates a new ProductViewOld inventory update.
   */
  private InventoryUtils() {
  }

  /**
   * merge inventory to category product.
   *
   * @param inventoryEntryViews  list of InventoryEntryView
   * @param categoryProductViews list of CategoryProductView
   * @return list of CategoryProductView
   */
  public static List<CategoryProductView> mergeInventoryToCategoryProducts
  (List<InventoryEntryView> inventoryEntryViews,
   List<CategoryProductView> categoryProductViews) {

    categoryProductViews.stream().forEach(
        categoryProductView -> {
          categoryProductView.setAvailable(getAvailable(categoryProductView.getSku(),
              inventoryEntryViews));
        }
    );

    return categoryProductViews;
  }


  private static boolean getAvailable(String sku, List<InventoryEntryView> inventoryEntries) {
    boolean result = false;

    InventoryEntryView inventory = inventoryEntries.stream().filter(
        inventoryEntryView -> inventoryEntryView.getSku().equals(sku)
    ).findFirst().get();

    if (inventory != null) {
      result = ProductVariantAvailabilityMapper.getAvailable(inventory);
    }

    return result;
  }
}
