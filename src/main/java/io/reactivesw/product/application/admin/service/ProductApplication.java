package io.reactivesw.product.application.admin.service;

import io.reactivesw.exception.NotExistException;
import io.reactivesw.product.application.admin.model.ProductDraft;
import io.reactivesw.product.application.admin.model.ProductView;
import io.reactivesw.product.application.admin.model.mapper.ProductMapper;
import io.reactivesw.product.application.model.ProductTypeView;
import io.reactivesw.product.application.service.ProductRestClient;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.domain.service.ProductService;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.update.UpdaterService;
import io.reactivesw.product.infrastructure.validator.AttributeConstraintValidator;
import io.reactivesw.product.infrastructure.validator.ProductTypeValidator;
import io.reactivesw.product.infrastructure.validator.SkuNameValidator;
import io.reactivesw.product.infrastructure.validator.SlugValidator;
import io.reactivesw.product.infrastructure.validator.VersionValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
   * Product updater service.
   */
  private transient UpdaterService updaterService;

  /**
   * Instantiates a new Product application.
   *
   * @param productService the product service
   * @param productRestClient the product rest client
   * @param updaterService the updater service
   */
  @Autowired
  public ProductApplication(ProductService productService, ProductRestClient productRestClient,
      UpdaterService updaterService) {
    this.productService = productService;
    this.productRestClient = productRestClient;
    this.updaterService = updaterService;
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

    Product entity = ProductMapper.toEntity(productDraft);
    AttributeConstraintValidator.validate(productType.getAttributes(), entity);

    SkuNameValidator.validate(productDraft);

    List<Product> products = productService.getAllProducts();
    SlugValidator.validate(productDraft.getSlug(), products);
    SkuNameValidator.validate(productDraft, products);

    ProductView result = save(entity);

    LOG.debug("Exit. Create productId: {}.", result.getId());
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
    VersionValidator.validate(entity, version);
    productService.deleteProductById(id);
    LOG.trace("Deleted product: {}.", entity);
    LOG.debug("Exit. Deleted productId: {}, version: {}.", id, version);
  }

  /**
   * Update product.
   *
   * @param id the id
   * @param version the version
   * @param actions the actions
   * @return the product view
   */
  public ProductView update(String id, Integer version, List<UpdateAction> actions) {
    LOG.debug("Enter. ProductId: {}, version: {}, actions: {}.", id, version, actions);

    Product product = productService.getProductById(id);
    VersionValidator.validate(product, version);

    ProductView result = updateProductEntity(product, actions);

    LOG.trace("Updated Product: {}.", result);
    LOG.debug("Exit.");

    return result;
  }

  /**
   * Update product entity.
   *
   * @param entity the product entity
   * @param actions the actions
   * @return product
   */
  private ProductView updateProductEntity(Product entity, List<UpdateAction> actions) {
    LOG.debug("Enter. ProductId: {}, actions: {}.", entity.getId(), actions);
    actions.stream().forEach(action -> {
      updaterService.handle(entity, action);
    });

    ProductView result = save(entity);
    LOG.debug("Exit. ProductId: {}.", result.getId());
    return result;
  }


  /**
   * Save product.
   *
   * @param entity the entity
   * @return the product view
   */
  private ProductView save(Product entity) {
    Product savedProduct = productService.save(entity);
    return ProductMapper.toModel(savedProduct);
  }
}
