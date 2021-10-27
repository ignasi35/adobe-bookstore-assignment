package com.adobe.bookstore.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Value;

import java.util.UUID;

/**
 * Presenting Order's in CSV requires flatting a hierarchical structure where a single order contains multiple books.
 * A CsvOrderRow is the flattened-out representation of a single book-id/quantity/order-id tuple.
 * <p>
 * This uses Jackson's CSV support as explained in https://cowtowncoder.medium.com/reading-csv-with-jackson-c4e74a15ddc1
 */
@Value
@JsonDeserialize
@JsonPropertyOrder({"orderId", "bookId", "quantity"})
public class CsvOrderRow {
  // CSV headers use snake_case
  @JsonProperty("order_id")
  private final UUID orderId;
  @JsonProperty("book_id")
  private final String bookId; // TODO: use UUID
  @JsonProperty("quantity")
  private final int quantity;

  public CsvOrderRow(String orderId, String bookId, int quantity) {
    this(UUID.fromString(orderId), bookId, quantity);
  }

  @JsonCreator
  public CsvOrderRow(UUID orderId, String bookId, int quantity) {
    this.orderId = orderId;
    this.bookId = bookId;
    this.quantity = quantity;
  }
}
