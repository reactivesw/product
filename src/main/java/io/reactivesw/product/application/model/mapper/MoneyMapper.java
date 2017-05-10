package io.reactivesw.product.application.model.mapper;

import io.reactivesw.model.Money;
import io.reactivesw.product.domain.model.MoneyValue;

/**
 * The type Money mapper.
 */
public final class MoneyMapper {

  /**
   * Instantiates a new Money mapper.
   */
  private MoneyMapper() {
  }

  /**
   * Entity to model money.
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
   * To entity money value.
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
}
