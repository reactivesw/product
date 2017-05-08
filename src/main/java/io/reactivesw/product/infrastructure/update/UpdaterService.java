package io.reactivesw.product.infrastructure.update;

import io.reactivesw.model.Updater;
import io.reactivesw.product.domain.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * We may got two kind of update: just use the data in action, or still use data from other service.
 * If we just use the data in action, we can only use action mapper to set the data.
 * If we need get data from other palace, we should use update service.
 */
@Service
public class UpdaterService implements Updater<Product, UpdateAction> {

  /**
   * ApplicationContext for get update services.
   */
  @Autowired
  private transient ApplicationContext context;

  /**
   * Put the value in action to entity.
   *
   * @param entity E
   * @param action UpdateAction
   */
  @Override
  public void handle(Product entity, UpdateAction action) {
    Updater updater = getUpdateService(action);
    updater.handle(entity, action);
  }

  /**
   * Get mapper.
   *
   * @param action UpdateAction
   * @return ZoneUpdateMapper
   */
  private Updater getUpdateService(UpdateAction action) {
    return (Updater) context.getBean(action.getActionName());
  }
}
