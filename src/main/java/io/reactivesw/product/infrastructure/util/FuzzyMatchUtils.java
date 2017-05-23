package io.reactivesw.product.infrastructure.util;

import java.util.List;
import java.util.stream.Collectors;

import me.xdrop.fuzzywuzzy.FuzzySearch;

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
    int maxRatio = comparedWords.stream().map(
        string -> FuzzySearch.tokenSortRatio(searchWords, string)
    ).collect(Collectors.toList()).stream().max(Integer::compare).get();

    if (maxRatio >= RATIO) {
      result = true;
    }

    return result;
  }
}
