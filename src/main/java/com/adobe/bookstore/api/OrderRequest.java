package com.adobe.bookstore.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Value;

import java.util.Arrays;
import java.util.List;

/**
 *
 */
@Value
@JsonDeserialize
public class OrderRequest {
  private final List<BookOrder> items;

  public OrderRequest(BookOrder... items) {
    this(Arrays.asList(items));
  }
  public OrderRequest(List<BookOrder> items) {
    this.items = items;
  }
}
