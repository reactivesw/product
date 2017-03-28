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
  private DetailProductMapper() {
  }

  /**
   * Map to model detail product view.
   *
   * @param entity the entity
   * @return the detail product view
   */
  public static DetailProductView toModel(Product entity) {
    DetailProductView model = new DetailProductView();

    ProductData productData = entity.getMasterData().getCurrent();

    model.setId(entity.getId());
    model.setName(LocalizedStringMapper.toModelDefaultNew(productData.getName()));
    model.setDescription(LocalizedStringMapper.toModelDefaultNew(productData.getDescription()));
    model.setMasterVariant(ProductVariantMapper.toModel(productData.getMasterVariant()));
    model.setMetaDescription(LocalizedStringMapper.toModelDefaultNew(productData
        .getMetaDescription()));
    model.setMetaKeywords(LocalizedStringMapper.toModelDefaultNew(productData
        .getMetaKeywords()));
    model.setMetaTitle(LocalizedStringMapper.toModelDefaultNew(productData.getMetaTitle()));
    model.setProductType(null);
    model.setSearchKeyword(new SearchKeyword(productData.getSearchKeyWords(), null));
    model.setVariants(ProductVariantMapper.toModel(productData.getVariants()));

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
