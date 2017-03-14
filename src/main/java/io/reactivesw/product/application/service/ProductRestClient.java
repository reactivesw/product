package io.reactivesw.product.application.service;

import io.reactivesw.product.application.model.InventoryEntryView;
import io.reactivesw.product.application.model.PagedQueryResult;
import io.reactivesw.product.application.model.ProductView;
import io.reactivesw.product.application.model.ProductTypeView;
import io.reactivesw.product.infrastructure.util.ProductUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
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
  private String productTypeUri;

  /**
   * inventory service uri.
   */
  @Value("${inventory.service.uri}")
  private String inventoryUri;

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
   * Gets InventoryEntryView.
   *
   * @param product the product
   * @return the inventory entry
   */
  public List<InventoryEntryView> getInventoryEntry(ProductView product) {
    List<String> skuNames = ProductUtils.getSkuNames(product);
    String url = inventoryUri;

    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
        .queryParam("skuNames", skuNames);

    HttpEntity<PagedQueryResult> result = restTemplate.exchange(
        builder.build().encode().toUri(),
        HttpMethod.GET,
        null,
        PagedQueryResult.class);

    return result.getBody().getResults();
  }
}
