package io.reactivesw.product.infrastructure.validator;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import io.reactivesw.exception.ParametersException;
import io.reactivesw.product.application.model.attribute.AttributeConstraint;
import io.reactivesw.product.application.model.attribute.AttributeDefinition;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.domain.model.ProductData;
import io.reactivesw.product.infrastructure.util.AttributeUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

/**
 * Created by Davis on 16/12/20.
 */
public final class UniqueAttributeValidator {

  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(UniqueAttributeValidator.class);

  /**
   * private constructor.
   */
  private UniqueAttributeValidator() {
  }

  /**
   * Validate product unique attribuate.
   *
   * @param attributeDefinitions the attribute definitions
   * @param product the Product entity
   */
  public static void validate(List<AttributeDefinition> attributeDefinitions, Product product) {
    List<String> uniqueAttributes = AttributeUtils.getAttributeNameByConstraint(
        attributeDefinitions,
        AttributeConstraint.Unique);

    ProductData staged = product.getMasterData().getStaged();

    uniqueAttributes.stream().forEach(
        uniqueAttribute -> {
          validateUniqueAttribute(uniqueAttribute, staged);
        }
    );
  }

  /**
   * Validate unique attribute.
   *
   * @param attributeName the attribute name
   * @param productData the ProductData entity
   */
  private static void validateUniqueAttribute(String attributeName, ProductData productData) {
    // TODO: 16/12/19 List<String> should be List<JsonNode>
    List<String> attributes = Lists.newArrayList();

    if (productData.getVariants() != null) {
      productData.getVariants().stream().forEach(
          productVariantDraft -> {
            productVariantDraft.getAttributes().stream().filter(
                attribute -> attribute.getName().equals(attributeName)
            ).forEach(
                attribute -> {
                  attributes.add(attribute.getValue().toString());
                }
            );
          }
      );
    }

    if (productData.getMasterVariant() != null
        && productData.getMasterVariant().getAttributes() != null) {
      productData.getMasterVariant().getAttributes().stream().filter(
          attribute -> attribute.getName().equals(attributeName)
      ).forEach(
          attribute -> {
            attributes.add(attribute.getValue().toString());
          }
      );
    }

    Set<String> attributeSets = Sets.newHashSet(attributes);
    if (attributeSets.size() < attributes.size()) {
      LOG.debug("unique attribute can not have same value");
      throw new ParametersException("unique attribute can not have same value");
    }
  }
}
