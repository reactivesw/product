package io.reactivesw.product.infrastructure.util;

import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;

import java.util.List;

/**
 * The type Fuzzy match utils.
 */
public final class FuzzyMatchUtils {

  /**
   * The RATIO.
   */
  private static final Integer RATIO = 83;

  /**
   * Instantiates a new Fuzzy match utils.
   */
  private FuzzyMatchUtils() {
  }

  /**
   * Is fuzzy match.
   *
   * @param searchWords the search words
   * @param comparedWords the compared words
   * @return the boolean
   */
  public static boolean isFuzzyMatch(String searchWords, List<String> comparedWords) {
    boolean result = false;
    ExtractedResult extractedResult = FuzzySearch.extractOne(searchWords, comparedWords);
    if (extractedResult != null && extractedResult.getScore() >= RATIO) {
      result = true;
    }
    return result;
  }
}
