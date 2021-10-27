package com.adobe.bookstore.internal;

import lombok.Value;

import java.util.UUID;

/**
 * An internal mirror of the .api.BookOrder class. See ACL for the reason why this exists.
 */
@Value
public class BookOrder {
  private final UUID bookId;
  private final int quantity;

  protected BookOrder(String bookId, int quantity) {
    this(UUID.fromString(bookId), quantity);
  }

  public BookOrder(UUID bookId, int quantity) {
    this.bookId = bookId;
    this.quantity = quantity;
  }

}
