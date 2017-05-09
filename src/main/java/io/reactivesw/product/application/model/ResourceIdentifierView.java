package io.reactivesw.product.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Davis on 16/11/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourceIdentifierView {

  /**
   * The Type id.
   */
  private String typeId;

  /**
   * The Id.
   */
  private String id;
}