package io.reactivesw.product.application.service;

import io.reactivesw.product.application.model.InventoryEntryView;
import io.reactivesw.product.application.model.ProductView;
import io.reactivesw.product.application.model.ProductViewOld;
import io.reactivesw.product.application.model.QueryConditions;
import io.reactivesw.product.application.model.mapper.ProductProjectionMapper;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.domain.service.ProductService;
import io.reactivesw.product.infrastructure.util.ProductInventoryUtils;
import io.reactivesw.product.infrastructure.util.QueryConditionUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Davis on 16/12/18.
 */
@Service
public class ProductApplication {
  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(ProductApplication.class);

  /**
   * ProductRestClient.
   */
  @Autowired
  private transient ProductRestClient productRestClient;

  /**
   * product service.
   */
  @Autowired
  private transient ProductService productService;

  /**
   * Get prodcut by id.
   *
   * @param id the id
   * @return the product
   */
  public ProductView getProductById(String id) {
    LOG.debug("enter getProductById, the id is : {}", id);

    ProductView result = productService.getProductById(id);

    List<InventoryEntryView> inventoryEntries = productRestClient.getInventoryEntry(result);

    if (inventoryEntries != null && ! inventoryEntries.isEmpty()) {
      result = ProductInventoryUtils.mergeInventoryEntryToProduct(inventoryEntries, result);
    }

    LOG.debug("end getProductById, the product is : {}", result.toString());

    return result;
  }

  /**
   * Query product merchant list.
   * TODO: 16/12/21 only for query product by category now.
   * query example:
   * categoryId:"1234567890"
   *
   * @param queryConditions the query conditions
   * @return the list
   */
  public List<ProductView> queryProductProject(QueryConditions queryConditions) {
    LOG.debug("enter queryProductProjections, query conditions is : {}",
        queryConditions.toString());

    String categoryId = QueryConditionUtils.getCategoryId(queryConditions);

    List<Product> productEntities = productService.queryProductByCategory(categoryId);

    List<ProductView> result = ProductProjectionMapper.entityToModel(productEntities);

    LOG.debug("end queryProductProjections, product projections number is : {}", result.size());

    return result;
  }
}