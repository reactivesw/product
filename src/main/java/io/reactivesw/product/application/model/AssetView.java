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
  private String id;

  private List<AssetSourceView> sources;

  private LocalizedString name;

  private LocalizedString description;

  private List<String> tags;
}
