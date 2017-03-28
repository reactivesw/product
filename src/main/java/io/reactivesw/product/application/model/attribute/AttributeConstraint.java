package io.reactivesw.product.application.model.attribute;

/**
 * Created by Davis on 16/11/16.
 */
public enum AttributeConstraint {
  /**
   * None attribute constraint.
   */
  None,

  /**
   * Unique attribute constraint.
   */
  Unique,

  /**
   * Combination unique attribute constraint.
   */
  CombinationUnique,

  /**
   * Same for all attribute constraint.
   */
  SameForAll;
}
