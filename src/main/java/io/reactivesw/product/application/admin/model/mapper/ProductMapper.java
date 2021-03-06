package io.reactivesw.product.application.admin.model.mapper;

import io.reactivesw.model.Reference;
import io.reactivesw.product.application.admin.model.ProductDraft;
import io.reactivesw.product.application.admin.model.ProductView;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.infrastructure.util.ReferenceTypes;

import org.apache.commons.lang3.StringUtils;

/**
 * Product Mapper class.
 * Convert ProductDraft to Product Entity,
 * or Convert Product Entity to ProductView.
 */
public final class ProductMapper {

  /**
   * Instantiates a new Product mapper.
   */
  private ProductMapper() {
  }

  /**
   * Convert ProductDraft to Product.
   *
   * @param model the ProductDraft
   * @return the Product
   */
  public static Product toEntity(ProductDraft model) {
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
   * Convert Product to ProductView.
   *
   * @param entity the Product
   * @return the ProductView
   */
  public static ProductView toModel(Product entity) {
    ProductView model = new ProductView();

    model.setCreatedAt(entity.getCreatedAt());
    model.setLastModifiedAt(entity.getLastModifiedAt());
    model.setId(entity.getId());
    model.setKey(entity.getKey());
    model.setVersion(entity.getVersion());
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
    // model.setState(entity.getState());
    // model.setReviewRatingStatistics();
    model.setMasterData(ProductCatalogDataMapper.entityToModel(entity.getMasterData()));

    return model;
  }
}
