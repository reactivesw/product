package io.reactivesw.product.infrastructure;

/**
 * Created by umasuo on 16/12/20.
 */
public final class Router {

  /**
   * product root.
   */
  public static final String PRODUCT_ROOT = "/";

  /**
   * product id.
   */
  public static final String PRODUCT_ID = "productId";

  /**
   * The constant CATEGORY_PRODUCT_ROOT.
   */
  public static final String CATEGORY_PRODUCT_ROOT = PRODUCT_ROOT + "CategoryProducts";

  /**
   * The constant SKU.
   */
  public static final String SKU = "Sku";

  /**
   * The constant DETAIL_PRODUCT.
   */
  public static final String DETAIL_PRODUCT = "DetailProducts";

  /**
   * The constant DETAIL_PRODUCT_SKU.
   */
  public static final String DETAIL_PRODUCT_SKU = PRODUCT_ROOT + DETAIL_PRODUCT + "/{" + SKU + "}";

  /**
   * The constant CART_PRODUCT.
   */
  public static final String CART_PRODUCT = "CartProducts";

  /**
   * The constant VARIANT_ID.
   */
  public static final String VARIANT_ID = "variantId";

  /**
   * The constant CART_PRODUCT_VARIANT_PATH.
   */
  public static final String CART_PRODUCT_VARIANT_PATH =
      PRODUCT_ROOT + CART_PRODUCT + "/{" + PRODUCT_ID + "}";

  /**
   * The constant PRODUCT_HEALTH_CHECK.
   */
  public static final String PRODUCT_HEALTH_CHECK = PRODUCT_ROOT + "health";

  /**
   * The constant PRODUCT_WITH_ID.
   */
  public static final String PRODUCT_WITH_ID = PRODUCT_ROOT + "/{" + PRODUCT_ID + "}";
}
