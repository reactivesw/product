package io.reactivesw.product.infrastructure.validator;

import io.reactivesw.exception.ConflictException;
import io.reactivesw.product.domain.model.Product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Validator class for version.
 */
public final class VersionValidator {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(VersionValidator.class);

  /**
   * Instantiates a new Version validator.
   */
  private VersionValidator() {
  }

  /**
   * Validate entity version.
   *
   * @param entity the entity
   * @param version the version
   * @throws ConflictException if version not equal
   */
  public static void validate(Product entity, Integer version) {
    if (!version.equals(entity.getVersion())) {
      LOG.debug("Version not match, input version: {}, entity version: {}.",
          version, entity.getVersion());
      throw new ConflictException("Version not match.");
    }
  }
}
