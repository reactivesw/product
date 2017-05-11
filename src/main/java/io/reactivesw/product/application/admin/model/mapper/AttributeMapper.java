package io.reactivesw.product.application.admin.model.mapper;

import com.google.common.collect.Lists;

import io.reactivesw.product.application.model.attribute.AttributeView;
import io.reactivesw.product.domain.model.Attribute;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Attribute mapper class.
 * For convert Attribute Entity to Attribute Model,
 * or convert Attribute Model to Attribute Entity.
 */
public final class AttributeMapper {

  /**
   * Instantiates a new Attribute mapper.
   */
  private AttributeMapper() {
  }

  /**
   * Convert List of AttributeView to List of Attribute Entity.
   *
   * @param models the models
   * @return the list
   */
  public static List<Attribute> toEntity(List<AttributeView> models) {
    return models.stream().map(
        model -> {
          Attribute entity = new Attribute();
          entity.setName(model.getName());
          entity.setValue(model.getValue());
          return entity;
        }
    ).collect(Collectors.toList());
  }

  /**
   * Convert List of Attribute Entity to List of AttributeView.
   *
   * @param entities the entities
   * @return the list
   */
  public static List<AttributeView> toModel(List<Attribute> entities) {
    return entities.stream().map(
        entity -> {
          return toModel(entity);
        }
    ).collect(Collectors.toList());
  }

  /**
   * Convert Attribute Entity to AttributeView.
   *
   * @param entity the entity
   * @return the attribute view
   */
  public static AttributeView toModel(Attribute entity) {
    AttributeView model = new AttributeView();

    model.setName(entity.getName());
    model.setValue(entity.getValue());

    return model;
  }

  /**
   * Copy from list of Attribute.
   *
   * @param entities the entities
   * @return the list
   */
  public static List<Attribute> copyFrom(List<Attribute> entities) {
    List<Attribute> result = Lists.newArrayList();

    Consumer<Attribute> consumer = attribute -> result.add(copyFrom(attribute));

    if (entities != null) {
      entities.stream().forEach(consumer);
    }

    return result;
  }

  /**
   * Copy from attribute.
   *
   * @param entity the entity
   * @return the attribute
   */
  private static Attribute copyFrom(Attribute entity) {
    Attribute result = new Attribute();

    result.setName(entity.getName());
    result.setValue(entity.getValue());

    return result;
  }
}