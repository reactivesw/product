package io.reactivesw.product.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.reactivesw.model.Reference;

import lombok.Data;

import java.time.ZonedDateTime;

/**
 * Created by Davis on 16/11/17.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductView {
  /**
   * The unique ID of the product.
   */
  private String id;

  /**
   * User-specific unique identifier for the product.
   * ProductView keys are different from product variant keys.
   */
  private String key;

  /**
   * The current version of the product.
   */
  private Integer version;

  /**
   * The Create at.
   */
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime createdAt;

  /**
   * last modified time.
   */
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime lastModifiedAt;

  /**
   * Reference to a ProductTypeView.
   */
  private Reference productType;

  /**
   * The product data in the master catalog.
   */
  private ProductCatalogDataView masterData;

  /**
   * Reference to a TaxCategory.
   */
  private Reference taxCategory;

  /**
   * Reference to a State.
   * Optional.
   */
  private Reference state;

  /**
   * Statistics about the review ratings taken into account for this product.
   * Statistics about the review ratings taken into account for this product.
   * Optional.
   */
  private ReviewRatingStatisticsView reviewRatingStatistics;
}
