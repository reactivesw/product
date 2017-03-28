package io.reactivesw.product.application.model.mapper;

import io.reactivesw.model.Money;
import io.reactivesw.product.domain.model.MoneyValue;

/**
 * Created by umasuo on 16/12/8.
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

}
