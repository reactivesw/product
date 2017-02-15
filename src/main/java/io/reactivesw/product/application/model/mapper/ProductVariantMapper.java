package io.reactivesw.product.application.model.mapper;

import com.google.common.collect.Sets;

import io.reactivesw.product.application.model.ProductVariantView;
import io.reactivesw.product.application.model.ProductVariantDraft;
import io.reactivesw.product.domain.model.ProductVariant;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Davis on 16/12/14.
 */
public class ProductVariantMapper {

  /**
   * Model to entity set.
   *
   * @param models the models
   * @return the set
   */
  public static Set<ProductVariant> modelToEntity(List<ProductVariantDraft> models) {
    Set entities = Sets.newHashSet();
    for (int i = 0; i < models.size(); i ++) {
      int id = i + 2;
      ProductVariant entity = modelToEntity(id, models.get(i));
      entities.add(entity);
    }
    return entities;
  }

  /**
   * Model to product variant entity.
   *
   * @param id    the id
   * @param model the model
   * @return the product variant entity
   */
  public static ProductVariant modelToEntity(int id, ProductVariantDraft model) {
    ProductVariant entity = new ProductVariant();

    entity.setId(id);
    entity.setKey(model.getKey());
    entity.setSku(model.getSku());
    entity.setImages(ImageMapper.modelToEntity(model.getImages()));
    if (model.getPrices() != null) {
      entity.setPrices(PriceMapper.modelToEntity(model.getPrices()));
    }
    if (model.getAttributes() != null) {
      entity.setAttributes(AttributeMapper.modelToEntity(model.getAttributes()));
    }

    return entity;
  }

  /**
   * Entity to model product variant.
   *
   * @param entity the entity
   * @return the product variant
   */
  public static ProductVariantView entityToModel(ProductVariant entity) {
    ProductVariantView model = new ProductVariantView();

    model.setId(entity.getId());
    model.setKey(entity.getKey());
    model.setSku(entity.getSku());
    if (entity.getPrices() != null) {
      model.setPrices(PriceMapper.entityToModel(entity.getPrices()));
    }
    if (entity.getAttributes() != null) {
      model.setAttributes(AttributeMapper.entityToModel(entity.getAttributes()));
    }
    if (entity.getImages() != null) {
      model.setImages(ImageMapper.entityToModel(entity.getImages()));
    }

    //availability will be setted in ProductInventoryUtils.mergeInventoryEntryToProduct
    //model.setAvailability();

    // TODO: 16/12/20
//    model.setIsMatchingVariant();
//    model.setPrice();
//    model.setScopedPrice();
//    model.setScopedPriceDiscounted();

    return model;
  }

  /**
   * Entity to model list.
   *
   * @param entities the entities
   * @return the list
   */
  public static List<ProductVariantView> entityToModel(Set<ProductVariant> entities) {
    return entities.stream().map(
        entity -> {
          return entityToModel(entity);
        }
    ).collect(Collectors.toList());
  }
}
