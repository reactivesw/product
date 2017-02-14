package io.reactivesw.product.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by Davis on 16/11/23.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "catalog_product_price")
public class PriceEntity {

  /**
   * value.
   */
  @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
  private MoneyEntity value;

  /**
   * country.
   */
  @Column(name = "country")
  private String country;

  /**
   * customer group.
   */
  @Column(name = "customer_group_id")
  private String customerGroup;

  /**
   * channel id.
   */
  @Column(name = "chanel_id")
  private String channel;

  /**
   * valid from.
   */
  @Column(name = "valid_from")
  private ZonedDateTime validFrom;

  /**
   * valid until.
   */
  @Column(name = "valid_until")
  private ZonedDateTime validUntil;

  /**
   * discounted.
   */
//  @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
//  private ProductDiscountEntity discounted;
  @Column(name = "discounted_id")
  private String discountedId;
}
