package com.adobe.bookstore;

import com.adobe.bookstore.api.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class OrderController {

  public static final String PATH = "/orders";

  @GetMapping(value = PATH, produces = {MediaType.APPLICATION_JSON_VALUE})
  public OrdersList getOrders() {
    return OrdersList.empty;
  }

  @GetMapping(value = PATH, produces = {CustomMediaType.TEXT_CSV_VALUE})
  public CsvOrderRow[] getOrdersAsCsv() {
    OrdersList ordersList = getOrders();
    return ordersList.getOrders().stream().flatMap((orderResponse) ->
        orderResponse.getOrderContents().stream().map((order) ->
            new CsvOrderRow(orderResponse.getOrderId(), order.getBookId().toString(), order.getQuantity())
        )).toArray(CsvOrderRow[]::new);
  }

  @PostMapping(value = PATH, produces = {MediaType.APPLICATION_JSON_VALUE})
  public OrderReceipt placeOrder(OrderRequest orderRequest) {
    return new OrderReceipt(UUID.randomUUID());
  }
}
