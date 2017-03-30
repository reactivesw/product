package io.reactivesw.product.application.model.mapper;

import io.reactivesw.product.application.model.attribute.AttributeView;
import io.reactivesw.product.domain.model.Attribute;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Davis on 16/12/14.
 */
public final class AttributeMapper {

  /**
   * Instantiates a new Attribute mapper.
   */
  private AttributeMapper() {
  }

  /**
   * Entity to model list.
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
   * Map to model attribute view.
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
}
