package com.adobe.bookstore;

import com.adobe.bookstore.internal.Order;

import java.util.List;

/**
 *
 */
public interface OrderRepository {
  void placeOrder(Order order);

  List<Order> getOrders();
}
