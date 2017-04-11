package io.reactivesw.product.application.admin.model.mapper;

import io.reactivesw.product.application.model.CategoryOrderHintView;
import io.reactivesw.product.domain.model.CategoryOrderHint;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * CategoryOrderHints Mapper class.
 * Convert List of CategoryOrderHintView to List of CategoryOrderHint Entity,
 * or Convert List of CategoryOrderHint Entity to List of CategoryOrderHintView.
 */
public final class CategoryOrderHintsMapper {
  /**
   * Instantiates a new Category order hints mapper.
   */
  private CategoryOrderHintsMapper() {
  }

  /**
   * Convert List of CategoryOrderHintView to List of CategoryOrderHint Entity.
   *
   * @param models the models
   * @return the list
   */
  public static Set<CategoryOrderHint> toEntity(List<CategoryOrderHintView> models) {
    return models.stream().map(
        model -> {
          return CategoryOrderHint.build(model.getKey(), model.getOrder());
        }
    ).collect(Collectors.toSet());
  }

  /**
   * Convert List of CategoryOrderHint Entity to List of CategoryOrderHintView.
   *
   * @param entities the entities
   * @return the list
   */
  public static List<CategoryOrderHintView> toModel(Set<CategoryOrderHint> entities) {
    return entities.stream().map(
        entity -> {
          return new CategoryOrderHintView(entity.getCategoryId(), entity.getOrderHint());
        }
    ).collect(Collectors.toList());
  }
}