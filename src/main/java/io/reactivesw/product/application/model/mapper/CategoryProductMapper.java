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
   * Entity to model list.
   *
   * @param entities the entities
   * @return the list
   */
  public static List<CategoryProductView> mapToModel(List<Product> entities) {
    List<CategoryProductView> models = Lists.newArrayList();

    models = entities.stream()
        .map(
            entity -> {
              return mapToModel(entity);
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
  public static CategoryProductView mapToModel(Product entity) {
    CategoryProductView model = new CategoryProductView();

    ProductData productData = entity.getMasterData().getCurrent();
    ProductVariant masterVariant = productData.getMasterVariant();

    model.setId(entity.getId());
    model.setSku(masterVariant.getSku());
    model.setName(LocalizedStringMapper.mapToModelDefaultNew(productData.getName()));
    if (masterVariant.getImages() != null && !masterVariant.getImages().isEmpty()) {
      model.setImageUrl(masterVariant.getImages().get(0).getUrl());
    }
    if (masterVariant.getPrices() != null && !masterVariant.getPrices().isEmpty()) {
      model.setPrice(PriceMapper.mapToModel(masterVariant.getPrices().get(0)));
    }
    model.setAvailable(false);

    return model;
  }
}
