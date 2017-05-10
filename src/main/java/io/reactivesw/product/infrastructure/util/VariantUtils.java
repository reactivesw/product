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
   * Instantiates a new Variant utils.
   */
  private VariantUtils() {
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
    if (variantId.equals(1)) {
      result = staged.getMasterVariant();
    } else {
      Predicate<ProductVariant> predicate = productVariant -> productVariant.getId()
          .equals(variantId);
      result = staged.getVariants().stream().filter(predicate).findAny().orElse(null);
    }

    if (result == null) {
      LOG.debug("Can not find variant: {} in product: {}.", variantId, product.getId());
    }

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
    if (variantId.equals(1)) {
      result = current.getMasterVariant();
    } else {
      Predicate<ProductVariant> predicate = productVariant -> productVariant.getId()
          .equals(variantId);
      result = current.getVariants().stream().filter(predicate).findAny().orElse(null);
    }

    if (result == null) {
      LOG.debug("Can not find variant: {} in product: {}.", variantId, product.getId());
    }

    return result;
  }
}
