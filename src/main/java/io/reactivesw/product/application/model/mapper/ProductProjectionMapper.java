package io.reactivesw.product.application.model.mapper;

import com.google.common.collect.Lists;

import io.reactivesw.model.Reference;
import io.reactivesw.product.application.model.ProductProjectionView;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.domain.model.ProductCatalogData;
import io.reactivesw.product.domain.model.ProductData;
import io.reactivesw.product.infrastructure.util.ReferenceTypes;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Davis on 16/12/21.
 */
public final class ProductProjectionMapper {
  /**
   * Entity to model list.
   *
   * @param entities the entities
   * @return the list
   */
  public static List<ProductProjectionView> entityToModel(List<Product> entities) {
    List<ProductProjectionView> models = Lists.newArrayList();

    models = entities.stream()
        .map(
            entity -> {
              return entityToModel(entity);
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
  public static ProductProjectionView entityToModel(Product entity) {
    ProductProjectionView model = new ProductProjectionView();

    ProductCatalogData masterData = entity.getMasterData();
    ProductData currentData = masterData.getCurrent();
    model.setId(entity.getId());
    model.setKey(entity.getKey());
    model.setName(LocalizedStringMapper.entityToModelDefaultNull(currentData.getName()));
    model.setSlug(currentData.getSlug());
    model.setDescription(LocalizedStringMapper.entityToModelDefaultNull(
        currentData.getDescription()));
    model.setCreatedAt(entity.getCreatedAt());
    model.setLastModifiedAt(entity.getLastModifiedAt());
    model.setMetaTitle(LocalizedStringMapper.entityToModelDefaultNull(currentData.getMetaTitle()));
    model.setMetaDescription(LocalizedStringMapper.entityToModelDefaultNull(
        currentData.getMetaDescription()));
    model.setMetaKeywords(LocalizedStringMapper.entityToModelDefaultNull(
        currentData.getMetaKeywords()));
    if (currentData.getCategories() != null && !currentData.getCategories().isEmpty()) {
      model.setCategories(currentData.getCategories().stream().map(
          category -> {
            return new Reference(ReferenceTypes.CATEGORY.getType(), category);
          }
      ).collect(Collectors.toList()));
    }
    if (currentData.getCategoryOrderHints() != null) {
      model.setCategoryOrderHints(CategoryOrderHintsMapper.entityToModel(
          currentData.getCategoryOrderHints()));
    }
    model.setMasterVariant(ProductVariantMapper.entityToModel(currentData.getMasterVariant()));
    model.setVariants(ProductVariantMapper.entityToModel(currentData.getVariants()));
    model.setProductType(new Reference(ReferenceTypes.PRODUCTTYPE.getType(),
        entity.getProductType()));
    model.setHasStagedChanges(masterData.getStagedChanged());
    model.setPublished(masterData.getPublished());
//    model.setSearchKeyword();
//    model.setTaxCategory();
//    model.setState();
//    model.setReviewRatingStatistics();

    return model;
  }
}
