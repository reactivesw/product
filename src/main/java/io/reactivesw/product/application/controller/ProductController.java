package io.reactivesw.product.application.controller;

import static io.reactivesw.product.infrastructure.ProductRouter.CATEGORY_PRODUCT_ROOT;
import static io.reactivesw.product.infrastructure.ProductRouter.DETAIL_PRODUCT_SKU;
import static io.reactivesw.product.infrastructure.ProductRouter.SKU;

import io.reactivesw.product.application.model.CategoryProductView;
import io.reactivesw.product.application.model.DetailProductView;
import io.reactivesw.product.application.model.PagedQueryResult;
import io.reactivesw.product.application.model.QueryConditions;
import io.reactivesw.product.application.service.ProductApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Davis on 16/12/14.
 */
@RestController
public class ProductController {
  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

  /**
   * Product Application.
   */
  private transient ProductApplication productApplication;

  /**
   * Instantiates a new Product controller.
   *
   * @param productApplication the product application
   */
  public ProductController(ProductApplication productApplication) {
    this.productApplication = productApplication;
  }

  /**
   * Query product projections list.
   * <p>
   * queryconditions example :
   * "where"="categoryId:\"c42e4efb-7de7-4f3d-adac-554b84bda1b5\""
   *
   * @param queryConditions the query conditions
   * @return the list
   */
// TODO: 16/12/21 only for query product by category now
  @GetMapping(CATEGORY_PRODUCT_ROOT)
  public PagedQueryResult<CategoryProductView> queryProductProjections(QueryConditions
                                                                           queryConditions) {
    LOG.debug("enter queryProductProjections, query conditions is : {}",
        queryConditions.toString());

    PagedQueryResult<CategoryProductView> result = new PagedQueryResult<>();
    List<CategoryProductView> productProjections =
        productApplication.queryCategoryProducts(queryConditions);
    result.setCount(productProjections.size());
    result.setResults(productProjections);
    LOG.debug("end queryProductProjections, category product number is : {}",
        productProjections.size());

    return result;
  }

  /**
   * Gets detail product by sku.
   *
   * @param sku the sku
   * @return the detail product by sku
   */
  @GetMapping(DETAIL_PRODUCT_SKU)
  public DetailProductView getDetailProductBySku(@PathVariable(SKU) String sku) {
    LOG.debug("enter getDetailProductBySku, sku is : {}", sku);

    DetailProductView result = productApplication.getDetailProductBySku(sku);

    LOG.debug("end getDetailProductBySku, result is : {}", result.toString());

    return result;
  }
}