package com.adobe.bookstore.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Value;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 *
 */
@Value
@JsonDeserialize
public class OrderResponse {
  private final UUID orderId;
  private final Set<BookOrder> orderContents;

  public OrderResponse(String orderId, Set<BookOrder> orderContents) {
    this(UUID.fromString(orderId), orderContents);
  }
  @JsonCreator
  public OrderResponse(UUID orderId, Set<BookOrder> orderContents) {
    this.orderId = orderId;
    this.orderContents = orderContents;
  }
}
