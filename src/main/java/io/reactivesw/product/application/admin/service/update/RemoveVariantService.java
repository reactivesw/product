package io.reactivesw.product.application.admin.service.update;

import io.reactivesw.model.Updater;
import io.reactivesw.product.application.admin.model.actions.RemoveVariant;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.domain.model.ProductVariant;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

/**
 * The type Remove variant service.
 */
@Service(UpdateActionUtils.REMOVE_VARIANT)
public class RemoveVariantService implements Updater<Product, UpdateAction> {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(RemoveVariantService.class);

  /**
   * Remove variant.
   *
   * @param product the product entity
   * @param updateAction the RemoveVariant action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    LOG.debug("Enter. ProductId: {}, update action: {}.", product.getId(), updateAction);

    RemoveVariant action = (RemoveVariant) updateAction;
    Integer variantId = action.getVariantId();
    if (variantId.equals(1)) {
      LOG.debug("Can not remove master variant");
    } else {
      Predicate<ProductVariant> variantPredicate = productVariant ->
          productVariant.getId().equals(variantId);
      product.getMasterData().getStaged().getVariants().removeIf(variantPredicate);
      product.getMasterData().setStagedChanged(true);
    }

    LOG.trace("Updated product: {}.", product);
    LOG.debug("Exit.");
  }
}
