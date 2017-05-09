package io.reactivesw.product.application.model.mapper;

import io.reactivesw.product.application.model.ImageView;
import io.reactivesw.product.domain.model.Image;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Davis on 16/12/14.
 */
public final class ImageMapper {

  /**
   * Instantiates a new Image mapper.
   */
  private ImageMapper() {
  }

  /**
   * Entity to model list.
   *
   * @param entities the entities
   * @return the list
   */
  public static List<ImageView> toModel(List<Image> entities) {
    return entities.stream().map(
        entity -> {
          return toModel(entity);
        }
    ).collect(Collectors.toList());
  }

  /**
   * Entity to model image view.
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
}