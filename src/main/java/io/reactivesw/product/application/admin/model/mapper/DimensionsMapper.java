package io.reactivesw.product.application.admin.model.mapper;

import io.reactivesw.product.application.model.AssetDimensionsView;
import io.reactivesw.product.domain.model.AssetDimensions;

/**
 * Created by Davis on 17/2/15.
 */
public final class DimensionsMapper {

  /**
   * Instantiates a new Dimensions mapper.
   */
  private DimensionsMapper() {
  }

  /**
   * Entity to model io . reactivesw . product . controller . model . asset dimensions.
   *
   * @param entity the entity
   * @return the io . reactivesw . product . controller . model . asset dimensions
   */
  public static AssetDimensionsView toModel(
      AssetDimensions entity) {
    AssetDimensionsView model = new AssetDimensionsView();

    model.setHeight(entity.getHeight());
    model.setWidth(entity.getWidth());

    return model;
  }

  /**
   * Model to entity asset dimensions.
   *
   * @param model the model
   * @return the asset dimensions
   */
  public static AssetDimensions toEntity(AssetDimensionsView model) {
    AssetDimensions entity = new AssetDimensions();

    entity.setHeight(model.getHeight());
    entity.setWidth(model.getWidth());

    return entity;
  }
}
