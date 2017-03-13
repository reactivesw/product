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
   * product with id.
   */
  public static final String PRODUCT_WITH_ID = PRODUCT_ROOT + "/{" + PRODUCT_ID + "}";


  /**
   * The constant PRODUCT_SLUG.
   */
  public static final String PRODUCT_SLUG = "productSlug";

  /**
   * The constant PRODUCT_WITH_SLUG.
   */
  public static final String PRODUCT_WITH_SLUG = PRODUCT_ROOT + "/{" + PRODUCT_SLUG + "}";

  /**
   * The constant PRODUCT_HEALTH_CHECK.
   */
  public static final String PRODUCT_HEALTH_CHECK = PRODUCT_ROOT + "health";
}
