package io.reactivesw.product.application.admin.service.update;

import io.reactivesw.model.Updater;
import io.reactivesw.product.application.admin.model.actions.SetAttributeInAllVariants;
import io.reactivesw.product.application.model.ProductTypeView;
import io.reactivesw.product.application.service.ProductRestClient;
import io.reactivesw.product.domain.model.Attribute;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.domain.model.ProductVariant;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;
import io.reactivesw.product.infrastructure.validator.AttributeConstraintValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * The type Set attribute in all variants service.
 */
@Service(UpdateActionUtils.SET_ATTRIBUTE_IN_ALL_VARIANTS)
public class SetAttributeInAllVariantsService implements Updater<Product, UpdateAction> {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(SetAttributeInAllVariantsService.class);

  /**
   * Product rest client.
   */
  @Autowired
  private transient ProductRestClient productRestClient;


  /**
   * Set attribute in all variants.
   *
   * @param product the product entity
   * @param updateAction the SetAttributeInAllVariants action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    LOG.debug("Enter. ProductId: {}, update action: {}.", product.getId(), updateAction);

    ProductTypeView productTypeView = productRestClient.getProductType(product.getProductType());

    SetAttributeInAllVariants action = (SetAttributeInAllVariants) updateAction;

    Predicate<Attribute> predicate = attribute -> attribute.getName().equals(action.getName());

    Consumer<Attribute> attributeConsumer = attribute -> attribute.setValue(action.getValue());

    product.getMasterData().getStaged().getMasterVariant().getAttributes().stream()
        .filter(predicate).forEach(attributeConsumer);

    Consumer<ProductVariant> variantConsumer = variant -> variant.getAttributes().stream()
        .filter(predicate).forEach(attributeConsumer);

    product.getMasterData().getStaged().getVariants().stream().forEach(variantConsumer);

    AttributeConstraintValidator.validate(productTypeView.getAttributes(), product);

    product.getMasterData().setStagedChanged(true);

    LOG.trace("Updated product: {}.", product);
    LOG.debug("Exit.");
  }
}
