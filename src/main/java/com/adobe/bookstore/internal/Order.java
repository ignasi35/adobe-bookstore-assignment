package com.adobe.bookstore.internal;

import lombok.Value;

import java.util.List;
import java.util.UUID;

/**
 *
 */
@Value
public class Order {
  // This comment applies to all usages of @Value across the code.
  // Lombok's @Value makes all fields private and final so it's not necessary to add
  // those modifiers every single time. Still, it's a good practice to add them. I'm not
  // adding the modifiers here as a demonstration fo that feature.
  UUID orderId;
  List<BookOrder> items;

  public Order(UUID orderId, List<BookOrder> items) {
    this.orderId = orderId;
    this.items = items;
  }
}
