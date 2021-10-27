package com.adobe.bookstore;

import com.adobe.bookstore.api.*;
import com.adobe.bookstore.internal.ACL;
import com.adobe.bookstore.internal.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
public class OrderController {

  public static final String PATH = "/orders";
  @Autowired
  private OrderRepository orderRepository;

  @GetMapping(value = PATH, produces = {MediaType.APPLICATION_JSON_VALUE})
  public OrdersList getOrders() {
    return new OrdersList(orderRepository.getOrders().stream().map(ACL::toOrderResponse).collect(Collectors.toList()));
  }

  @GetMapping(value = PATH, produces = {CustomMediaType.TEXT_CSV_VALUE})
  public CsvOrderRow[] getOrdersAsCsv() {
    OrdersList ordersList = getOrders();
    return ordersList.getOrders().stream().flatMap((orderResponse) ->
        orderResponse.getItems().stream().map((order) ->
            new CsvOrderRow(orderResponse.getOrderId(), order.getBookId().toString(), order.getQuantity())
        )).toArray(CsvOrderRow[]::new);
  }

  @PostMapping(value = PATH,
      consumes = {MediaType.APPLICATION_JSON_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE})
  @ResponseStatus(HttpStatus.CREATED)
  public OrderReceipt placeOrder(@RequestBody OrderRequest orderRequest) {
    Order order = ACL.toInternalOrder(orderRequest);
    orderRepository.placeOrder(order);
    return new OrderReceipt(order.getOrderId());
  }
}
