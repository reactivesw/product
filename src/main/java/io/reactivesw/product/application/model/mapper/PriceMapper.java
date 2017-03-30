package io.reactivesw.product.application.model.mapper;

import io.reactivesw.model.Reference;
import io.reactivesw.product.application.model.PriceView;
import io.reactivesw.product.domain.model.Price;
import io.reactivesw.product.infrastructure.util.ReferenceTypes;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Davis on 16/12/14.
 */
public final class PriceMapper {

  /**
   * Instantiates a new Price mapper.
   */
  private PriceMapper() {
  }

  /**
   * Entity to model price view.
   *
   * @param entity the entity
   * @return the price view
   */
  public static PriceView toModel(Price entity) {
    PriceView model = new PriceView();

    if (model.getChannel() != null) {
      model.setChannel(new Reference(ReferenceTypes.CHANNEL.getType(), entity.getChannel()));
    }
    model.setCountry(entity.getCountry());
    model.setId(entity.getId());
    model.setValidFrom(entity.getValidFrom());
    model.setValidUntil(entity.getValidUntil());
    model.setValue(MoneyMapper.toModel(entity.getValue()));
    if (model.getCustomerGroup() != null) {
      model.setCustomerGroup(new Reference(ReferenceTypes.CUSTOMERGROUP.getType(), entity
          .getCustomerGroup()));
    }

    return model;
  }

  /**
   * Map to model list.
   *
   * @param entities the entities
   * @return the list
   */
  public static List<PriceView> toModel(List<Price> entities) {
    return entities.stream().map(
        entity -> {
          return toModel(entity);
        }
    ).collect(Collectors.toList());
  }
}
