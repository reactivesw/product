package io.reactivesw.product.application.admin.controller;

import io.reactivesw.product.application.admin.model.ProductDraft;
import io.reactivesw.product.application.admin.model.ProductView;
import io.reactivesw.product.application.admin.service.ProductApplication;
import io.reactivesw.product.infrastructure.Router;
import io.reactivesw.product.infrastructure.update.UpdateRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Product controller class for admin web.
 */
@RestController(value = "AdminProductController")
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
    LOG.info("Enter. Product draft name: {}.", productDraft.getName());
    LOG.trace("ProductDraft: {}.", productDraft);

    ProductView result = productApplication.createProduct(productDraft);

    LOG.info("Exit. ProductId: {}.", result.getId());
    LOG.trace("Created Product: {}.", result);

    return result;
  }

  /**
   * Delete product by id.
   *
   * @param id product id
   * @param version product version
   */
  @DeleteMapping(Router.PRODUCT_WITH_ID)
  public void deleteProductById(@PathVariable(value = Router.PRODUCT_ID) String id,
      @RequestParam @NotNull Integer version) {
    LOG.info("Enter. ProductId: {}, version: {}.", id, version);

    productApplication.deleteProductById(id, version);

    LOG.info("Exit. ProductId: {}, version: {}.", id, version);
  }

  /**
   * Update product.
   *
   * @param id the id
   * @param updateRequest the update request
   * @return the product view
   */
  @PutMapping(Router.PRODUCT_WITH_ID)
  public ProductView update(@PathVariable(Router.PRODUCT_ID) String id,
      @RequestBody @Valid UpdateRequest updateRequest) {
    LOG.info("Enter. ProductId: {}, update request: {}.", id, updateRequest);

    ProductView result =
        productApplication.update(id, updateRequest.getVersion(), updateRequest.getActions());

    LOG.trace("Updated product: {}.", result);
    LOG.info("Exit.");
    return result;
  }
}
