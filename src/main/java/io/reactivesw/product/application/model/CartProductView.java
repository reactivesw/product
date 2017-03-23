package io.reactivesw.product.application.model;

import io.reactivesw.model.LocalizedString;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

import javax.persistence.Column;

/**
 * Created by Davis on 17/3/22.
 */
@Getter
@Setter
@ToString
public class CartProductView {
  /**
   * product id.
   */
  private String productId;

  /**
   * name in localized string.
   */
  private LocalizedString name;

  /**
   * id in number.
   */
  private Integer variantId;

  /**
   * sku.
   */
  private String sku;

  /**
   * list of images.
   */
  private List<ImageView> images;

  /**
   * Price view.
   */
  private PriceView price;
}
