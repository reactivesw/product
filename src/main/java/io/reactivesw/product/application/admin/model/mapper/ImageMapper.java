package io.reactivesw.product.application.admin.model.mapper;

import io.reactivesw.product.application.model.ImageView;
import io.reactivesw.product.domain.model.Image;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Image Mapper class.
 * For convert Image Entity to Image Model,
 * or convert Image Model to Image Entity.
 */
public final class ImageMapper {

  /**
   * Instantiates a new Image mapper.
   */
  private ImageMapper() {
  }

  /**
   * Convert List of Image Entity to List of ImageView.
   *
   * @param entities the entities
   * @return the list
   */
  public static List<ImageView> toModel(List<Image> entities) {
    return entities.stream().map(
        entity ->
            toModel(entity)
    ).collect(Collectors.toList());
  }

  /**
   * Convert Image Entity to ImageView.
   *
   * @param entity the entity
   * @return the image view
   */
  public static ImageView toModel(Image entity) {
    ImageView model = new ImageView();

    model.setUrl(entity.getUrl());
    model.setLabel(entity.getLabel());
    model.setDimensions(
        DimensionsMapper.toModel(entity.getDimensions())
    );

    return model;
  }

  /**
   * Convert List of ImageView to List of Image Entity.
   *
   * @param models the models
   * @return the list
   */
  public static List<Image> toEntity(List<ImageView> models) {
    return models.stream().map(
        model -> toEntity(model)
    ).collect(Collectors.toList());
  }

  /**
   * Convert ImageView to Image Entity.
   *
   * @param model the model
   * @return the image
   */
  public static Image toEntity(ImageView model) {
    Image entity = new Image();

    entity.setUrl(model.getUrl());
    entity.setLabel(model.getLabel());
    entity.setDimensions(DimensionsMapper.toEntity(model.getDimensions()));

    return entity;
  }
}