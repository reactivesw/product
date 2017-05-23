package io.reactivesw.product.application.model.mapper;

import com.google.common.collect.Lists;

import io.reactivesw.product.application.model.CategoryProductView;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.domain.model.ProductData;
import io.reactivesw.product.domain.model.ProductVariant;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Davis on 16/12/21.
 */
public final class CategoryProductMapper {

  /**
   * Instantiates a new Category product mapper.
   */
  private CategoryProductMapper() {
  }

  /**
   * Entity to model list.
   *
   * @param entities the entities
   * @return the list
   */
  public static List<CategoryProductView> toModel(List<Product> entities) {
    List<CategoryProductView> models = Lists.newArrayList();

    models = entities.stream()
        .map(
            entity -> {
              return toModel(entity);
            }
        ).collect(Collectors.toList());

    return models;
  }

  /**
   * Entity to model product projection.
   *
   * @param entity the entity
   * @return the product projection
   */
  public static CategoryProductView toModel(Product entity) {
    CategoryProductView model = new CategoryProductView();

    ProductData productData = entity.getMasterData().getCurrent();
    ProductVariant masterVariant = productData.getMasterVariant();

    model.setId(entity.getId());
    model.setSku(masterVariant.getSku());
    model.setName(LocalizedStringMapper.toModelDefaultNew(productData.getName()));
    if (masterVariant.getImages() != null && !masterVariant.getImages().isEmpty()) {
      model.setImageUrl(masterVariant.getImages().get(0).getUrl());
    }
    if (masterVariant.getPrices() != null && !masterVariant.getPrices().isEmpty()) {
      model.setPrice(PriceMapper.toModel(masterVariant.getPrices().get(0)));
    }
    model.setAvailable(false);

    return model;
  }


  /**
   * Convert to CategoryProductView.
   *
   * @param entity the entity
   * @param variant the variant
   * @return the category product view
   */
  public static CategoryProductView toModel(Product entity, ProductVariant variant) {
    CategoryProductView model = new CategoryProductView();

    ProductData productData = entity.getMasterData().getCurrent();

    model.setId(entity.getId());
    model.setSku(variant.getSku());
    model.setName(LocalizedStringMapper.toModelDefaultNew(productData.getName()));
    if (variant.getImages() != null && !variant.getImages().isEmpty()) {
      model.setImageUrl(variant.getImages().get(0).getUrl());
    }
    if (variant.getPrices() != null && !variant.getPrices().isEmpty()) {
      model.setPrice(PriceMapper.toModel(variant.getPrices().get(0)));
    }
    model.setAvailable(false);

    return model;
  }
}
