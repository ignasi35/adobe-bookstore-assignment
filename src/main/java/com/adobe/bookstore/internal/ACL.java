package com.adobe.bookstore.internal;

import com.adobe.bookstore.api.OrderRequest;
import com.adobe.bookstore.api.OrderResponse;

import java.util.UUID;
import java.util.stream.Collectors;

/**
 * The Anti-Corruption Layer provides methods to convert to/from the public API and the internal
 * classes. Helps keep the public API stable.
 */
public class ACL {
  public static Order toInternalOrder(OrderRequest request) {
    return new Order(UUID.randomUUID(),
        request.getItems().stream().map(ACL::toInternalBookOrder).collect(Collectors.toList())
    );
  }

  private static BookOrder toInternalBookOrder(com.adobe.bookstore.api.BookOrder api) {
    return new BookOrder(api.getBookId(), api.getQuantity());
  }

  public static OrderResponse toOrderResponse(Order order) {
    return new OrderResponse(
        order.getOrderId(),
        order.getItems().stream().map(ACL::toExternalBookOrder).collect(Collectors.toList())
    );
  }

  private static com.adobe.bookstore.api.BookOrder toExternalBookOrder(BookOrder internal) {
    return new com.adobe.bookstore.api.BookOrder(internal.getBookId(), internal.getQuantity());
  }
}
