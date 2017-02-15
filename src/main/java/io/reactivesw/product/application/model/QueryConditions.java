package io.reactivesw.product.application.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Davis on 16/11/21.
 */
@Getter
@Setter
@ApiModel
public class QueryConditions {

  @ApiModelProperty(required = false)
  String expandId;

  @ApiModelProperty(required = false)
  Integer version;

  /**
   * name(en="Pro T-Shirt")
   */
  @ApiModelProperty(required = false)
  String where;

  /**
   * name.em
   */
  @ApiModelProperty(required = false)
  String sort;

  @ApiModelProperty(required = false)
  String sortOrder;

  @ApiModelProperty(required = false)
  String page;

  @ApiModelProperty(required = false)
  String perPage;

  @ApiModelProperty(required = false)
  String expand;

  @ApiModelProperty(required = false)
  Boolean staged;

  @ApiModelProperty(required = false)
  Boolean stagedId;
}
