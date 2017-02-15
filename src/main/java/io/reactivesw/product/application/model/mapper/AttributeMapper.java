package io.reactivesw.product.application.model.mapper;

import io.reactivesw.product.application.model.attribute.AttributeView;
import io.reactivesw.product.domain.model.Attribute;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Davis on 16/12/14.
 */
public class AttributeMapper {
  public static List<Attribute> modelToEntity(List<AttributeView> models) {
    return models.stream().map(
        model -> {
          Attribute entity = new Attribute();
          entity.setName(model.getName());
          entity.setValue(model.getValue());
          return entity;
        }
    ).collect(Collectors.toList());
  }

  public static List<io.reactivesw.product.application.model.attribute.AttributeView> entityToModel(List<Attribute> entities) {
    return entities.stream().map(
        entity -> {
          return entityToModel(entity);
        }
    ).collect(Collectors.toList());
  }

  public static io.reactivesw.product.application.model.attribute.AttributeView entityToModel(Attribute entity) {
    io.reactivesw.product.application.model.attribute.AttributeView model = new io.reactivesw.product.application.model.attribute.AttributeView();
    
    model.setName(entity.getName());
    model.setValue(entity.getValue());

    return model;
  }
}
