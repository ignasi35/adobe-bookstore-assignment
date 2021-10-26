package com.adobe.bookstore;

import com.adobe.bookstore.internal.Order;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

/**
 *
 */
@Repository
@Scope(scopeName =
    ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class InMemOrderRepository implements OrderRepository {
  // Use a concurrent Queue (even though we'll only add orders. Using a
  // concurrent data-structure makes it trivial to support concurrent
  // placement of orders.
  private final ConcurrentLinkedQueue<Order> orders = new ConcurrentLinkedQueue<>();

  @Override
  public void placeOrder(Order order) {
    orders.add(order);
  }

  @Override
  public List<Order> getOrders() {
    List<Order> collect = orders.stream().collect(Collectors.toList());
    return collect;
  }
}
