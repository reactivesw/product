package io.reactivesw.product.application.admin.service.update;

import io.reactivesw.model.Updater;
import io.reactivesw.product.application.admin.model.actions.SetCategoryOrderHint;
import io.reactivesw.product.domain.model.CategoryOrderHint;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.OrderHintUtils;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * The type Set category order hint service.
 */
@Service(UpdateActionUtils.SET_CATEGORY_ORDER_HINT)
public class SetCategoryOrderHintService implements Updater<Product, UpdateAction> {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(SetCategoryOrderHintService.class);

  /**
   * Set category orderHint.
   *
   * @param product the product entity
   * @param updateAction the SetCategoryOrderHint action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    LOG.debug("Enter. ProductId: {}, update action: {}.", product.getId(), updateAction);

    SetCategoryOrderHint action = (SetCategoryOrderHint) updateAction;

    String categoryId = action.getCategoryId();

    if (! product.getMasterData().getStaged().getCategories().contains(categoryId)) {
      LOG.debug("Category list do not contain this categoryId: {}.", categoryId);
      return;
    }

    String newOrderHint = calculateOrderHint(action);

    Predicate<CategoryOrderHint> predicate = orderHint ->
        orderHint.getCategoryId().equals(categoryId);

    Consumer<CategoryOrderHint> consumer = orderHint -> orderHint.setOrderHint(newOrderHint);

    product.getMasterData().getStaged().getCategoryOrderHints().stream()
        .filter(predicate).forEach(consumer);

    product.getMasterData().setStagedChanged(true);

    LOG.trace("Updated product: {}.", product);
    LOG.debug("Exit.");
  }


  /**
   * Calculate order hint.
   *
   * @param action the action
   * @return the string
   */
  private String calculateOrderHint(SetCategoryOrderHint action) {

    String result = "";

    if (StringUtils.isBlank(action.getNextOrderHint())) {
      //    Category is changed to the last one.
      result = OrderHintUtils.createOrderHint();
    } else {
      BigDecimal previous = new BigDecimal(action.getPreviousOrderHint());
      BigDecimal next = new BigDecimal(action.getNextOrderHint());
      result = String.valueOf(previous.add(next).divide(new BigDecimal(2)));
    }
    return result;
  }
}
