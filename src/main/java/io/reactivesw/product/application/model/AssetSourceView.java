package io.reactivesw.product.application.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by umasuo on 16/11/17.
 */
@Getter
@Setter
public class AssetSourceView {

  /**
   * asset uri.
   */
  private String uri;

  /**
   * the key.
   */
  private String key;

  /**
   * asset dimensions.
   */
  private AssetDimensionsView dimensions;

  /**
   * content type.
   */
  private String contentType;
}
