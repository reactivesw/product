package io.reactivesw.product.application.model.mapper;

import io.reactivesw.model.Money;
import io.reactivesw.product.domain.model.MoneyValue;

/**
 * Created by umasuo on 16/12/8.
 */
public class MoneyMapper {


  /**
   * Entity to model money.
   *
   * @param entity the entity
   * @return the money
   */
  public static Money entityToModel(MoneyValue entity) {
    io.reactivesw.model.Money model = null;
    if (entity != null) {
      model = new io.reactivesw.model.Money();

      model.setCentAmount(entity.getCentAmount());
      model.setCurrencyCode(entity.getCurrencyCode());
    }
    return model;
  }

}
