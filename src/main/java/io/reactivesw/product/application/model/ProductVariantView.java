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

  private Integer id;

  private String sku;

  private String key;

  private List<PriceView> prices;

  private List<AttributeView> attributes;

  private PriceView price;

  private List<ImageView> images;

  private List<AssetView> assets;

  private ProductVariantAvailabilityView availability;

  private Boolean isMatchingVariant;

  private ScopedPriceView scopedPrice;

  private Boolean scopedPriceDiscounted;
}
