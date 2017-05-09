package io.reactivesw.product.application.model;

import io.reactivesw.model.LocalizedString;

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
  private String id;

  /**
   * The Name.
   */
  private LocalizedString name;

  /**
   * The Sku.
   */
  private String sku;

  /**
   * The Price.
   */
  private PriceView price;

  /**
   * The Image url.
   */
  private String imageUrl;

  /**
   * The Available.
   */
  private boolean available;
}
