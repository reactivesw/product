package io.reactivesw.product.infrastructure.validator;

import io.reactivesw.product.application.model.attribute.AttributeDefinition;
import io.reactivesw.product.domain.model.Product;

import java.util.List;

/**
 * Created by Davis on 16/12/20.
 */
public final class AttributeConstraintValidator {

  /**
   * private constructor.
   */
  private AttributeConstraintValidator() {
  }

  /**
   * Validate.
   *
   * @param attributeDefinitions the attribute definitions
   * @param product the product
   */
  public static void validate(List<AttributeDefinition> attributeDefinitions, Product product) {
    if (attributeDefinitions != null) {
      //1. require的attribute
      RequireAttributeValidator.validate(attributeDefinitions, product);
      //2. attribute name
      AttributeNameValidator.validate(attributeDefinitions, product);
      //3. unique 比对一下所有都不相同
      UniqueAttributeValidator.validate(attributeDefinitions, product);
      //4. combination unique 组合不相同,需要比对
      CombinationAttributeValidator.validate(attributeDefinitions, product);
      //5. same for all
      SameForAllAttributeValidator.validate(attributeDefinitions, product);
      //6. none 不需要考虑
    }
  }
}
