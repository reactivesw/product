package io.reactivesw.product.domain.service;

import io.reactivesw.exception.ConflictException;
import io.reactivesw.exception.NotExistException;
import io.reactivesw.product.application.model.ProductView;
import io.reactivesw.product.application.model.ProductDraft;
import io.reactivesw.product.application.model.mapper.ProductMapper;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.infrastructure.repository.ProductRepository;
import io.reactivesw.product.infrastructure.validator.SkuNameValidator;
import io.reactivesw.product.infrastructure.validator.SlugValidator;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
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
  @Autowired
  private transient ProductRepository productRepository;

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

    Product entity = ProductMapper.modelToEntity(productDraft);

    Product savedEntity = productRepository.save(entity);

    ProductView result = ProductMapper.entityToModel(savedEntity);

    LOG.debug("end createProduct, created ProductView is : {}", result.toString());

    return result;
  }

  /**
   * Query product by category list.
   *
   * @param categoryId the category id
   * @return the list
   */
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
   * Gets product by slug.
   *
   * @param slug the slug
   * @return the product by slug
   */
  public ProductView getProductBySlug(String slug) {
    LOG.debug("enter getProductBySlug, slug is : {}", slug);

    List<Product> products = productRepository.findAll();
    Product productEntity = products.parallelStream().filter(
        product -> StringUtils.equals(slug, product.getMasterData().getCurrent().getSlug())
    ).findAny().orElse(null);

    if (productEntity == null) {
      throw new NotExistException();
    }
    
    ProductView result = ProductMapper.entityToModel(productEntity);

    LOG.debug("end getProductBySlug, get product : {}", result.toString());

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

  /**
   * Delete product.
   *
   * @param id      the id
   * @param version the version
   */
  public void deleteProduct(String id, Integer version) {
    LOG.debug("enter deleteProduct, id:{}, version:{}", id, version);

    Product entity = this.getProductEntityById(id);
    validateVersion(entity, version);

    productRepository.delete(entity);

    //TODO send message for:

    LOG.debug("end deleteProduct, id:{}, version:{}", id, version);
  }


  /**
   * Validate version.
   *
   * @param entity  the entity
   * @param version the version
   */
  private void validateVersion(Product entity, Integer version) {
    if (!Objects.equals(version, entity.getVersion())) {
      LOG.debug("Version not match, input version:{}, entity version:{}",
          version, entity.getVersion());
      throw new ConflictException("Version not match");
    }
  }
}
