package io.reactivesw.product.application.model.mapper;

import io.reactivesw.product.application.model.ImageView;
import io.reactivesw.product.domain.model.Image;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Davis on 16/12/14.
 */
public class ImageMapper {
  public static List<ImageView> entityToModel(List<Image> entities) {
    return entities.stream().map(
        entity -> {
          return entityToModel(entity);
        }
    ).collect(Collectors.toList());
  }

  public static ImageView entityToModel(Image entity) {
    ImageView model = new ImageView();

    model.setUrl(entity.getUrl());
    model.setLabel(entity.getLabel());
    model.setDimensions(
        DimensionsMapper.entityToModel(entity.getDimensions())
    );

    return model;
  }

  public static List<Image> modelToEntity(List<ImageView> models) {
    return models.stream().map(
        model -> {
          return modelToEntity(model);
        }
    ).collect(Collectors.toList());
  }

  public static Image modelToEntity(ImageView model) {
    Image entity = new Image();

    entity.setUrl(model.getUrl());
    entity.setLabel(model.getLabel());
    entity.setDimensions(DimensionsMapper.modelToEntity(model.getDimensions()));

    return entity;
  }
}
