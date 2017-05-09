package io.reactivesw.product.application.admin.model.actions;

import io.reactivesw.product.infrastructure.update.UpdateAction;
import io.reactivesw.product.infrastructure.util.UpdateActionUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Remove image.
 */
@Setter
@Getter
public class RemoveImage implements UpdateAction {


  /**
   * Variant Id for product.
   */
  private Integer variantId;

  /**
   * The URL of the image.
   */
  private String imageUrl;

  /**
   * Get update service name.
   *
   * @return update service name
   */
  @Override
  public String getActionName() {
    return UpdateActionUtils.REMOVE_IMAGE;
  }
}
