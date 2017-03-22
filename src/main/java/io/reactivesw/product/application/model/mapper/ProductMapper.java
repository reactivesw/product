package io.reactivesw.product.application.model.mapper;

import io.reactivesw.model.Reference;
import io.reactivesw.product.application.model.ProductViewOld;
import io.reactivesw.product.application.model.ProductDraft;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.infrastructure.util.ReferenceTypes;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Davis on 16/12/14.
 */
public final class ProductMapper {

  /**
   * convert ProductDraft to Product.
   *
   * @param model the ProductDraft
   * @return the Product
   */
  public static Product modelToEntity(ProductDraft model) {
    Product entity = new Product();

    entity.setKey(model.getKey());
    if (model.getState() != null) {
      entity.setState(model.getState().getId());
    }
    entity.setProductType(model.getProductType().getId());
    if (model.getTaxCategory() != null) {
      entity.setTaxCategory(model.getTaxCategory().getId());
    }
    entity.setMasterData(ProductCatalogDataMapper.modelToEntity(model));

    return entity;
  }

  /**
   * convert Product to ProductViewOld.
   *
   * @param entity the Product
   * @return the ProductViewOld
   */
  public static ProductViewOld entityToModel(Product entity) {
    ProductViewOld model = new ProductViewOld();

    model.setId(entity.getId());
    model.setKey(entity.getKey());
    if (StringUtils.isNotBlank(entity.getTaxCategory())) {
      model.setTaxCategory(
          new Reference(ReferenceTypes.TAXCATEGORY.getType(), entity.getTaxCategory())
      );
    }
    if (StringUtils.isNotBlank(entity.getProductType())) {
      model.setProductType(
          new Reference(ReferenceTypes.TAXCATEGORY.getType(), entity.getProductType())
      );
    }
//    model.setState(entity.getState());
//    model.setReviewRatingStatistics();
    model.setMasterData(ProductCatalogDataMapper.entityToModel(entity.getMasterData()));


    return model;
  }
}
