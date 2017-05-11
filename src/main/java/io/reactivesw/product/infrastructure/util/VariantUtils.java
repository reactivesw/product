package io.reactivesw.product.infrastructure.util;

import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.domain.model.ProductData;
import io.reactivesw.product.domain.model.ProductVariant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Predicate;

/**
 * The type Variant utils.
 */
public final class VariantUtils {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(VariantUtils.class);

  /**
   * The constant MASTER_VARIANT_ID.
   */
  public static final Integer MASTER_VARIANT_ID = 1;

  /**
   * The constant VARIANT_ID_INTERVAL.
   */
  public static final Integer VARIANT_ID_INTERVAL = 1;

  /**
   * Instantiates a new Variant utils.
   */
  private VariantUtils() {
  }

  /**
   * Create new variant id in staged integer.
   *
   * @param product the product
   * @return the integer
   */
  public static Integer createNewVariantIdInStaged(Product product) {
    Integer result = null;

    ProductData staged = product.getMasterData().getStaged();
    Integer biggestVariantId = getBiggestVariantId(staged);
    result = biggestVariantId + VARIANT_ID_INTERVAL;

    return result;
  }


  /**
   * Gets staged variant.
   *
   * @param product the product
   * @param variantId the variant id
   * @return the staged variant
   */
  public static ProductVariant getStagedVariant(Product product, Integer variantId) {
    ProductVariant result = null;
    ProductData staged = product.getMasterData().getStaged();
    result = getProductVariant(product, variantId, staged);

    return result;
  }

  /**
   * Gets current variant.
   *
   * @param product the product
   * @param variantId the variant id
   * @return the current variant
   */
  public static ProductVariant getCurrentVariant(Product product, Integer variantId) {
    ProductVariant result = null;
    ProductData current = product.getMasterData().getCurrent();
    result = getProductVariant(product, variantId, current);

    return result;
  }

  /**
   * Gets product variant.
   *
   * @param product the product
   * @param variantId the variant id
   * @param productData the productData
   * @return the product variant
   */
  private static ProductVariant getProductVariant(Product product, Integer variantId,
      ProductData productData) {
    ProductVariant result = null;
    if (variantId.equals(MASTER_VARIANT_ID)) {
      result = productData.getMasterVariant();
    } else {
      Predicate<ProductVariant> predicate = productVariant ->
          productVariant.getId().equals(variantId);
      result = productData.getVariants().stream().filter(predicate).findAny().orElse(null);
    }

    if (result == null) {
      LOG.debug("Can not find variant: {} in product: {}.", variantId, product.getId());
    }
    return result;
  }

  /**
   * Get the biggest variant id from ProductData.
   *
   * @param productData the ProductData entity
   * @return Integer
   */
  private static Integer getBiggestVariantId(ProductData productData) {
    Integer result = productData.getMasterVariant().getId();
    if (productData.getVariants() != null && !productData.getVariants().isEmpty()) {
      result = productData.getVariants().stream().map(
          ProductVariant::getId).max(Integer::compare)
          .get();
    }

    return result;
  }
}
