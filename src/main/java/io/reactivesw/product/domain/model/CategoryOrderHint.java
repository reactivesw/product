package io.reactivesw.product.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Davis on 16/12/20.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Table(name = "product_category")
public class CategoryOrderHint {

  /**
   * id.
   */
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id")
  protected String id;

  /**
   * category id.
   */
  @Column(name = "category_id")
  private String categoryId;

  /**
   * order hint.
   */
  @Column(name = "order_hint")
  private String orderHint;

  /**
   * Build category order hint.
   *
   * @param categoryId the category id
   * @param orderHint the order hint
   * @return the category order hint
   */
  public static CategoryOrderHint build(String categoryId, String orderHint) {
    CategoryOrderHint categoryOrderHint = new CategoryOrderHint();
    categoryOrderHint.setCategoryId(categoryId);
    categoryOrderHint.setOrderHint(orderHint);

    return categoryOrderHint;
  }
}