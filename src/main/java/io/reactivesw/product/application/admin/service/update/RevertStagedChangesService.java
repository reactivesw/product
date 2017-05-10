package io.reactivesw.product.application.admin.service.update;

import io.reactivesw.model.Updater;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import org.springframework.stereotype.Service;

/**
 * The type Revert staged changes service.
 */
@Service(UpdateActionUtils.REVERT_STAGED_CHANGES)
public class RevertStagedChangesService implements Updater<Product, UpdateAction> {

  /**
   * Revert staged changes.
   *
   * @param product the product entity
   * @param updateAction the RevertStagedChanges action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    // TODO: 17/5/10
  }
}
