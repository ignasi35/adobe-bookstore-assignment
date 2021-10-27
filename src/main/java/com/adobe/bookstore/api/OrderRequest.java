package com.adobe.bookstore.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Value;

import java.util.List;

/**
 *
 */
@Value
@JsonDeserialize
public class OrderRequest {
  // This comment applies to all usages of @Value across the code.
  // Lombok's @Value makes all fields private and final so it's not necessary to add
  // those modifiers every single time. Still, it's a good practice to add them. I'm not
  // adding the modifiers here as a demonstration fo that feature.
  List<BookOrder> items;

  @JsonCreator
  public OrderRequest(List<BookOrder> items) {
    this.items = items;
  }
}
