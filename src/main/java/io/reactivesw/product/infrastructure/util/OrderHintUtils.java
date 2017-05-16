package io.reactivesw.product.infrastructure.util;

/**
 * The type Order hint utils.
 */
public final class OrderHintUtils {

  /**
   * Instantiates a new Order hint utils.
   */
  private OrderHintUtils() {
  }

  /**
   * Create order hint.
   *
   * @return the string
   */
  public static String createOrderHint() {
    long currentTime = System.currentTimeMillis();
    String orderHint = "0." + currentTime;

    return orderHint;
  }
}
