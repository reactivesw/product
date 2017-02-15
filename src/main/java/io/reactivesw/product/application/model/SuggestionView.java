package io.reactivesw.product.application.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Davis on 16/11/17.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SuggestionView {
  /**
   * The suggested text.
   */
  private String text;
}
