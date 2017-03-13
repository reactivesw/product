package io.reactivesw.product.application.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.reactivesw.model.LocalizedString;
import io.reactivesw.product.application.model.attribute.AttributeConstraint;
import io.reactivesw.product.application.model.attribute.AttributeType;
import io.reactivesw.product.application.model.attribute.TextInputHint;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by Davis on 16/11/17.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AttributeDefinitionDraft {

  /**
   * Describes the type of the attribute.
   */
  @NotNull
  private AttributeType type;

  /**
   * The unique name of the attribute used in the API.
   * The name must be between two and 36 characters long and can contain
   * the ASCII letters A to Z in lowercase or uppercase, digits, underscores (_)
   * and the hyphen-minus (-).
   * It is allowed to have attributes with the same name in two or more ProductTypes.
   * The important constraint is: all fields of the AttributeDefinitionEntity
   * need to be the same across all attributes with the same name.
   */
  @NotNull
  @Pattern(regexp="[-a-zA-Z0-9_]{2,256}", message = "can not match attribute name")
  private String name;

  /**
   * A human-readable label for the attribute.
   */
  @NotNull
  private LocalizedString label;

  /**
   * Whether the attribute is required to have a value.
   */
  @NotNull
  private Boolean isRequired;

  /**
   * Describes how an attribute or a set of attributes
   * should be validated across all variants of a product.
   */
  @NotNull
  private AttributeConstraint attributeConstraint;

  /**
   * Provides a visual representation type for this attribute.
   * only relevant for text-based attribute model like TextType and LocalizableTextType.
   */
  private TextInputHint inputHint;

  /**
   * Whether the attribute’s value should generally be enabled in product search. This
   * determines whether the value is stored in product for matching terms in the context of
   * full-text search query and can be used in facets & filters as part of product search
   * query. The exact features that are enabled/disabled with this flag depend on the concrete
   * attribute type and are described there.
   */
  @NotNull
  private Boolean isSearchable;

  /**
   * inputTip.
   */
  private LocalizedString inputTip;
}
