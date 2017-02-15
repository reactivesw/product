package io.reactivesw.product.application.model.mapper;

import io.reactivesw.product.application.model.attribute.Attribute;
import io.reactivesw.product.domain.model.AttributeEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Davis on 16/12/14.
 */
public class AttributeMapper {
  public static List<AttributeEntity> modelToEntity(List<Attribute> models) {
    return models.stream().map(
        model -> {
          AttributeEntity entity = new AttributeEntity();
          entity.setName(model.getName());
          entity.setValue(model.getValue());
          return entity;
        }
    ).collect(Collectors.toList());
  }

  public static List<Attribute> entityToModel(List<AttributeEntity> entities) {
    return entities.stream().map(
        entity -> {
          return entityToModel(entity);
        }
    ).collect(Collectors.toList());
  }

  public static Attribute entityToModel(AttributeEntity entity) {
    Attribute model = new Attribute();
    
    model.setName(entity.getName());
    model.setValue(entity.getValue());

    return model;
  }
}
