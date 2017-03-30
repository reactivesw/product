package io.reactivesw.product.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.reactivesw.model.Money;
import io.reactivesw.model.Reference;

import lombok.Data;

import java.time.ZonedDateTime;

/**
 * Created by umasuo on 16/11/17.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PriceView {

  /**
   * The Id.
   */
  private String id;

  /**
   * The Value.
   */
  private Money value;

  /**
   * The Country.
   */
  private String country;

  /**
   * The Customer group.
   */
  private Reference customerGroup;

  /**
   * The Channel.
   */
  private Reference channel;

  /**
   * The Valid from.
   */
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime validFrom;

  /**
   * The Valid until.
   */
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime validUntil;
}
