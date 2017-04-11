package io.reactivesw.product.application.admin.model.mapper;

import io.reactivesw.model.Reference;
import io.reactivesw.product.application.admin.model.PriceDraft;
import io.reactivesw.product.application.model.PriceView;
import io.reactivesw.product.domain.model.Price;
import io.reactivesw.product.infrastructure.util.ReferenceTypes;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Price Mapper class.
 * Convert Price Entity to Price Model, or Convert Price Model to Price Entity.
 */
public final class PriceMapper {

  /**
   * Instantiates a new Price mapper.
   */
  private PriceMapper() {
  }

  /**
   * Convert List of Price Model to List of Price Entity.
   *
   * @param models the models
   * @return the list
   */
  public static List<Price> toEntity(List<PriceDraft> models) {
    return models.stream().map(
        model -> {
          return toEntity(model);
        }
    ).collect(Collectors.toList());
  }

  /**
   * Convert Price Model to Price Entity.
   *
   * @param model the model
   * @return the price
   */
  public static Price toEntity(PriceDraft model) {
    Price entity = new Price();

    entity.setCountry(model.getCountry());
    entity.setValue(MoneyMapper.toEntity(model.getValue()));
    if (model.getChannel() != null) {
      entity.setChannel(model.getChannel().getId());
    }
    if (model.getValidFrom() != null) {
      entity.setValidFrom(model.getValidFrom());
    }
    if (model.getValidUntil() != null) {
      entity.setValidUntil(model.getValidUntil());
    }
    if (model.getCustomerGroup() != null) {
      entity.setCustomerGroup(model.getCustomerGroup().getId());
    }

    return entity;
  }

  /**
   * Convert Price Entity to Price Model.
   *
   * @param entity the entity
   * @return the price view
   */
  public static PriceView entityToModel(Price entity) {
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
   * Convert List of Price Entity to List of Price Model.
   *
   * @param entities the entities
   * @return the list
   */
  public static List<PriceView> entityToModel(List<Price> entities) {
    return entities.stream().map(
        entity -> {
          return entityToModel(entity);
        }
    ).collect(Collectors.toList());
  }
}
