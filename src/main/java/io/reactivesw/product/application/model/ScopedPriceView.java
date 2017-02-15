package io.reactivesw.product.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.reactivesw.model.Money;
import io.reactivesw.model.Reference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;

/**
 * Created by umasuo on 16/11/17.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "Scoped price is returned as a part of a variant in product search (when price selector is used).")
public class ScopedPriceView {

  @ApiModelProperty(value = "The unique ID of this price.",
          required = true,
          readOnly = true)
  private String id;

  @ApiModelProperty(value = "the original price value",required = true)
  private Money value;

  @ApiModelProperty(value = "either the original price value or the discounted value, if it’s available",required = true)
  private Money currentValue;

  @ApiModelProperty(value = "A two-digit country code as per ↗ ISO 3166-1 alpha-2 .",required = false)
  private String country;

  @ApiModelProperty(value = "A reference to a customer group.", required = false)
  private Reference customerGroup;

  @ApiModelProperty(value = "A reference to a channel.",required = false)
  private Reference channel;

  @ApiModelProperty(value = "Date from which the price is valid.",
          required = false)
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime validFrom;

  @ApiModelProperty(value = "Date until which the price is valid.", required = false)
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime validUntil;
}
