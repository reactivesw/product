package io.reactivesw.product.application.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by umasuo on 16/11/17.
 */
@Getter
@Setter
@ApiModel(description = "An AssetSourceView is a representation of an AssetView in a specific format, e.g. a video in a certain encoding, or an image in a certain resolution.\n")
public class AssetSourceView {

  @ApiModelProperty(required = true)
  private String uri;

  @ApiModelProperty(value = "Must be unique within the AssetView", required = false)
  private String key;

  @ApiModelProperty(value = "AssetDimensionsView", required = false)
  private AssetDimensionsView dimensions;

  @ApiModelProperty(required = false)
  private String contentType;
}
