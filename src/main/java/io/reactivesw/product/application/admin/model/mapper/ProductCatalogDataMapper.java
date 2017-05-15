package io.reactivesw.product.application.admin.model.mapper;

import io.reactivesw.product.application.admin.model.ProductDraft;
import io.reactivesw.product.application.model.ProductCatalogDataView;
import io.reactivesw.product.domain.model.ProductCatalogData;

/**
 * ProductCatalogData Mapper class.
 * Convert ProductDraft to ProductCatalogData Entity,
 * or Convert ProductCatalogData Entity to ProductCatalogDataView.
 */
public final class ProductCatalogDataMapper {

  /**
   * Instantiates a new Product catalog data mapper.
   */
  private ProductCatalogDataMapper() {
  }

  /**
   * Convert ProductDraft to ProductCatalogData Entity.
   *
   * @param model the model
   * @return the product catalog data
   */
  public static ProductCatalogData toEntityOnlyStaged(ProductDraft model) {
    ProductCatalogData entity = new ProductCatalogData();

    entity.setPublished(model.getPublish());
    entity.setStagedChanged(false);
//    entity.setCurrent(ProductDataMapper.toEntity(model));
    entity.setStaged(ProductDataMapper.toEntity(model));

    return entity;
  }

  /**
   * Convert ProductCatalogData Entity to ProductCatalogDataView.
   *
   * @param entity the entity
   * @return the product catalog data view
   */
  public static ProductCatalogDataView toModel(ProductCatalogData entity) {
    ProductCatalogDataView model = new ProductCatalogDataView();

    model.setPublished(entity.getPublished());
    model.setHasStagedChanges(entity.getStagedChanged());
    if (entity.getCurrent() != null) {
      model.setCurrent(ProductDataMapper.toModel(entity.getCurrent()));
    }
    model.setStaged(ProductDataMapper.toModel(entity.getStaged()));

    return model;
  }
}
