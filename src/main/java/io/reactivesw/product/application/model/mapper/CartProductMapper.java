package io.reactivesw.product.application.model.mapper;

import io.reactivesw.product.application.model.CartProductView;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.domain.model.ProductData;
import io.reactivesw.product.domain.model.ProductVariant;

/**
 * Created by Davis on 17/3/22.
 */
public final class CartProductMapper {
  /**
   * Instantiates a new Cart product mapper.
   */
  public CartProductMapper() {
  }

  /**
   * Map to model cart product view.
   *
   * @param product the product
   * @param variant the variant
   * @return the cart product view
   */
  public static CartProductView mapToModel(Product product, ProductVariant variant) {
    CartProductView model = new CartProductView();

    ProductData productData = product.getMasterData().getCurrent();

    model.setProductId(product.getId());
    model.setName(LocalizedStringMapper.mapToModelDefaultNew(productData.getName()));
    model.setPrice(PriceMapper.mapToModel(variant.getPrices().get(0)));
    model.setImages(ImageMapper.entityToModel(variant.getImages()));
    model.setSku(variant.getSku());
    model.setVariantId(variant.getId());

    return model;
  }
}
