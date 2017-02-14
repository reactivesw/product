package io.reactivesw.product.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Created by umasuo on 16/11/23.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "catalog_product")
public class ProductEntity {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id")
  protected String id;

  /**
   * key value.
   */
  @Column(name = "key_value")
  private String key;

  /**
   * version.
   */
  @Version
  @Column
  private Integer version;

  /**
   * product type id.
   */
  @Column(name = "product_type_id")
  private String productType;

  /**
   * master data.
   */
  @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
  private ProductCatalogDataEntity masterData;

  /**
   * tax category id.
   */
  @Column(name = "tax_category_id")
  private String taxCategory;

  /**
   * state id.
   */
  @Column(name = "state_id")
  private String state;
}
