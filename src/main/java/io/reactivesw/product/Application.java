package io.reactivesw.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * Application starter.
 */
@SpringBootApplication(scanBasePackages = "io.reactivesw")
@EnableScheduling
public class Application {

  /**
   * The entry point of controller.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}