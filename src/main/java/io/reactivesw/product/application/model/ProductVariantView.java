package io.reactivesw.product.application.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.reactivesw.product.application.model.attribute.AttributeView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by umasuo on 16/11/17.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductVariantView {

  @ApiModelProperty(value = "The sequential ID of the variant within the product.", required = true)
  private Integer id;

  @ApiModelProperty(value = "The SKU of the variant.")
  private String sku;

  @ApiModelProperty(value = "User-specific unique identifier for the variant. ProductView variant " +
      "keys are different from product keys.")
  private String key;

  @ApiModelProperty(value = "The prices of the variant. " +
      "The prices does not contain two prices for the same price scope (same currency, country, " +
      "customer group and channel).",
      required = false)
  private List<PriceView> prices;

  @ApiModelProperty(required = false)
  private List<AttributeView> attributes;

  @ApiModelProperty(value = "Only appears when price selection is used. This field cannot be used" +
      " in a query predicate.", required = false)
  private PriceView price;

  @ApiModelProperty(required = false)
  private List<ImageView> images;

  @ApiModelProperty(required = false)
  private List<AssetView> assets;

  @ApiModelProperty(value = "The availability is set if the variant is tracked by the inventory. " +
      "The field might not contain the latest inventory state (it is eventually consistent) and " +
      "can be used as an optimization to reduce calls to the inventory service.",
      required = false)
  private ProductVariantAvailabilityView availability;

  @ApiModelProperty(value = "Only appears in response to a ProductView Projection Search request to " +
      "mark this variant as one that matches the search query.",
      required = false)
  private Boolean isMatchingVariant;

  @ApiModelProperty(value = "Only appears when price selection is used.",
      required = false)
  private ScopedPriceView scopedPrice;

  @ApiModelProperty(value = "Only appears in response to a ProductView Projection Search request when" +
      " price selection is used.", required = false)
  private Boolean scopedPriceDiscounted;

}
