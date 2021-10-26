package com.adobe.bookstore.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Value;

import java.util.UUID;

/**
 *
 */
@Value
@JsonDeserialize
public class BookOrder {
  private final UUID bookId;
  private final int quantity;

  @JsonCreator
  public BookOrder(String bookId, int quantity) {
    this(UUID.fromString(bookId), quantity);
  }
  public BookOrder(UUID bookId, int quantity) {
    this.bookId = bookId;
    this.quantity = quantity;
  }

}
