package io.reactivesw.product.application.admin.service.update;

import io.reactivesw.model.Updater;
import io.reactivesw.product.application.admin.model.actions.ChangeMasterVariant;
import io.reactivesw.product.application.admin.model.mapper.ProductVariantMapper;
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
 * The type Change master variant service.
 */
@Service(UpdateActionUtils.CHANGE_MASTER_VARIANT)
public class ChangeMasterVariantService implements Updater<Product, UpdateAction> {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(ChangeMasterVariantService.class);

  /**
   * Change master variant.
   *
   * @param product the product entity
   * @param updateAction the ChangeMasterVariant action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    LOG.debug("Enter. ProductId: {}, update action: {}.", product.getId(), updateAction);

    ChangeMasterVariant action = (ChangeMasterVariant) updateAction;

    Integer variantId = action.getVariantId();

    if (variantId.equals(VariantUtils.MASTER_VARIANT_ID)) {
      LOG.debug("Can not change master variant to master variant.");
      return;
    }

    setVariantAsMasterVariant(product, variantId);
    moveMasterVariantToVariants(product);

    LOG.trace("Updated product: {}.", product);
    LOG.debug("Exit.");
  }

  /**
   * Move master variant to variants.
   *
   * @param product the product
   */
  private void moveMasterVariantToVariants(Product product) {

    ProductVariant oldMasterVariant = product.getMasterData().getStaged().getMasterVariant();

    ProductVariant newVariant = ProductVariantMapper.copyFrom(oldMasterVariant);
    Integer newVariantId = VariantUtils.createNewVariantIdInStaged(product);
    newVariant.setId(newVariantId);

    product.getMasterData().getStaged().getVariants().add(newVariant);
  }

  /**
   * Sets variant as master variant.
   *
   * @param product the product
   * @param variantId the variant id
   */
  private void setVariantAsMasterVariant(Product product, Integer variantId) {
    ProductVariant variant = VariantUtils.getStagedVariant(product, variantId);

    ProductVariant newMasterVariant = ProductVariantMapper.copyFrom(variant);
    newMasterVariant.setId(VariantUtils.MASTER_VARIANT_ID);

    Predicate<ProductVariant> predicate = productVariant ->
        productVariant.getId().equals(variantId);
    product.getMasterData().getStaged().getVariants().removeIf(predicate);

    product.getMasterData().getStaged().setMasterVariant(newMasterVariant);
  }
}
