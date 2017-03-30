package io.reactivesw.product.application.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * Created by umasuo on 16/11/17.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductVariantAvailabilityView {

  /**
   * The Is on stock.
   */
  private Boolean isOnStock;

  /**
   * The Restockable in days.
   */
  private Integer restockableInDays;

  /**
   * The Available quantity.
   */
  private Integer availableQuantity;

  /**
   * The Channels.
   */
  private Map<String, ProductVariantAvailabilityView> channels;
}
