package com.adobe.bookstore.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Value;

import java.util.Map;

/**
 *
 */
@Value
@JsonDeserialize
public class OrderResponse {
  private final String orderId ;
  private final Map<String, Integer> orderContents ;

  @JsonCreator
  public OrderResponse(String orderId, Map<String, Integer> orderContents) {
    this.orderId = orderId;
    this.orderContents = orderContents;
  }
}
