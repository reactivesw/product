package io.reactivesw.product.application.model.attribute;

import io.reactivesw.model.LocalizedString;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by umasuo on 16/11/18.
 */
@Data
@AllArgsConstructor
public class LocalizedEnumValue {

  /**
   * The key of the value used as a programmatic identifier, e.g. in facets & filters.
   */
  private String key;

  /**
   * A descriptive, localized label of the value.
   */
  private LocalizedString label;
}
