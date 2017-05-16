package io.reactivesw.product.application.admin.service.update;

import io.reactivesw.model.Updater;
import io.reactivesw.product.application.admin.model.actions.AddToCategory;
import io.reactivesw.product.domain.model.CategoryOrderHint;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;
import io.reactivesw.product.infrastructure.validator.CategoryValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * The type Add to category service.
 */
@Service(UpdateActionUtils.ADD_TO_CATEGORY)
public class AddToCategoryService implements Updater<Product, UpdateAction> {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(AddToCategoryService.class);

  /**
   * Add to category.
   *
   * @param product the product entity
   * @param updateAction the AddToCategory action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    LOG.debug("Enter. ProductId: {}, update action: {}.", product.getId(), updateAction);

    AddToCategory action = (AddToCategory) updateAction;

    CategoryValidator.validateCategory(action.getCategory());
    String categoryId = action.getCategory().getId();

    if (product.getMasterData().getStaged().getCategories().contains(categoryId)) {
      LOG.debug("Category list has contained this categoryId: {}.", categoryId);
      return;
    }

    product.getMasterData().getStaged().getCategories().add(categoryId);
    product.getMasterData().getStaged().getCategoryOrderHints()
        .add(CategoryOrderHint.build(categoryId));

    product.getMasterData().setStagedChanged(true);

    LOG.trace("Updated product: {}.", product);
    LOG.debug("Exit.");
  }
}
