package io.reactivesw.product.application.admin.model;

import io.reactivesw.product.application.model.ImageView;
import io.reactivesw.product.application.model.attribute.AttributeView;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import javax.validation.constraints.Pattern;

/**
 * Product variant draft, used in admin-web when create product.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductVariantDraft {

  /**
   * The Sku.
   */
  @Pattern(regexp = "[-a-zA-Z0-9_]{2,256}", message = "category slug can not match")
  private String sku;

  /**
   * User-specific unique identifier for the variant.
   */
  private String key;

  /**
   * Array of PriceDraft.
   * The prices for the variant draft.
   * Optional.
   */
  private List<PriceDraft> prices;

  /**
   * Array of ImageView.
   * Optional.
   * External images for the variant draft.
   * You can also upload images to use the commercetools™ platform’s Content Delivery Network.
   */
  private List<ImageView> images;

  /**
   * Array of AttributeView.
   * Optional.
   */
  private List<AttributeView> attributes;
}
