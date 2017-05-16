package io.reactivesw.product.application.admin.service.update;

import com.google.common.collect.Lists;

import io.reactivesw.model.Updater;
import io.reactivesw.product.application.admin.model.actions.AddExternalImage;
import io.reactivesw.product.application.admin.model.mapper.ImageMapper;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.domain.model.ProductVariant;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;
import io.reactivesw.product.infrastructure.util.VariantUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * The type Add external image service.
 */
@Service(UpdateActionUtils.ADD_EXTERNAL_IMAGE)
public class AddExternalImageService implements Updater<Product, UpdateAction> {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(AddExternalImageService.class);

  /**
   * Add external image.
   *
   * @param product the product entity
   * @param updateAction the AddExternalImage action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    LOG.debug("Enter. ProductId: {}, update action: {}.", product.getId(), updateAction);

    AddExternalImage action = (AddExternalImage) updateAction;
    ProductVariant variant = VariantUtils.getStagedVariant(product, action.getVariantId());

    if (variant.getImages() == null || variant.getImages().isEmpty()) {
      variant.setImages(Lists.newArrayList(ImageMapper.toEntity(action.getImage())));
    } else {
      variant.getImages().add(ImageMapper.toEntity(action.getImage()));
    }

    product.getMasterData().setStagedChanged(true);

    LOG.trace("Updated product: {}.", product);
    LOG.debug("Exit.");
  }
}
