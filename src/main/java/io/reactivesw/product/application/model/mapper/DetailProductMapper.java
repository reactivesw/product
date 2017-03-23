package io.reactivesw.product.application.model.mapper;

import io.reactivesw.product.application.model.DetailProductView;
import io.reactivesw.product.application.model.ProductTypeView;
import io.reactivesw.product.application.model.SearchKeyword;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.domain.model.ProductData;

/**
 * Created by Davis on 17/3/22.
 */
public final class DetailProductMapper {

  /**
   * Instantiates a new Detail product mapper.
   */
  public DetailProductMapper() {
  }

  /**
   * Map to model detail product view.
   *
   * @param entity the entity
   * @return the detail product view
   */
  public static DetailProductView mapToModel(Product entity) {
    DetailProductView model = new DetailProductView();

    ProductData productData = entity.getMasterData().getCurrent();

    model.setId(entity.getId());
    model.setName(LocalizedStringMapper.mapToModelDefaultNew(productData.getName()));
    model.setDescription(LocalizedStringMapper.mapToModelDefaultNew(productData.getDescription
        ()));
    model.setMasterVariant(ProductVariantMapper.mapToModel(productData.getMasterVariant()));
    model.setMetaDescription(LocalizedStringMapper.mapToModelDefaultNew(productData
        .getMetaDescription()));
    model.setMetaKeywords(LocalizedStringMapper.mapToModelDefaultNew(productData
        .getMetaKeywords()));
    model.setMetaTitle(LocalizedStringMapper.mapToModelDefaultNew(productData.getMetaTitle()));
    model.setProductType(null);
    model.setSearchKeyword(new SearchKeyword(productData.getSearchKeyWords(), null));
    model.setVariants(ProductVariantMapper.mapToModel(productData.getVariants()));

    return model;
  }

  /**
   * Merge product type detail product view.
   *
   * @param productType   the product type
   * @param detailProduct the detail product
   * @return the detail product view
   */
  public static DetailProductView mergeProductType(ProductTypeView productType, DetailProductView
      detailProduct) {

    detailProduct.setProductType(productType);

    return detailProduct;
  }
}
