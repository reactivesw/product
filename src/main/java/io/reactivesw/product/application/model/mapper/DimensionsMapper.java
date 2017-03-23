package io.reactivesw.product.application.model.mapper;

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
   * Map to model asset dimensions view.
   *
   * @param entity the entity
   * @return the asset dimensions view
   */
  public static AssetDimensionsView mapToModel(
      AssetDimensions entity) {
    AssetDimensionsView model = new AssetDimensionsView();

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
  public static AssetDimensions modelToEntity(AssetDimensionsView model) {
    AssetDimensions entity = new AssetDimensions();

    entity.setH(model.getH());
    entity.setW(model.getW());

    return entity;
  }
}
