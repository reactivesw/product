package io.reactivesw.product.application.model.mapper;

import io.reactivesw.product.application.model.ProductVariantView;
import io.reactivesw.product.domain.model.ProductVariant;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ProductVariant Mapper class.
 * Convert ProductVariant to ProductVariantView.
 */
public final class ProductVariantMapper {

  /**
   * Instantiates a new Product variant mapper.
   */
  private ProductVariantMapper() {
  }

  /**
   * Convert ProductVariant Entity to ProductVariantView.
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
   * Convert List of ProductVariant Entity to List of ProductVariantView.
   *
   * @param entities the entities
   * @return the list
   */
  public static List<ProductVariantView> toModel(List<ProductVariant> entities) {
    return entities.stream().map(
        entity -> {
          return toModel(entity);
        }
    ).collect(Collectors.toList());
  }
}