package io.reactivesw.product.application.model.mapper;

import io.reactivesw.product.application.model.CategoryOrderHintView;
import io.reactivesw.product.domain.model.CategoryOrderHint;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Davis on 16/12/20.
 */
public final class CategoryOrderHintsMapper {
  /**
   * Model to entity list.
   *
   * @param models the models
   * @return the list
   */
  public static Set<CategoryOrderHint> modelToEntity(List<CategoryOrderHintView> models) {
    return models.stream().map(
        model -> {
          return new CategoryOrderHint(model.getKey(), model.getOrder());
        }
    ).collect(Collectors.toSet());
  }

  /**
   * Entity to model list.
   *
   * @param entities the entities
   * @return the list
   */
  public static List<CategoryOrderHintView> entityToModel(Set<CategoryOrderHint> entities) {
    return entities.stream().map(
        entity -> {
          return new CategoryOrderHintView(entity.getCategoryId(), entity.getOrderHint());
        }
    ).collect(Collectors.toList());
  }
}
