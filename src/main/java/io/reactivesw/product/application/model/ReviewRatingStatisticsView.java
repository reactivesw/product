package io.reactivesw.product.application.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Davis on 16/11/17.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReviewRatingStatisticsView {

  /**
   * Average rating of one target.
   * This number is rounded with 5 decimals.
   */
  private Float averageRating;

  /**
   * Highest rating of one target.
   */
  private Float highestRating;

  /**
   * Lowest rating of one target.
   */
  private Float lowestRating;

  /**
   * Number of ratings taken into account.
   */
  private Integer count;

  /**
   * The full distribution of the ratings.
   * The keys are the different ratings and the value are the count of review having this rating.
   * Only the used ratings appear in this object.
   */
  //TODO String should be JSON object
  private String ratingsDistribution;
}
