package io.reactivesw.product.application.admin.service.update;

import com.google.common.collect.Lists;

import io.reactivesw.model.Updater;
import io.reactivesw.product.application.admin.model.actions.AddVariant;
import io.reactivesw.product.application.admin.model.mapper.ProductVariantMapper;
import io.reactivesw.product.application.model.ProductTypeView;
import io.reactivesw.product.application.service.ProductRestClient;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.domain.service.SkuService;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;
import io.reactivesw.product.infrastructure.util.VariantUtils;
import io.reactivesw.product.infrastructure.validator.AttributeConstraintValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Add variant service.
 */
@Service(UpdateActionUtils.ADD_VARIANT)
public class AddVariantService implements Updater<Product, UpdateAction> {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(AddVariantService.class);

  /**
   * Product rest client.
   */
  @Autowired
  private transient ProductRestClient productRestClient;

  /**
   * The Sku service.
   */
  @Autowired
  private transient SkuService skuService;

  /**
   * Add variant.
   *
   * @param product the product entity
   * @param updateAction the AddVariant action
   */
  @Override
  public void handle(Product product, UpdateAction updateAction) {
    LOG.debug("Enter. ProductId: {}, update action: {}.", product.getId(), updateAction);

    ProductTypeView productTypeView = productRestClient.getProductType(product.getProductType());

    AddVariant action = (AddVariant) updateAction;
    skuService.validateSkuName(action.getSku());

    Integer variantId = VariantUtils.createNewVariantIdInStaged(product);
    if (product.getMasterData().getStaged().getVariants() == null) {
      product.getMasterData().getStaged().setVariants(
          Lists.newArrayList(ProductVariantMapper.build(variantId, action)));
    } else {
      product.getMasterData().getStaged().getVariants()
          .add(ProductVariantMapper.build(variantId, action));
    }

    AttributeConstraintValidator.validate(productTypeView.getAttributes(), product);

    LOG.trace("Updated product: {}.", product);
    LOG.debug("Exit.");
  }
}
