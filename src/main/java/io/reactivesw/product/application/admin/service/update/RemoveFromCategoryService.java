package io.reactivesw.product.application.admin.service.update;

import io.reactivesw.model.Updater;
import io.reactivesw.product.application.admin.model.actions.RemoveFromCategory;
import io.reactivesw.product.domain.model.CategoryOrderHint;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

/**
 * The type Remove from category service.
 */
@Service(UpdateActionUtils.REMOVE_FROM_CATEGORY)
public class RemoveFromCategoryService implements Updater<Product, UpdateAction> {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(RemoveFromCategoryService.class);

  /**
   * Remove from category.
   *
   * @param product the product action
   * @param updateAction the RemoveFromCategory action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    LOG.debug("Enter. ProductId: {}, update action: {}.", product.getId(), updateAction);

    RemoveFromCategory action = (RemoveFromCategory) updateAction;

    // Remove category id.
    Predicate<String> categoryPredicate = string -> string.equals(action.getCategory().getId());
    product.getMasterData().getStaged().getCategories().removeIf(categoryPredicate);

    // Remove category orderHint.
    Predicate<CategoryOrderHint> orderHintPredicate = categoryOrderHint ->
        categoryOrderHint.getCategoryId().endsWith(action.getCategory().getId());
    product.getMasterData().getStaged().getCategoryOrderHints().removeIf(orderHintPredicate
    );
    
    product.getMasterData().setStagedChanged(true);

    LOG.trace("Updated product: {}.", product);
    LOG.debug("Exit.");
  }
}
