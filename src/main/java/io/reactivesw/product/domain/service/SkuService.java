package io.reactivesw.product.domain.service;

import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.infrastructure.repository.ProductRepository;
import io.reactivesw.product.infrastructure.validator.SkuNameValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for sku.
 */
@Service
public class SkuService {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(SkuService.class);

  /**
   * The Product repository.
   */
  private transient ProductRepository productRepository;

  /**
   * Validate sku name.
   *
   * @param skuName the sku name
   */
  public void validateSkuName(String skuName) {
    LOG.debug("Enter. SkuName: {}.", skuName);
    List<Product> products = productRepository.findAll();
    SkuNameValidator.validate(skuName, products);
    LOG.debug("Exit.");
  }
}
