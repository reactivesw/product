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
@ApiModel(description = "An AssetSource is a representation of an Asset in a specific format, e.g. a video in a certain encoding, or an image in a certain resolution.\n")
public class AssetSource {

  @ApiModelProperty(required = true)
  private String uri;

  @ApiModelProperty(value = "Must be unique within the Asset", required = false)
  private String key;

  @ApiModelProperty(value = "AssetDimensions", required = false)
  private AssetDimensions dimensions;

  @ApiModelProperty(required = false)
  private String contentType;
}
