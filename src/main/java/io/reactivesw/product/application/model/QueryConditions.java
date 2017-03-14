package io.reactivesw.product.application.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Davis on 16/11/21.
 */
@Getter
@Setter
@ToString
public class QueryConditions {

  /**
   * The Expand id.
   */
  String expandId;

  /**
   * The Version.
   */
  Integer version;

  /**
   * name(en="Pro T-Shirt")
   */
  String where;

  /**
   * name.em
   */
  String sort;

  /**
   * The Sort order.
   */
  String sortOrder;

  /**
   * The Page.
   */
  String page;

  /**
   * The Per page.
   */
  String perPage;

  /**
   * The Expand.
   */
  String expand;

  /**
   * The Staged.
   */
  Boolean staged;

  /**
   * The Staged id.
   */
  Boolean stagedId;
}
