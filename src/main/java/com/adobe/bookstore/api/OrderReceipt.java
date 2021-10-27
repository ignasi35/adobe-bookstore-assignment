package com.adobe.bookstore.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Value;

import java.util.UUID;

/**
 *
 */
@Value
@JsonDeserialize
public class OrderReceipt {
  @JsonProperty("order-id")
  private final UUID orderId;

  @JsonCreator
  public OrderReceipt(@JsonProperty("order-id") String orderId) {
    this(UUID.fromString(orderId));
  }

  public OrderReceipt(UUID orderId) {
    this.orderId = orderId;
  }
}
