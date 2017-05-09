package io.reactivesw.product.domain.service;

import io.reactivesw.exception.NotExistException;
import io.reactivesw.product.application.admin.model.ProductDraft;
import io.reactivesw.product.application.admin.model.ProductView;
import io.reactivesw.product.application.admin.model.mapper.ProductMapper;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.infrastructure.repository.ProductRepository;
import io.reactivesw.product.infrastructure.util.SkuUtils;
import io.reactivesw.product.infrastructure.validator.SkuNameValidator;
import io.reactivesw.product.infrastructure.validator.SlugValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Davis on 16/12/14.
 */
@Service
public class ProductService {
  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(ProductService.class);

  /**
   * ProductRepository.
   */
  private transient ProductRepository productRepository;

  /**
   * Instantiates a new Product service.
   *
   * @param productRepository the product repository
   */
  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  /**
   * Create product.
   *
   * @param productDraft the product draft
   * @return the product
   */
  @Transactional
  public ProductView createProduct(ProductDraft productDraft) {
    LOG.debug("enter createProduct, ProductDraft is : {}", productDraft.toString());

    List<Product> products = productRepository.findAll();
    SlugValidator.validate(productDraft.getSlug(), products);
    SkuNameValidator.validate(productDraft, products);

    Product entity = ProductMapper.toEntity(productDraft);

    Product savedEntity = productRepository.save(entity);

    ProductView result = ProductMapper.toModel(savedEntity);

    LOG.debug("end createProduct, created ProductView is : {}", result.toString());

    return result;
  }

  /**
   * Query product by category list.
   *
   * @param categoryId the category id
   * @return the list
   */
  // TODO: 17/3/22 should search by categoryId, not findAll to filter
  public List<Product> queryProductByCategory(String categoryId) {
    LOG.debug("enter queryProductByCategory, categoryId is : {}", categoryId);

    List<Product> productEntities = productRepository.findAll();

    List<Product> result = productEntities.stream().filter(
        productEntity ->
            productEntity.getMasterData().getCurrent().getCategories().contains(categoryId)
    ).collect(Collectors.toList());

    LOG.debug("end queryProductByCategory, get product number is : {}", result.size());
    return result;
  }

  /**
   * Gets product by slug.
   *
   * @param sku the slug
   * @return the product by slug
   */
  // TODO: 17/3/22  should search by sku, not findAll to filter
  public Product getProductBySku(String sku) {
    LOG.debug("enter getProductBySku, sku is : {}", sku);

    List<Product> products = productRepository.findAll();

    Product productEntity = products.stream().filter(
        product -> isMatchingSku(product, sku)
    ).findAny().orElse(null);

    if (productEntity == null) {
      throw new NotExistException();
    }

    LOG.debug("end getProductBySku, get product : {}", productEntity.toString());

    return productEntity;
  }

  /**
   * Gets product by id.
   *
   * @param id the id
   * @return the product by id
   */
  public Product getProductById(String id) {
    LOG.debug("enter getCartProductById, id is : {}", id);

    Product result = getProductEntityById(id);

    LOG.debug("end getCartProductById, get Product is : {}", result.toString());

    return result;
  }

  /**
   * Save product.
   *
   * @param entity the entity
   * @return the product
   */
  public Product save(Product entity) {
    LOG.debug("Enter. ProductId: {}.", entity.getId());
    LOG.trace("Product: {}.", entity);

    Product savedEntity = productRepository.save(entity);

    LOG.debug("Exit.");
    return savedEntity;
  }

  /**
   * Gets product entity by id.
   *
   * @param id the id
   * @return the product entity by id
   */
  private Product getProductEntityById(String id) {
    Product entity = productRepository.findOne(id);
    if (entity == null) {
      LOG.debug("can not find product by id : {}", id);
      throw new NotExistException("ProductViewOld Not Found");
    }
    return entity;
  }

  /**
   * find out if product has the sku.
   *
   * @param product the Product
   * @param sku     the sku
   * @return boolean
   */
  private boolean isMatchingSku(Product product, String sku) {
    boolean result = false;
    List<String> skus = SkuUtils.getSkuNames(product);
    result = skus.contains(sku);
    return result;
  }
}
