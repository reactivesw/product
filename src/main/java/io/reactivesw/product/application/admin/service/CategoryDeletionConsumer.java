package io.reactivesw.product.application.admin.service;

import io.reactivesw.message.client.consumer.Consumer;
import io.reactivesw.message.client.core.DefaultConsumerFactory;
import io.reactivesw.message.client.core.Message;
import io.reactivesw.message.client.utils.serializer.JsonDeserializer;
import io.reactivesw.product.infrastructure.configuration.EventConfig;
import io.reactivesw.product.infrastructure.util.EventSubscriberUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Category deletion consumer.
 */
@Service
public class CategoryDeletionConsumer {

  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(CategoryDeletionConsumer.class);

  /**
   * message consumer.
   */
  private transient Consumer consumer;

  /**
   * Json deserializer.
   */
  private transient JsonDeserializer<List> jsonDeserializer = new JsonDeserializer<>(List.class);

  /**
   * Instantiates a new Category deletion consumer.
   *
   * @param config the config
   */
  public CategoryDeletionConsumer(EventConfig config) {
    consumer = DefaultConsumerFactory.createGoogleConsumer(config.getGoogleCloudProjectId(),
        EventSubscriberUtil.CATEGORY_DELETION);
  }

  /**
   * executor.
   * Executes each 200 ms.
   */
  @Scheduled(fixedRate = 200)
  public void executor() {

    // Pull messages todo this should be configurable.
    List<Message> events = consumer.pullMessages(10);
    if (!events.isEmpty()) {
      events.stream().forEach(
          message -> {
            List categoryIdList = jsonDeserializer.deserialize(message.getData().toString());
            handleCategoryDeletion(categoryIdList);
            consumer.acknowledgeMessage(message.getExternalId());
          }
      );
    }
  }

  /**
   * Handle category deletion.
   *
   * @param categoryIds the category ids
   */
  public void handleCategoryDeletion(List<String> categoryIds) {
    LOG.debug("Enter. CategoryIds: {}.", categoryIds);
  }
}
