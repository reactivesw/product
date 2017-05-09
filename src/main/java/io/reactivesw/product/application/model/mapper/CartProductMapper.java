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
  private CartProductMapper() {
  }

  /**
   * Map to model cart product view.
   *
   * @param product the product
   * @param variant the variant
   * @return the cart product view
   */
  public static CartProductView toModel(Product product, ProductVariant variant) {
    CartProductView model = new CartProductView();

    ProductData productData = product.getMasterData().getCurrent();

    model.setProductId(product.getId());
    model.setName(LocalizedStringMapper.toModelDefaultNew(productData.getName()));
    model.setPrice(PriceMapper.toModel(variant.getPrices().get(0)));
    model.setImages(ImageMapper.toModel(variant.getImages()));
    model.setSku(variant.getSku());
    model.setVariantId(variant.getId());

    return model;
  }
}
