package io.reactivesw.product.application.admin.model.mapper;

import io.reactivesw.model.Money;
import io.reactivesw.product.domain.model.MoneyValue;

/**
 * Money Mapper class.
 * Convert Money Entity to Money Model,
 * or convert Money Model to Money Entity.
 */
public final class MoneyMapper {

  /**
   * Instantiates a new Money mapper.
   */
  private MoneyMapper() {
  }

  /**
   * Convert Money Model to Money Entity.
   *
   * @param model the model
   * @return the money value
   */
  public static MoneyValue toEntity(Money model) {
    MoneyValue entity = null;
    if (model != null) {
      entity = new MoneyValue();
      entity.setCentAmount(model.getCentAmount());
      entity.setCurrencyCode(model.getCurrencyCode());
    }
    return entity;
  }

  /**
   * Convert Money Entity to Money Model.
   *
   * @param entity the entity
   * @return the money
   */
  public static Money toModel(MoneyValue entity) {
    Money model = null;
    if (entity != null) {
      model = new Money();

      model.setCentAmount(entity.getCentAmount());
      model.setCurrencyCode(entity.getCurrencyCode());
    }
    return model;
  }

  /**
   * Copy from money.
   *
   * @param entity the entity
   * @return the money
   */
  public static MoneyValue copyFrom(MoneyValue entity) {
    MoneyValue result = new MoneyValue();

    result.setCurrencyCode(entity.getCurrencyCode());
    result.setCentAmount(entity.getCentAmount());

    return result;
  }

}
