package io.reactivesw.product.application.model.mapper;

import io.reactivesw.product.application.model.ProductCatalogDataView;
import io.reactivesw.product.application.model.ProductDraft;
import io.reactivesw.product.domain.model.ProductCatalogData;

/**
 * Created by Davis on 16/12/14.
 */
public class ProductCatalogDataMapper {
  public static ProductCatalogData modelToEntity(ProductDraft model) {
    ProductCatalogData entity = new ProductCatalogData();

    entity.setPublished(model.getPublish());
    entity.setStagedChanged(false);
    entity.setCurrent(ProductDataMapper.modelToEntity(model));
    entity.setStaged(ProductDataMapper.modelToEntity(model));

    return entity;
  }

  public static ProductCatalogDataView entityToModel(ProductCatalogData entity) {
    ProductCatalogDataView model = new ProductCatalogDataView();

    model.setPublished(entity.getPublished());
    model.setHasStagedChanges(entity.getStagedChanged());
    model.setCurrent(ProductDataMapper.entityToModel(entity.getCurrent()));
    model.setStaged(ProductDataMapper.entityToModel(entity.getStaged()));

    return model;
  }
}
