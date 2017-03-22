package io.reactivesw.product.domain.service;

import io.reactivesw.exception.NotExistException;
import io.reactivesw.product.application.model.ProductView;
import io.reactivesw.product.application.model.mapper.ProductMapper;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.infrastructure.repository.ProductRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
   * Query product by category list.
   *
   * @param categoryId the category id
   * @return the list
   */
  public List<Product> queryProductByCategory(String categoryId) {
    LOG.debug("enter queryProductByCategory, categoryId is : {}", categoryId);

    // TODO: 17/3/22 should search by categoryId, not findAll to filter
    List<Product> productEntities = productRepository.findAll();

    List<Product> result = productEntities.stream().filter(
        productEntity ->
            productEntity.getMasterData().getCurrent().getCategories().contains(categoryId)
    ).collect(Collectors.toList());

    LOG.debug("end queryProductByCategory, get product number is : {}", result.size());
    return result;
  }

  /**
   * Gets product by id.
   *
   * @param id the id
   * @return the product by id
   */
  public ProductView getProductById(String id) {
    LOG.debug("enter getProductById, id is : {}", id);

    Product entity = getProductEntityById(id);

    ProductView result = ProductMapper.entityToModel(entity);

    LOG.debug("end getProductById, get ProductView is : {}", result.toString());

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
      throw new NotExistException("ProductView Not Found");
    }
    return entity;
  }
}
