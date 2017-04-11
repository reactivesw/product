package io.reactivesw.product.application.admin.model.mapper;

import com.google.common.collect.Sets;

import io.reactivesw.product.application.admin.model.ProductVariantDraft;
import io.reactivesw.product.application.model.ProductVariantView;
import io.reactivesw.product.domain.model.ProductVariant;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ProductVariant Mapper class.
 * Convert ProductVariant to ProductVariantView,
 * or Convert ProductVariantDraft to ProductVariant Entity.
 */
public final class ProductVariantMapper {

  /**
   * Instantiates a new Product variant mapper.
   */
  private ProductVariantMapper() {
  }

  /**
   * Convert List of ProductVariantDraft to List of ProductVariant Entity.
   * Set ProductVariant id.
   *
   * @param models the models
   * @return the set
   */
  public static Set<ProductVariant> toEntity(List<ProductVariantDraft> models) {
    Set entities = Sets.newHashSet();
    for (int i = 0; i < models.size(); i++) {
      int id = i + 2;
      ProductVariant entity = toEntity(id, models.get(i));
      entities.add(entity);
    }
    return entities;
  }

  /**
   * Convert ProductVariantDraft to ProductVariant Entity.
   *
   * @param id    the id
   * @param model the model
   * @return the product variant entity
   */
  public static ProductVariant toEntity(int id, ProductVariantDraft model) {
    ProductVariant entity = new ProductVariant();

    entity.setId(id);
    entity.setKey(model.getKey());
    entity.setSku(model.getSku());
    entity.setImages(ImageMapper.toEntity(model.getImages()));
    if (model.getPrices() != null) {
      entity.setPrices(PriceMapper.toEntity(model.getPrices()));
    }
    if (model.getAttributes() != null) {
      entity.setAttributes(AttributeMapper.toEntity(model.getAttributes()));
    }

    return entity;
  }

  /**
   * Convert ProductVariant to ProductVariantView.
   *
   * @param entity the entity
   * @return the product variant
   */
  public static ProductVariantView toModel(ProductVariant entity) {
    ProductVariantView model = new ProductVariantView();

    model.setId(entity.getId());
    model.setKey(entity.getKey());
    model.setSku(entity.getSku());
    if (entity.getPrices() != null) {
      model.setPrices(PriceMapper.entityToModel(entity.getPrices()));
    }
    if (entity.getAttributes() != null) {
      model.setAttributes(AttributeMapper.toModel(entity.getAttributes()));
    }
    if (entity.getImages() != null) {
      model.setImages(ImageMapper.toModel(entity.getImages()));
    }

    //availability will be setted in ProductInventoryUtils.mergeInventoryEntryToProduct
    //model.setAvailability();

    // TODO: 16/12/20
    // model.setIsMatchingVariant();
    // model.setPrice();
    // model.setScopedPrice();
    // model.setScopedPriceDiscounted();

    return model;
  }

  /**
   * Convert List of ProductVariant to List of ProductVariantView.
   *
   * @param entities the entities
   * @return the list
   */
  public static List<ProductVariantView> toModel(Set<ProductVariant> entities) {
    return entities.stream().map(
        entity -> {
          return toModel(entity);
        }
    ).collect(Collectors.toList());
  }
}