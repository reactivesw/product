package io.reactivesw.product.domain.model;

import io.reactivesw.database.dialect.JSONBUserType;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Davis on 16/11/23.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "image")
@TypeDef(name = "Dimensions", typeClass = JSONBUserType.class, parameters = {
    @Parameter(name = JSONBUserType.CLASS,
        value = "io.reactivesw.product.domain.model.AssetDimensions")}
)
public class Image {

  /**
   * id.
   */
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id")
  protected String id;

  /**
   * url.
   */
  @Column(name = "url", length = 1024)
  private String url;

  /**
   * dimensions.
   */
  @Type(type = "Dimensions")
  private AssetDimensions dimensions;

  /**
   * label.
   */
  @Column(name = "label")
  private String label;
}
