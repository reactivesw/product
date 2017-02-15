package io.reactivesw.product.application.model.mapper;

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
   * Entity to model io . reactivesw . product . application . model . asset dimensions.
   *
   * @param entity the entity
   * @return the io . reactivesw . product . application . model . asset dimensions
   */
  public static io.reactivesw.product.application.model.AssetDimensions entityToModel(
      AssetDimensions entity) {
    io.reactivesw.product.application.model.AssetDimensions model = new io.reactivesw.product
        .application.model.AssetDimensions();

    model.setH(entity.getH());
    model.setW(entity.getW());

    return model;
  }

  /**
   * Model to entity asset dimensions.
   *
   * @param model the model
   * @return the asset dimensions
   */
  public static AssetDimensions modelToEntity(io.reactivesw.product.application.model
                                                  .AssetDimensions model) {
    AssetDimensions entity = new AssetDimensions();

    entity.setH(model.getH());
    entity.setW(model.getW());

    return entity;
  }
}
