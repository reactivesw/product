package io.reactivesw.product.infrastructure.util;

import io.reactivesw.product.application.model.CategoryProductView;
import io.reactivesw.product.application.model.DetailProductView;
import io.reactivesw.product.application.model.InventoryEntryView;
import io.reactivesw.product.application.model.mapper.ProductVariantAvailabilityMapper;

import java.util.List;

/**
 * Created by Davis on 16/12/22.
 */
public final class InventoryUtils {
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
  public static List<CategoryProductView> mergeInventoryToCategoryProducts(
      List<InventoryEntryView> inventoryEntryViews,
      List<CategoryProductView> categoryProductViews) {

    categoryProductViews.stream().forEach(
        categoryProductView -> {
          categoryProductView.setAvailable(getAvailable(categoryProductView.getSku(),
              inventoryEntryViews));
        }
    );

    return categoryProductViews;
  }

  /**
   * Merge inventory to detail product detail product view.
   *
   * @param inventoryEntryViews the inventory entry views
   * @param detailProductView   the detail product view
   * @return the detail product view
   */
  public static DetailProductView mergeInventoryToDetailProduct(List<InventoryEntryView>
                                                                    inventoryEntryViews,
                                                                DetailProductView
                                                                    detailProductView) {
    detailProductView.getMasterVariant().setAvailable(getAvailable(detailProductView
        .getMasterVariant().getSku(), inventoryEntryViews));

    detailProductView.getVariants().stream().forEach(
        variant -> {
          variant.setAvailable(getAvailable(variant.getSku(), inventoryEntryViews));
        }
    );

    return detailProductView;
  }

  /**
   * Gets available.
   *
   * @param sku              the sku
   * @param inventoryEntries the inventory entries
   * @return the available
   */
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
