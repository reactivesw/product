package io.reactivesw.product.infrastructure;

/**
 * Created by Davis on 16/12/21.
 */
public final class ProductProjectionRouter {
  /**
   * product merchant root.
   */

  public static final String PRODUCT_PROJECTION_ROOT = "/projections";

  /**
   * product merchant id.
   */
  public static final String PRODUCT_PROJECTION_ID = "productProjectId";

  /**
   * product merchant with id.
   */
  public static final String PRODUCT_PROJECTION_WITH_ID = PRODUCT_PROJECTION_ROOT
      + "/{" + PRODUCT_PROJECTION_ID + "}";
}
