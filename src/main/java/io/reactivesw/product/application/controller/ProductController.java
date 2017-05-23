package io.reactivesw.product.application.controller;

import static io.reactivesw.product.infrastructure.Router.CART_PRODUCT_VARIANT_PATH;
import static io.reactivesw.product.infrastructure.Router.CATEGORY_PRODUCT_ROOT;
import static io.reactivesw.product.infrastructure.Router.DETAIL_PRODUCT_SKU;
import static io.reactivesw.product.infrastructure.Router.PRODUCT_ID;
import static io.reactivesw.product.infrastructure.Router.SEARCH;
import static io.reactivesw.product.infrastructure.Router.SEARCH_WORDS;
import static io.reactivesw.product.infrastructure.Router.SKU;
import static io.reactivesw.product.infrastructure.Router.VARIANT_ID;

import io.reactivesw.product.application.model.CartProductView;
import io.reactivesw.product.application.model.CategoryProductView;
import io.reactivesw.product.application.model.DetailProductView;
import io.reactivesw.product.application.model.PagedQueryResult;
import io.reactivesw.product.application.service.ProductApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Davis on 16/12/14.
 */
@RestController(value = "CustomerProductController")
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
   * queryconditions example :
   * "where"="categoryId:\"c42e4efb-7de7-4f3d-adac-554b84bda1b5\""
   * TODO: 16/12/21 only for query product by category now
   *
   * @param categoryId the query conditions
   * @return the list
   */
  @GetMapping(CATEGORY_PRODUCT_ROOT)
  public PagedQueryResult<CategoryProductView> queryCategoryProducts(@RequestParam("categoryId")
      String categoryId) {
    LOG.debug("enter queryCategoryProducts, category id  is : {}", categoryId);

    PagedQueryResult<CategoryProductView> result = new PagedQueryResult<>();
    List<CategoryProductView> categoryProductViews =
        productApplication.queryCategoryProducts(categoryId);
    result.setCount(categoryProductViews.size());
    result.setResults(categoryProductViews);
    LOG.debug("end queryCategoryProducts, category product number is : {}",
        categoryProductViews.size());

    return result;
  }

  /**
   * Search category products.
   *
   * @param searchWords the search words
   * @return the paged query result
   */
  @GetMapping(SEARCH)
  public PagedQueryResult<CategoryProductView> searchCategoryProducts(@RequestParam(SEARCH_WORDS)
      String searchWords) {
    LOG.info("Enter. SearchWords: {}.", searchWords);

    List<CategoryProductView> categoryProductViews = productApplication.searchProduct(searchWords);

    PagedQueryResult<CategoryProductView> result = new PagedQueryResult<>();
    result.setCount(categoryProductViews.size());
    result.setResults(categoryProductViews);

    LOG.info("Exit. Result count: {}.", categoryProductViews.size());

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

  /**
   * Gets cart product by id.
   *
   * @param productId the product id
   * @param variantId the variant id
   * @return the cart product by id
   */
  @GetMapping(CART_PRODUCT_VARIANT_PATH)
  public CartProductView getCartProductById(@PathVariable(PRODUCT_ID) String productId,
      @RequestParam(VARIANT_ID) Integer variantId) {

    LOG.debug("Enter. ProductId: {}, VariantId: {}.", productId,
        variantId);

    CartProductView result = productApplication.getCartProductById(productId, variantId);

    LOG.debug("Exit. CartProduct: {}.", result);

    return result;
  }
}