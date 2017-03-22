package io.reactivesw.product.application.model.mapper;

import io.reactivesw.product.application.model.ProductVariantView;
import io.reactivesw.product.domain.model.ProductVariant;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Davis on 16/12/14.
 */
public class ProductVariantMapper {
  /**
   * Entity to model product variant.
   *
   * @param entity the entity
   * @return the product variant
   */
  public static ProductVariantView mapToModel(ProductVariant entity) {
    ProductVariantView model = new ProductVariantView();

    model.setId(entity.getId());
    model.setKey(entity.getKey());
    model.setSku(entity.getSku());
    if (entity.getPrices() != null) {
      model.setPrices(PriceMapper.mapToModel(entity.getPrices()));
    }
    if (entity.getAttributes() != null) {
      model.setAttributes(AttributeMapper.mapToModel(entity.getAttributes()));
    }
    if (entity.getImages() != null) {
      model.setImages(ImageMapper.entityToModel(entity.getImages()));
    }

    //availability will be setted in InventoryUtils.mergeInventoryEntryToProduct
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
  public static List<ProductVariantView> mapToModel(Set<ProductVariant> entities) {
    return entities.stream().map(
        entity -> {
          return mapToModel(entity);
        }
    ).collect(Collectors.toList());
  }
}
