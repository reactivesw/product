package io.reactivesw.product.application.admin.model.mapper;

import com.google.common.collect.Lists;

import io.reactivesw.product.application.model.CategoryOrderHintView;
import io.reactivesw.product.domain.model.CategoryOrderHint;

import java.util.List;
import java.util.function.Consumer;
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
  public static List<CategoryOrderHint> toEntity(List<CategoryOrderHintView> models) {
    return models.stream().map(
        model -> CategoryOrderHint.build(model.getKey(), model.getOrder())
    ).collect(Collectors.toList());
  }

  /**
   * Convert List of CategoryOrderHint Entity to List of CategoryOrderHintView.
   *
   * @param entities the entities
   * @return the list
   */
  public static List<CategoryOrderHintView> toModel(List<CategoryOrderHint> entities) {
    return entities.stream().map(
        entity ->
            new CategoryOrderHintView(entity.getCategoryId(), entity.getOrderHint())
    ).collect(Collectors.toList());
  }

  /**
   * Copy from list of CategoryOrderHint.
   *
   * @param entities the entities
   * @return the list
   */
  public static List<CategoryOrderHint> copyFrom(List<CategoryOrderHint> entities) {
    List<CategoryOrderHint> result = Lists.newArrayList();
    Consumer<CategoryOrderHint> consumer = categoryOrderHint -> result.add(CategoryOrderHint.build(
        categoryOrderHint.getCategoryId(), categoryOrderHint.getOrderHint()
    ));
    if (entities != null) {
      entities.stream().forEach(consumer);
    }
    return result;
  }
}