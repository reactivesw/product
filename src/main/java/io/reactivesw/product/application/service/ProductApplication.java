package io.reactivesw.product.application.service;

import com.google.common.collect.Lists;

import io.reactivesw.product.application.model.CartProductView;
import io.reactivesw.product.application.model.CategoryProductView;
import io.reactivesw.product.application.model.DetailProductView;
import io.reactivesw.product.application.model.InventoryEntryView;
import io.reactivesw.product.application.model.ProductTypeView;
import io.reactivesw.product.application.model.mapper.CartProductMapper;
import io.reactivesw.product.application.model.mapper.CategoryProductMapper;
import io.reactivesw.product.application.model.mapper.DetailProductMapper;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.domain.model.ProductData;
import io.reactivesw.product.domain.model.ProductVariant;
import io.reactivesw.product.domain.service.ProductService;
import io.reactivesw.product.infrastructure.util.InventoryUtils;
import io.reactivesw.product.infrastructure.util.SearchUtils;
import io.reactivesw.product.infrastructure.util.SkuUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Davis on 16/12/18.
 */
@Service(value = "CustomerProductApplication")
public class ProductApplication {

  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(ProductApplication.class);

  /**
   * ProductRestClient.
   */
  private transient ProductRestClient productRestClient;

  /**
   * product service.
   */
  private transient ProductService productService;

  /**
   * Instantiates a new Product application.
   *
   * @param productRestClient the product rest client
   * @param productService the product service
   */
  @Autowired
  public ProductApplication(ProductRestClient productRestClient, ProductService productService) {
    this.productRestClient = productRestClient;
    this.productService = productService;
  }

  /**
   * Get prodcut by id.
   *
   * @param id the id
   * @param variantId the variant id
   * @return the product
   */
  public CartProductView getCartProductById(String id, Integer variantId) {
    LOG.debug("enter getCartProductById, the id is : {}", id);

    Product product = productService.getProductById(id);

    ProductVariant productVariant = getProductVariant(product, variantId);

    CartProductView result = CartProductMapper.toModel(product, productVariant);

    LOG.debug("end getCartProductById, the product is : {}", result.toString());

    return result;
  }

  /**
   * Query product merchant list.
   * TODO: 16/12/21 only for query product by category now.
   * query example:
   * categoryId:"1234567890"
   *
   * @param categoryId the query id
   * @return the list
   */
  public List<CategoryProductView> queryCategoryProducts(String categoryId) {
    LOG.debug("enter queryCategoryProducts, category id  is : {}.", categoryId);

    List<Product> productEntities = productService.queryProductByCategory(categoryId);

    List<CategoryProductView> result = CategoryProductMapper.toModel(productEntities);

    mergeInventory(result);

    LOG.debug("end queryCategoryProducts, category {} has {} products.", categoryId, result.size());

    return result;
  }

  /**
   * Gets detail product by sku.
   *
   * @param sku the sku
   * @return the detail product by sku
   */
  public DetailProductView getDetailProductBySku(String sku) {
    LOG.debug("enter getDetailProductBySku, sku is : {}.", sku);

    Product productEntity = productService.getProductBySku(sku);

    DetailProductView result = DetailProductMapper.toModel(productEntity);

    String productTypeId = getProductTypeId(productEntity);
    ProductTypeView productTypeView = productRestClient.getProductType(productTypeId);
    result = DetailProductMapper.mergeProductType(productTypeView, result);

    List<String> skuNames = SkuUtils.getSkuNames(productEntity);
    List<InventoryEntryView> inventoryEntries = productRestClient.getInventoryBySkus(skuNames);

    if (inventoryEntries != null && !inventoryEntries.isEmpty()) {
      result = InventoryUtils.mergeInventoryToDetailProduct(inventoryEntries, result);
    }

    LOG.debug("end getDetailProductBySku, result is : {}.", result.toString());

    return result;
  }

  /**
   * Search products.
   *
   * @param searchWords the search words
   * @return the list
   */
  public List<CategoryProductView> searchProduct(String searchWords) {
    LOG.debug("Enter. SearchWords: {}.", searchWords);
    List<CategoryProductView> result = Lists.newArrayList();

    List<Product> products = productService.getAllProducts();

    if (products != null && !products.isEmpty()) {
      result = SearchUtils.searchProduct(searchWords.trim(), products);
      mergeInventory(result);
    }

    LOG.trace("Matching product: {}.", result);
    LOG.debug("Exit. Result count: {}.", result.size());

    return result;
  }

  /**
   * get product typd id.
   *
   * @param product the Product
   * @return String
   */
  private String getProductTypeId(Product product) {
    return product.getProductType();
  }

  /**
   * get product variant by it's id.
   *
   * @param product product entity
   * @param variantId variant id
   * @return ProductVariant
   */
  private ProductVariant getProductVariant(Product product, Integer variantId) {
    ProductVariant result = null;
    ProductData productData = product.getMasterData().getCurrent();

    if (variantId.equals(1)) {
      result = productData.getMasterVariant();
    } else {
      Predicate<ProductVariant> predicate =
          productVariant -> productVariant.getId().equals(variantId);
      result = productData.getVariants().stream().filter(predicate).findAny().get();
    }

    return result;
  }

  /**
   * Query inventory by sku name and merge to CategoryProductView.
   *
   * @param productViews list of CategoryProductView
   * @return list of CategoryProductView after merged
   */
  private void mergeInventory(List<CategoryProductView> productViews) {
    List<String> skuNames = SkuUtils.getCategoryProductSkuNames(productViews);
    List<InventoryEntryView> inventoryEntries = productRestClient.getInventoryBySkus(skuNames);

    if (inventoryEntries != null && !inventoryEntries.isEmpty()) {
      InventoryUtils.mergeInventoryToCategoryProducts(inventoryEntries, productViews);
    }
  }
}