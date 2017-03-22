package io.reactivesw.product.application.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.reactivesw.model.LocalizedString;
import io.reactivesw.model.Reference;
import io.reactivesw.product.domain.model.ProductCatalogData;

import lombok.Data;

import java.util.List;

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
   * Reference to a ProductTypeView.
   */
  private Reference productType;

  /**
   * the name.
   */
  private LocalizedString name;

  /**
   * the description.
   */
  private LocalizedString description;

  /**
   * human-readable identifiers usually used as deep-link URL to the related product.
   * Each slug is unique across a merchant,
   * but a product can have the same slug for different languages.
   */
  private String slug;

  /**
   * Array of Reference to a CategoryEntity
   * References to category the product is in.
   */
  private List<Reference> categories;

  /**
   * categoryOrderHints.
   */
  private List<CategoryOrderHintView> categoryOrderHints;

  /**
   * the meta title.
   * optional.
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
   * masterVariant.
   */
  private ProductVariantView masterVariant;

  /**
   * The Variants.
   */
  private List<ProductVariantView> variants;

  /**
   * search keyword.
   */
  private SearchKeyword searchKeyword;

  /**
   * Reference to a TaxCategory.
   */
  @Deprecated
  private Reference taxCategory;

  /**
   * Reference to a State.
   * Optional.
   */
  @Deprecated
  private Reference state;

  /**
   * Statistics about the review ratings taken into account for this product.
   * Statistics about the review ratings taken into account for this product.
   * Optional.
   */
  @Deprecated
  private ReviewRatingStatisticsView reviewRatingStatistics;

  @Deprecated
  private ProductCatalogDataView masterData;
}
