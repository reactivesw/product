package io.reactivesw.product.domain.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import io.reactivesw.exception.NotExistException;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.domain.model.ProductData;
import io.reactivesw.product.domain.model.ProductVariant;
import io.reactivesw.product.infrastructure.repository.ProductRepository;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
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

    Predicate<ProductVariant> p = productVariant -> StringUtils.equals(productVariant.getSku(),
        sku);

    Product productEntity = products.parallelStream().filter(
        product ->
            StringUtils.equals(sku, product.getMasterData().getCurrent().getMasterVariant()
                .getSku())
                || product.getMasterData().getCurrent().getVariants().stream().anyMatch(p)
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
    LOG.debug("enter getProductById, id is : {}", id);

    Product result = getProductEntityById(id);

    LOG.debug("end getProductById, get Product is : {}", result.toString());

    return result;
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
}
