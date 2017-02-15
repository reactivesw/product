package io.reactivesw.product.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by umasuo on 16/11/18.
 */
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CategoryOrderHintView {

  /**
   * use category id as key.
   */
  String key;

  /**
   * number, value between [0...1]
   */
  String order;
}
