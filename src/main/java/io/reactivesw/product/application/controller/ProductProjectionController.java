package io.reactivesw.product.application.controller;

import static io.reactivesw.product.infrastructure.ProductProjectionRouter.PRODUCT_PROJECTION_ROOT;

import io.reactivesw.product.application.model.PagedQueryResult;
import io.reactivesw.product.application.model.ProductProjectionView;
import io.reactivesw.product.application.model.QueryConditions;
import io.reactivesw.product.application.service.ProductApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Davis on 16/12/21.
 */
@RestController
public class ProductProjectionController {
  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(ProductProjectionController.class);

  /**
   * The ProductView controller.
   */
  @Autowired
  private transient ProductApplication productApplication;

  /**
   * Query product projections list.
   * <p>
   * queryconditions example :
   * {"where":"categoryId:\"c42e4efb-7de7-4f3d-adac-554b84bda1b5\""}
   *
   * @param queryConditions the query conditions
   * @return the list
   */
  // TODO: 16/12/21 only for query product by category now
  @GetMapping(PRODUCT_PROJECTION_ROOT)
  public PagedQueryResult<ProductProjectionView> queryProductProjections(QueryConditions
                                                                             queryConditions) {
    LOG.debug("enter queryProductProjections, query conditions is : {}",
        queryConditions.toString());

    PagedQueryResult<ProductProjectionView> result = new PagedQueryResult<>();
    List<ProductProjectionView> productProjections =
        productApplication.queryProductProject(queryConditions);
    result.setTotal(productProjections.size());
    result.setResults(productProjections);
    LOG.debug("end queryProductProjections, product projections number is : {}",
        productProjections.size());

    return result;
  }
}
