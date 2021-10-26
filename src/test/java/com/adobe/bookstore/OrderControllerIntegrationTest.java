package com.adobe.bookstore;

import com.adobe.bookstore.api.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.net.URI;
import java.util.Arrays;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerIntegrationTest {

  @Autowired
  private TestRestTemplate template;

  @Test
  public void addOrdersAndGetThem() throws Exception {
    // there are 5 in the initial inventory
    BookOrder bookOrder = new BookOrder("22d580fc-d02e-4f70-9980-f9693c18f6e0", 1);
    OrderRequest validOrder = new OrderRequest(bookOrder);
    URI uri = new URI(OrderController.PATH);

    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<OrderRequest> entity = new HttpEntity<>(validOrder, requestHeaders);
    ResponseEntity<OrderReceipt> postResponse =
        template.exchange(uri, HttpMethod.POST, entity, OrderReceipt.class);


    assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    OrderReceipt receipt = postResponse.getBody();

    ResponseEntity<OrdersList> response = template.getForEntity(OrderController.PATH, OrdersList.class);
    OrdersList body = response.getBody();
    assertThat(body).isEqualTo(new OrdersList(Arrays.asList(
        new OrderResponse(receipt.getOrderId(), Set.of(bookOrder))
        ))
    );
  }
}
