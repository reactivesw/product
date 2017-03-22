package io.reactivesw.product.application.controller;

import static io.reactivesw.product.infrastructure.ProductRouter.CATEGORY_PRODUCT_ROOT;
import static io.reactivesw.product.infrastructure.ProductRouter.PRODUCT_ID;
import static io.reactivesw.product.infrastructure.ProductRouter.PRODUCT_WITH_ID;

import io.reactivesw.product.application.model.CategoryProductView;
import io.reactivesw.product.application.model.PagedQueryResult;
import io.reactivesw.product.application.model.ProductView;
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
   * Gets ProductViewOld by id.
   *
   * @param id the id
   * @return the ProductViewOld
   */
  @GetMapping(PRODUCT_WITH_ID)
  public ProductView getProductById(@PathVariable(value = PRODUCT_ID) String id) {
    LOG.debug("enter getProductById, id is : {}", id);

    ProductView result = productApplication.getProductById(id);

    LOG.debug("end getProductById, get product is : {}", result.toString());

    return result;
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
}
