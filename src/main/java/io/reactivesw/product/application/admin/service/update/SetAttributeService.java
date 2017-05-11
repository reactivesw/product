package io.reactivesw.product.application.admin.service.update;

import io.reactivesw.model.Updater;
import io.reactivesw.product.application.admin.model.actions.SetAttribute;
import io.reactivesw.product.application.model.ProductTypeView;
import io.reactivesw.product.application.service.ProductRestClient;
import io.reactivesw.product.domain.model.Attribute;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.domain.model.ProductVariant;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;
import io.reactivesw.product.infrastructure.util.VariantUtils;
import io.reactivesw.product.infrastructure.validator.AttributeConstraintValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * The type set attribute service.
 */
@Service(UpdateActionUtils.SET_ATTRIBUTE)
public class SetAttributeService implements Updater<Product, UpdateAction> {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(SetAttributeService.class);

  /**
   * Product rest client.
   */
  @Autowired
  private transient ProductRestClient productRestClient;

  /**
   * Set attribute.
   *
   * @param product the product entity
   * @param updateAction the SetAttribute action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    LOG.debug("Enter. ProductId: {}, update action: {}.", product.getId(), updateAction);

    ProductTypeView productTypeView = productRestClient.getProductType(product.getProductType());

    SetAttribute action = (SetAttribute) updateAction;

    ProductVariant variant = VariantUtils.getStagedVariant(product, action.getVariantId());

    Predicate<Attribute> predicate = attribute -> attribute.getName().equals(action.getName());

    Consumer<Attribute> consumer = attribute -> attribute.setValue(action.getValue());

    variant.getAttributes().stream().filter(predicate).forEach(consumer);

    AttributeConstraintValidator.validate(productTypeView.getAttributes(), product);

    product.getMasterData().setStagedChanged(true);

    LOG.trace("Updated product: {}.", product);
    LOG.debug("Exit.");
  }
}
