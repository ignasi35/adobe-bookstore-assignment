package com.adobe.bookstore;

import com.adobe.bookstore.api.CsvOrderRow;
import com.adobe.bookstore.api.CustomMediaType;
import com.adobe.bookstore.api.OrdersList;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class OrderController {

  private final String PATH = "/orders";

  @GetMapping(value = PATH, produces = {MediaType.APPLICATION_JSON_VALUE})
  public OrdersList getOrdersAsJson() {
    return getOrders();
  }

  @GetMapping(value = PATH, produces = {CustomMediaType.TEXT_CSV_VALUE})
  public CsvOrderRow[] getOrdersAsCsv() {
    OrdersList ordersList = getOrders();
    return ordersList.getOrders().stream().flatMap((orderResponse) ->
        orderResponse.getOrderContents().entrySet().stream().map((entry) ->
            new CsvOrderRow(orderResponse.getOrderId(), entry.getKey(), entry.getValue())
        )).toArray(CsvOrderRow[]::new);
  }

  private OrdersList getOrders() {
    return OrdersList.empty;
  }


}
