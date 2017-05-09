package io.reactivesw.product.application.admin.model.actions;

import io.reactivesw.product.application.model.ImageView;
import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Add external image.
 */
@Setter
@Getter
public class AddExternalImage implements UpdateAction {


  /**
   * Variant Id for product.
   */
  private Integer variantId;

  /**
   * Image to add.
   */
  private ImageView image;

  /**
   * get update service name.
   *
   * @return update service name
   */
  @Override
  public String getActionName() {
    return UpdateActionUtils.ADD_EXTERNAL_IMAGE;
  }
}
