package io.reactivesw.product.application.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by Davis on 16/11/17.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SuggestionResult {
  /**
   * The Search keywords.
   */
  private List<SuggestionView> searchKeywords;
}
