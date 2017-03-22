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
  @Deprecated
  String expandId;

  /**
   * The Version.
   */
  @Deprecated
  Integer version;

  /**
   * name(en="Pro T-Shirt")
   */
  String where;

  /**
   * name.em
   */
  @Deprecated
  String sort;

  /**
   * The Sort order.
   */
  @Deprecated
  String sortOrder;

  /**
   * The Page.
   */
  @Deprecated
  String page;

  /**
   * The Per page.
   */
  @Deprecated
  String perPage;

  /**
   * The Expand.
   */
  @Deprecated
  String expand;

  /**
   * The Staged.
   */
  @Deprecated
  Boolean staged;

  /**
   * The Staged id.
   */
  @Deprecated
  Boolean stagedId;
}
