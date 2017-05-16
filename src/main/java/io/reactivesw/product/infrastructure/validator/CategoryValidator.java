package io.reactivesw.product.infrastructure.validator;

import io.reactivesw.exception.ParametersException;
import io.reactivesw.model.Reference;
import io.reactivesw.product.infrastructure.util.ReferenceTypes;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Validator class for category and category orderHint.
 */
public final class CategoryValidator {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(CategoryValidator.class);

  /**
   * Instantiates a new Category validator.
   */
  private CategoryValidator() {
  }

  /**
   * Validate category.
   *
   * @param category the category
   */
  public static void validateCategory(Reference category) {
    if (category == null
        || !category.getTypeId().equals(ReferenceTypes.CATEGORY.getType())
        || StringUtils.isBlank(category.getId())) {
      LOG.debug("Category reference is null.");
      throw new ParametersException("Category Reference is Null.");
    }
  }
}
