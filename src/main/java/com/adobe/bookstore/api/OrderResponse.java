package com.adobe.bookstore.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Value;

import java.util.List;
import java.util.UUID;

/**
 *
 */
@Value
@JsonDeserialize
public class OrderResponse {
  @JsonProperty("order-id")
  private final UUID orderId;
  @JsonProperty("items")
  private final List<BookOrder> items;

  public OrderResponse(String orderId, List<BookOrder> items) {
    this(UUID.fromString(orderId), items);
  }

  @JsonCreator
  public OrderResponse(@JsonProperty("order-id") UUID orderId,
                       @JsonProperty("items")
                           List<BookOrder> items) {
    this.orderId = orderId;
    this.items = items;
  }
}
