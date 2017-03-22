package io.reactivesw.product.infrastructure;

/**
 * Created by umasuo on 16/12/20.
 */
public final class ProductRouter {

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

  public static final String DETAIL_PRODUCT = "DetailProducts";

  /**
   * The constant DETAIL_PRODUCT_SKU.
   */
  public static final String DETAIL_PRODUCT_SKU = PRODUCT_ROOT + DETAIL_PRODUCT + "/{" + SKU + "}";

  /**
   * The constant PRODUCT_HEALTH_CHECK.
   */
  public static final String PRODUCT_HEALTH_CHECK = PRODUCT_ROOT + "health";
}
