package io.reactivesw.product.infrastructure.configuration;

import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Event config.
 */
@Configuration
@Data
public class EventConfig {

  /**
   * Google cloud project id.
   */
  @Value("${io.reactivesw.message.google.project.id}")
  private String googleCloudProjectId;

  /**
   * google cloud project id.
   */
  @Value("${io.reactivesw.message.topic.categorydeleted.subscriber}")
  private String categoryDeletedSubscriber;
}
