package io.reactivesw.product.application.admin.service;

import io.reactivesw.exception.NotExistException;
import io.reactivesw.product.application.admin.model.ProductDraft;
import io.reactivesw.product.application.admin.model.ProductView;
import io.reactivesw.product.application.model.ProductTypeView;
import io.reactivesw.product.application.service.ProductRestClient;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.domain.service.ProductService;
import io.reactivesw.product.infrastructure.validator.AttributeConstraintValidator;
import io.reactivesw.product.infrastructure.validator.ProductTypeValidator;
import io.reactivesw.product.infrastructure.validator.SkuNameValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Product application class for admin web.
 */
@Service(value = "AdminProductApplication")
public class ProductApplication {

  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(ProductApplication.class);

  /**
   * product domain service.
   */
  private transient ProductService productService;

  /**
   * Product rest client.
   */
  private transient ProductRestClient productRestClient;

  /**
   * Instantiates a new Product application.
   *
   * @param productService the product service
   * @param productRestClient the product rest client
   */
  @Autowired
  public ProductApplication(ProductService productService, ProductRestClient productRestClient) {
    this.productService = productService;
    this.productRestClient = productRestClient;
  }

  /**
   * Create product by draft.
   *
   * @param productDraft product draft
   * @return product view object
   */
  public ProductView createProduct(ProductDraft productDraft) {
    LOG.debug("Enter.");
    LOG.trace("ProductDraft: {}.", productDraft);

    ProductTypeValidator.validateReference(productDraft);
    String productTypeId = productDraft.getProductType().getId();
    ProductTypeView productType = productRestClient.getProductType(productTypeId);

    if (productType == null) {
      LOG.debug("Can not find product type by id : {}", productTypeId);
      throw new NotExistException("ProductTypeView Not Found");
    }

    AttributeConstraintValidator.validate(productType.getAttributes(), productDraft);
    SkuNameValidator.validate(productDraft);

    ProductView result = productService.createProduct(productDraft);

    LOG.debug("Exit.");
    LOG.trace("created Product: {}.", result);

    return result;
  }

  /**
   * Delete product by id.
   *
   * @param id id
   * @param version version
   */
  public void deleteProductById(String id, Integer version) {
    LOG.debug("Enter. ProductId: {}, version: {}.", id, version);
    Product entity = productService.getProductById(id);
    productService.validateVersion(version, entity);
    productService.deleteProductById(id);
    LOG.trace("Deleted product: {}.", entity);
    LOG.debug("Exit. Deleted productId: {}, version: {}.", id, version);
  }

}
