package io.reactivesw.product.application.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.reactivesw.product.application.model.attribute.AttributeView;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by umasuo on 16/11/17.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductVariantView {

  /**
   * The Id.
   */
  private Integer id;

  /**
   * The Sku.
   */
  private String sku;

  /**
   * The Key.
   */
  private String key;

  /**
   * The Prices.
   */
  private List<PriceView> prices;

  /**
   * The Attributes.
   */
  private List<AttributeView> attributes;

  /**
   * The Price.
   */
  private PriceView price;

  /**
   * The Images.
   */
  private List<ImageView> images;

  /**
   * The Assets.
   */
  @Deprecated
  private List<AssetView> assets;

  /**
   * The Availability.
   */
  private ProductVariantAvailabilityView availability;

  /**
   * The Available.
   */
  private boolean available;

  /**
   * The Is matching variant.
   */
  private Boolean isMatchingVariant;

  /**
   * The Scoped price.
   */
  @Deprecated
  private ScopedPriceView scopedPrice;

  /**
   * The Scoped price discounted.
   */
  @Deprecated
  private Boolean scopedPriceDiscounted;
}
