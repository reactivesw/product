package io.reactivesw.product.application.service;


import com.google.common.collect.Lists;

import io.reactivesw.product.application.model.InventoryEntryView;
import io.reactivesw.product.application.model.ProductTypeView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by Davis on 16/12/22.
 */
@Component
public class ProductRestClient {
  /**
   * product type service uri.
   */
  @Value("${producttype.service.uri}")
  private transient String productTypeUri;

  /**
   * inventory service uri.
   */
  @Value("${inventory.service.uri}")
  private transient String inventoryUri;

  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(ProductRestClient.class);

  /**
   * RestTemplate.
   */
  private transient RestTemplate restTemplate = new RestTemplate();


  /**
   * Gets product type.
   *
   * @param id the id
   * @return the product type
   */
  public ProductTypeView getProductType(String id) {
    LOG.debug("enter getProductType, product type id is : {}", id);
    String url = productTypeUri + id;
    LOG.debug("get product type by url : {}", url);
    ProductTypeView result = restTemplate.getForObject(url, ProductTypeView.class);
    LOG.debug("end getProductType, result is : {}", result);
    return result;
  }


  /**
   * get product inventory by sku names.
   * @param skus list of String
   * @return list of InventoryEntryView
   */
  public List<InventoryEntryView> getInventoryBySkus(List<String> skus) {
    LOG.debug("enter getInventoryBySkus, sku is : {}", skus);

    List<InventoryEntryView> result = Lists.newArrayList();

    if (skus != null && !skus.isEmpty()) {

      String url = inventoryUri;

      UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
          .queryParam("skuNames", String.join(",", skus));

      ResponseEntity<InventoryEntryView[]> inventoryResult = restTemplate.exchange(
          builder.build().encode().toUri(),
          HttpMethod.GET,
          null,
          InventoryEntryView[].class);

      LOG.debug("end getInventoryBySkus");

      result = Lists.newArrayList(inventoryResult.getBody());
    }

    return result;
  }
}
