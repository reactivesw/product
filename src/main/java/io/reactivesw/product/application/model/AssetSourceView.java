package io.reactivesw.product.application.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by umasuo on 16/11/17.
 */
@Getter
@Setter
public class AssetSourceView {

  private String uri;

  private String key;

  private AssetDimensionsView dimensions;

  private String contentType;
}
