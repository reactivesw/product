package io.reactivesw.product.application.admin.model.actions;

import io.reactivesw.product.application.admin.model.PriceDraft;
import io.reactivesw.product.application.model.ImageView;
import io.reactivesw.product.application.model.attribute.AttributeView;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Davis on 17/5/9.
 */
@Setter
@Getter
public class AddVariant implements UpdateAction {

  /**
   * Sku for variant.
   */
  private String sku;

  /**
   * Key for variant.
   */
  private String key;

  /**
   * Prices for variant.
   */
  private List<PriceDraft> prices;

  /**
   * Images for variant.
   */
  private List<ImageView> images;

  /**
   * Attributes for variant.
   */
  private List<AttributeView> attributes;

  /**
   * get update service name.
   *
   * @return update service name
   */
  @Override
  public String getActionName() {
    return UpdateActionUtils.ADD_VARIANT;
  }
}
