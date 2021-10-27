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
public class BookOrder {
  // JSON attributes use kebab-case
  @JsonProperty("book-id")
  private final UUID bookId;
  private final int quantity;

  @JsonCreator
  public BookOrder(@JsonProperty("book-id") String bookId,
                   @JsonProperty("quantity") int quantity) {
    this(UUID.fromString(bookId), quantity);
  }

  public BookOrder(UUID bookId, int quantity) {
    this.bookId = bookId;
    this.quantity = quantity;
  }

}
