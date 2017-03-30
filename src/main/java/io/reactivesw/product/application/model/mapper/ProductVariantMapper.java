package io.reactivesw.product.application.model.mapper;

import io.reactivesw.product.application.model.ProductVariantView;
import io.reactivesw.product.domain.model.ProductVariant;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Davis on 16/12/14.
 */
public final class ProductVariantMapper {

  /**
   * Instantiates a new Product variant mapper.
   */
  private ProductVariantMapper() {
  }

  /**
   * Entity to model product variant.
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
      model.setPrices(PriceMapper.toModel(entity.getPrices()));
      model.setPrice(PriceMapper.toModel(entity.getPrices().get(0)));
    }
    if (entity.getAttributes() != null) {
      model.setAttributes(AttributeMapper.toModel(entity.getAttributes()));
    }
    if (entity.getImages() != null) {
      model.setImages(ImageMapper.toModel(entity.getImages()));
    }

    //availability will be setted in InventoryUtils.mergeInventoryEntryToProduct
    //model.setAvailability();

    // TODO: 16/12/20
    //model.setIsMatchingVariant();
    //model.setScopedPrice();
    //model.setScopedPriceDiscounted();

    return model;
  }

  /**
   * Entity to model list.
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
