package io.reactivesw.product.application.service;


import com.google.common.collect.Lists;

import io.reactivesw.product.application.model.InventoryEntryView;
import io.reactivesw.product.application.model.ProductTypeView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
    LOG.debug("enter. product type id: {}.", id);

    String url = productTypeUri + id;
    ProductTypeView result = restTemplate.getForObject(url, ProductTypeView.class);

    LOG.debug("end. product type: {}.", result);

    return result;
  }


  /**
   * get product inventory by sku names.
   *
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

      InventoryEntryView[] inventoryResult = restTemplate.getForObject(
          builder.build().encode().toUri(),
          InventoryEntryView[].class);

      result = Lists.newArrayList(inventoryResult);
    }

    LOG.debug("end getInventoryBySkus");
    return result;
  }
}
