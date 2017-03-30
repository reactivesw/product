package io.reactivesw.product.infrastructure.util;

import com.google.common.collect.Lists;

import io.reactivesw.product.application.model.CategoryProductView;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.domain.model.ProductCatalogData;
import io.reactivesw.product.domain.model.ProductData;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Davis on 16/12/23.
 */
public final class SkuUtils {
  /**
   * Instantiates a new ProductViewOld update.
   */
  private SkuUtils() {
  }

  /**
   * get all CategoryProductView's sku name.
   *
   * @param productViews list of CategoryProductView
   * @return list of String
   */
  public static List<String> getCategoryProductSkuNames(List<CategoryProductView> productViews) {
    List<String> result = productViews.stream().map(
        productView -> {
          return productView.getSku();
        }).collect(Collectors.toList());
    return result;
  }

  /**
   * Gets sku names.
   *
   * @param product the product
   * @return the sku names
   */
  public static List<String> getSkuNames(Product product) {
    List<String> skuNames = Lists.newArrayList();

    ProductCatalogData masterData = product.getMasterData();
    ProductData currentData = masterData.getCurrent();

    skuNames.addAll(getSkuNames(currentData));

    return ListUtils.removeDuplicateString(skuNames);
  }

  /**
   * Gets sku names.
   *
   * @param productData the product data
   * @return the sku names
   */
  public static List<String> getSkuNames(ProductData productData) {
    List<String> skuNames = Lists.newArrayList();

    if (productData.getMasterVariant() != null) {
      skuNames.add(productData.getMasterVariant().getSku());
    }

    if (productData.getVariants() != null) {
      skuNames.addAll(productData.getVariants().parallelStream().map(
          variant -> {
            return variant.getSku();
          }).collect(Collectors.toList()));
    }

    return skuNames;
  }
}
