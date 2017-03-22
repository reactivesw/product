package io.reactivesw.product.application.model;

import io.reactivesw.model.LocalizedString;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Davis on 17/3/22.
 */
@Getter
@Setter
public class DetailProductView {
  /**
   * The Id.
   */
  private String id;

  /**
   * The Product type.
   */
  private ProductTypeView productType;

  /**
   * The Name.
   */
  private LocalizedString name;

  /**
   * The Description.
   */
  private LocalizedString description;

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
   * The Search keyword.
   */
  private SearchKeyword searchKeyword;

  /**
   * The Master variant.
   */
  private ProductVariantView masterVariant;

  /**
   * The Variants.
   */
  private List<ProductVariantView> variants;
}
