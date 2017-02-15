package io.reactivesw.product.application;

import io.reactivesw.product.application.model.InventoryEntry;
import io.reactivesw.product.application.model.PagedQueryResult;
import io.reactivesw.product.application.model.Product;
import io.reactivesw.product.application.model.ProductType;
import io.reactivesw.product.infrastructure.util.ProductUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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
   * RestTemplate.
   */
  private transient RestTemplate restTemplate = new RestTemplate();;


  /**
   * Gets product type.
   *
   * @param id the id
   * @return the product type
   */
  public ProductType getProductType(String id) {
    String url = "http://localhost:8088/product-types/" + id;
    return restTemplate.getForObject(url, ProductType.class);
  }

  /**
   * Gets InventoryEntry.
   *
   * @param product the product
   * @return the inventory entry
   */
  public List<InventoryEntry> getInventoryEntry(Product product) {
    List<String> skuNames = ProductUtils.getSkuNames(product);
    String url = "http://localhost:8088/inventory";

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
