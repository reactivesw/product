package io.reactivesw.product.application.admin.service.update;

import io.reactivesw.model.Updater;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import org.springframework.stereotype.Service;

/**
 * The type Set attribute in all variants service.
 */
@Service(UpdateActionUtils.SET_ATTRIBUTE_IN_ALL_VARIANTS)
public class SetAttributeInAllVariantsService implements Updater<Product, UpdateAction> {

  /**
   * Set attribute in all variants.
   *
   * @param product the product entity
   * @param updateAction the SetAttributeInAllVariants action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    // TODO: 17/5/10
  }
}
