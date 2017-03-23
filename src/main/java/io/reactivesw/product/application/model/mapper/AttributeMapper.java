package io.reactivesw.product.application.model.mapper;

import io.reactivesw.product.application.model.attribute.AttributeView;
import io.reactivesw.product.domain.model.Attribute;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Davis on 16/12/14.
 */
public class AttributeMapper {


  /**
   * Entity to model list.
   *
   * @param entities the entities
   * @return the list
   */
  public static List<AttributeView> mapToModel
  (List<Attribute> entities) {
    return entities.stream().map(
        entity -> {
          return mapToModel(entity);
        }
    ).collect(Collectors.toList());
  }

  /**
   * Map to model attribute view.
   *
   * @param entity the entity
   * @return the attribute view
   */
  public static AttributeView mapToModel(Attribute entity) {
    AttributeView model = new AttributeView();

    model.setName(entity.getName());
    model.setValue(entity.getValue());

    return model;
  }
}
