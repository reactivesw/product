package io.reactivesw.product.application.model.mapper;

import io.reactivesw.product.application.model.Image;
import io.reactivesw.product.domain.model.ImageEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Davis on 16/12/14.
 */
public class ImageMapper {
  public static List<Image> entityToModel(List<ImageEntity> entities) {
    return entities.stream().map(
        entity -> {
          return entityToModel(entity);
        }
    ).collect(Collectors.toList());
  }

  public static Image entityToModel(ImageEntity entity) {
    Image model = new Image();

    model.setUrl(entity.getUrl());
    model.setLabel(entity.getLabel());
    model.setDimensions(
        DimensionsMapper.entityToModel(entity.getDimensions())
    );

    return model;
  }

  public static List<ImageEntity> modelToEntity(List<Image> models) {
    return models.stream().map(
        model -> {
          return modelToEntity(model);
        }
    ).collect(Collectors.toList());
  }

  public static ImageEntity modelToEntity(Image model) {
    ImageEntity entity = new ImageEntity();

    entity.setUrl(model.getUrl());
    entity.setLabel(model.getLabel());
    entity.setDimensions(DimensionsMapper.modelToEntity(model.getDimensions()));

    return entity;
  }
}
