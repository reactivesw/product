package io.reactivesw.product.infrastructure.validator;

import io.reactivesw.exception.ParametersException;
import io.reactivesw.product.application.admin.model.ProductDraft;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ProductType validator class, validateReference the ProductType is null.
 */
public final class ProductTypeValidator {

  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(ProductTypeValidator.class);

  /**
   * Instantiates a new Product type validator.
   */
  private ProductTypeValidator() {
  }

  /**
   * validateReference that product type is null.
   *
   * @param draft the ProductDraft
   * @throws ParametersException if ProductType is null or ProductType id is blank
   */
  public static void validateReference(ProductDraft draft) {
    if (draft.getProductType() == null
        || StringUtils.isBlank(draft.getProductType().getId())) {
      LOG.debug("ProductType is null or ProductType id is blank.");
      throw new ParametersException("ProductType can not be null");
    }
  }
}
