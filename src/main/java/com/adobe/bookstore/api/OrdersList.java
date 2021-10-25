package com.adobe.bookstore.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.List;
import lombok.Value;

/**
 * A List of Orders in the service. Used as Response.
 */
// Use Lombok's @Value for all the heavy lifting (getters, setters, equals, hashCode, toString...).
// If I were using Java 17 I would try using records
@Value
@JsonDeserialize
public class OrdersList {

  private final List<OrderResponse> orders;

  public static final OrdersList empty = new OrdersList(new ArrayList<>(0)) ;

  @JsonCreator
  public OrdersList(List<OrderResponse> orders) {
    this.orders = orders;
  }
}
