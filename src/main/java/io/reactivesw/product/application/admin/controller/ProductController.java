package io.reactivesw.product.application.admin.controller;

import io.reactivesw.product.application.admin.model.ProductDraft;
import io.reactivesw.product.application.admin.model.ProductView;
import io.reactivesw.product.application.admin.service.ProductApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Product controller class for admin web.
 */
@RestController
public class ProductController {
  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

  /**
   * product admin application.
   */
  private transient ProductApplication productApplication;

  /**
   * Instantiates a new Product controller.
   *
   * @param productApplication the product application
   */
  @Autowired
  public ProductController(ProductApplication productApplication) {
    this.productApplication = productApplication;
  }

  /**
   * Create product by draft.
   *
   * @param productDraft product draft
   * @return product view object
   */
  @PostMapping
  public ProductView createProduct(@RequestBody @Valid ProductDraft productDraft) {
    LOG.info("enter. Product draft name: {}.", productDraft.getName());

    ProductView result = productApplication.createProduct(productDraft);

    LOG.info("exit. Product id: {}", result.getId());

    return result;
  }
}
