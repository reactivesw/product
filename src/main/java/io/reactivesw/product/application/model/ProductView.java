package io.reactivesw.product.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.reactivesw.model.LocalizedString;
import io.reactivesw.model.Reference;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by Davis on 16/11/17.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductView {

  /**
   * The unique ID of the ProductViewOld.
   */
  private String id;

  /**
   * User-specific unique identifier of the ProductViewOld.
   */
  private String key;

  /**
   * Reference to a ProductTypeView
   */
  private Reference productType;

  /**
   * The Name.
   */
  private LocalizedString name;

  /**
   * The Description.
   */
  private LocalizedString description;

  /**
   * The Slug.
   */
  private String slug;

  /**
   * Array of Reference to a CategoryEntity.
   * References to category the product is in.
   */
  private List<Reference> categories;

  /**
   * The CategoryEntity order hints.
   */
  private List<CategoryOrderHintView> categoryOrderHints;

  /**
   * The Meta title.
   */
  private LocalizedString metaTitle;

  /**
   * The Meta description.
   */
  private LocalizedString metaDescription;

  /**
   * The Meta keywords.
   */
  private LocalizedString metaKeywords;

  /**
   * The Search keywords.
   */
  private List<SearchKeyword> searchKeyword;

  /**
   * variants - Array of ProductVariant
   */
  private ProductVariantView masterVariant;

  /**
   * variants
   */
  private List<ProductVariantView> variants;

  /**
   * Reference to a TaxCategory.
   * Optional.
   */
  private Reference taxCategory;

  /**
   * Reference to a State.
   * Optional.
   */
  private Reference state;

  /**
   * Statistics about the review ratings taken into account for this product.
   */
  private ReviewRatingStatisticsView reviewRatingStatistics;
}
