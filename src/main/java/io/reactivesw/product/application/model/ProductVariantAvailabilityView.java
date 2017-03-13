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

  private Boolean isOnStock;

  private Integer restockableInDays;

  private Integer availableQuantity;

  private Map<String, ProductVariantAvailabilityView> channels;
}
