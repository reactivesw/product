package io.reactivesw.product.infrastructure.validator;

import com.google.common.collect.Lists;

import io.reactivesw.exception.ParametersException;
import io.reactivesw.product.application.admin.model.ProductDraft;
import io.reactivesw.product.application.model.attribute.AttributeDefinition;
import io.reactivesw.product.application.model.attribute.AttributeView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Davis on 16/12/20.
 */
public final class RequireAttributeValidator {
  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(RequireAttributeValidator.class);

  /**
   * private constructor.
   */
  private RequireAttributeValidator() {
  }

  /**
   * Validate product require attribute.
   *
   * @param attributeDefinitions the attribute definitions
   * @param productDraft         the product draft
   */
  public static void validate(List<AttributeDefinition>
                                  attributeDefinitions,
                              ProductDraft productDraft) {
    List<String> requireAttributeNames = getRequireAttributeNames(attributeDefinitions);

    if (requireAttributeNames.isEmpty()) {
      return;
    }

    if (productDraft.getMasterVariant() == null) {
      throwExceptionForRequire();
    }

    validateRequireAttribute(requireAttributeNames,
        productDraft.getMasterVariant().getAttributes());

    if (productDraft.getVariants() != null && !productDraft.getVariants().isEmpty()) {
      productDraft.getVariants().stream().forEach(
          productVariantDraft -> {
            validateRequireAttribute(requireAttributeNames, productVariantDraft
                .getAttributes());
          }
      );
    }
  }

  /**
   * Get required attribute names from List of AttributeDefinition.
   *
   * @param attributeDefinitions List of AttributeDefinition
   * @return List of String
   */
  private static List<String> getRequireAttributeNames(List<AttributeDefinition>
                                                           attributeDefinitions) {
    List<String> requireAttributeNames = Lists.newArrayList();
    if (attributeDefinitions != null) {
      requireAttributeNames = attributeDefinitions.stream()
          .filter(
              attributeDefinition -> attributeDefinition.getIsRequired().equals(true)
          ).map(
              attributeDefinition -> {
                return attributeDefinition.getName();
              })
          .collect(Collectors.toList());
    }
    return requireAttributeNames;
  }

  /**
   * Validate require attribute.
   *
   * @param requireAttributeNames the require attribute names
   * @param attributes            the attributes
   */
  private static void validateRequireAttribute(List<String> requireAttributeNames,
                                               List<AttributeView> attributes) {
    List<String> attributeNames = attributes.stream().map(
        attribute -> {
          return attribute.getName();
        }).collect(Collectors.toList());

    if (!attributeNames.containsAll(requireAttributeNames)) {
      throwExceptionForRequire();
    }
  }

  /**
   * Throw exception for require.
   */
  private static void throwExceptionForRequire() {
    LOG.debug("Field 'require' must not be empty");
    throw new ParametersException("Field 'require' must not be empty");
  }
}
