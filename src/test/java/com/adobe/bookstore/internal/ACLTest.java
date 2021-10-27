package com.adobe.bookstore.internal;

import com.adobe.bookstore.api.OrderRequest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 */
public class ACLTest {
  @Test
  public void mapAnOrderRequestToAnInternalOrder() {
    com.adobe.bookstore.api.BookOrder bookOrder1 = new com.adobe.bookstore.api.BookOrder("22d580fc-d02e-4f70-9980-f9693c18f6e0", 1);
    com.adobe.bookstore.api.BookOrder bookOrder2 = new com.adobe.bookstore.api.BookOrder("aedfaedf-d02e-4f70-9980-f9693c18f6e0", 2);
    OrderRequest validOrder = new OrderRequest(Arrays.asList(bookOrder1, bookOrder2));
    Order actual = ACL.toInternalOrder(validOrder);
    // The actual has an  UUID:orderId that I can't predict so I only assert over the contents.
    assertThat(actual.getItems()).isEqualTo(
        Arrays.asList(new BookOrder("22d580fc-d02e-4f70-9980-f9693c18f6e0", 1),
            new BookOrder("aedfaedf-d02e-4f70-9980-f9693c18f6e0", 2)
        ));
  }
}
