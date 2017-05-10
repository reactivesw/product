package io.reactivesw.product.application.admin.service.update;

import io.reactivesw.model.Updater;
import io.reactivesw.product.application.admin.model.actions.RemoveImage;
import io.reactivesw.product.domain.model.Image;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.domain.model.ProductVariant;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;
import io.reactivesw.product.infrastructure.util.VariantUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

/**
 * The type Remove image service.
 */
@Service(UpdateActionUtils.REMOVE_IMAGE)
public class RemoveImageService implements Updater<Product, UpdateAction> {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(RemoveImageService.class);

  /**
   * Remove image.
   *
   * @param product the product entity
   * @param updateAction the RemoveImage action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    LOG.debug("Enter. ProductId: {}, update action: {}.", product.getId(), updateAction);

    RemoveImage action = (RemoveImage) updateAction;
    ProductVariant variant = VariantUtils.getStagedVariant(product, action.getVariantId());

    String imageUrl = action.getImageUrl();

    if (variant.getImages() == null || variant.getImages().isEmpty()) {
      LOG.debug("Image is null, can not remove this image: {}.", imageUrl);
    } else {
      Predicate<Image> predicate = image -> image.getUrl().equals(imageUrl);
      variant.getImages().removeIf(predicate);
    }

    product.getMasterData().setStagedChanged(true);

    LOG.trace("Updated product: {}.", product);
    LOG.debug("Exit.");
  }
}
