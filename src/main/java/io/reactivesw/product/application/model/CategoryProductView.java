package io.reactivesw.product.application.model;

import io.reactivesw.model.LocalizedString;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Davis on 17/3/22.
 */
@Getter
@Setter
public class CategoryProductView {
  /**
   * The Id.
   */
  public String id;

  /**
   * The Name.
   */
  public LocalizedString name;

  /**
   * The Sku.
   */
  String sku;

  /**
   * The Price.
   */
  PriceView price;

  /**
   * The Image url.
   */
  String imageUrl;

  /**
   * The Available.
   */
  boolean available;
}
