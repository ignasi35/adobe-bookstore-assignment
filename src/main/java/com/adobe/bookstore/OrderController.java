package com.adobe.bookstore;

import com.adobe.bookstore.api.OrdersList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class OrderController {
  @GetMapping("/orders")
  public OrdersList getOrders(){
    return OrdersList.empty;
  }
}
