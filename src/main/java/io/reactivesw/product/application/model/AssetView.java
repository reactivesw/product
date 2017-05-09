package io.reactivesw.product.application.model;

import io.reactivesw.model.LocalizedString;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by umasuo on 16/11/17.
 */
@Getter
@Setter
public class AssetView {

  /**
   * the id.
   */
  private String id;

  /**
   * asset sources.
   */
  private List<AssetSourceView> sources;

  /**
   * the name.
   */
  private LocalizedString name;

  /**
   * the description.
   */
  private LocalizedString description;

  /**
   * the tags.
   */
  private List<String> tags;
}
