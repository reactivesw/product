package io.reactivesw.product.application.controller;

import static io.reactivesw.product.infrastructure.ProductRouter.PRODUCT_ID;
import static io.reactivesw.product.infrastructure.ProductRouter.PRODUCT_ROOT;
import static io.reactivesw.product.infrastructure.ProductRouter.PRODUCT_WITH_ID;

import io.reactivesw.product.application.model.ProductDraft;
import io.reactivesw.product.application.model.ProductView;
import io.reactivesw.product.application.service.ProductApplication;
import io.reactivesw.product.domain.service.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
   * ProductView Application.
   */
  @Autowired
  private transient ProductApplication productApplication;

  /**
   * The ProductView service.
   */
  @Autowired
  private transient ProductService productService;

  /**
   * Create ProductView.
   *
   * @param productDraft the ProductDraft
   * @return the ProductView
   */
  @PostMapping(PRODUCT_ROOT)
  public ProductView createProduct(@RequestBody @Valid ProductDraft productDraft) {
    LOG.debug("enter createProduct, ProductDraft is : {}", productDraft.toString());

    ProductView result = productApplication.createProduct(productDraft);

    LOG.debug("end createProduct, created ProductView is : {}", result.toString());

    return result;
  }

  /**
   * Gets ProductView by id.
   *
   * @param id the id
   * @return the ProductView
   */
  @GetMapping(PRODUCT_WITH_ID)
  public ProductView getProductById(@PathVariable(value = PRODUCT_ID) String id) {
    LOG.debug("enter getProductById, id is : {}", id);

    ProductView result = productApplication.getProductById(id);

    LOG.debug("end getProductById, get product is : {}", result.toString());

    return result;
  }

  /**
   * Gets product by slug.
   *
   * @param slug the slug
   * @return the product by slug
   */
  @GetMapping(PRODUCT_ROOT)
  public ProductView getProductBySlug(@RequestParam String slug) {
    LOG.debug("enter getProductBySlug, slug is : {}", slug);

    ProductView result = productService.getProductBySlug(slug);

    LOG.debug("end getProductBySlug, get product : {}", result.toString());

    return result;
  }

  /**
   * Delete product by id.
   *
   * @param id      the id
   * @param version the version
   */
  @DeleteMapping(PRODUCT_WITH_ID)
  public void deleteProductById(@PathVariable(value = PRODUCT_ID) String id,
                                @RequestParam Integer version) {
    LOG.debug("enter deleteProductById, id is {}, version is {}", id, version);

    productService.deleteProduct(id, version);

    LOG.debug("end deleteProductById, id is {}, version is {}", id, version);
  }
}
