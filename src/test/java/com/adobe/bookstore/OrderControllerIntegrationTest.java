package com.adobe.bookstore;

import com.adobe.bookstore.api.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.net.URI;
import java.util.Arrays;

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
    OrderRequest validOrder = new OrderRequest(Arrays.asList(bookOrder));
    URI uri = new URI(OrderController.PATH);

    HttpHeaders requestHeadersPost = new HttpHeaders();
    requestHeadersPost.setContentType(MediaType.APPLICATION_JSON);
    requestHeadersPost.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    HttpEntity<OrderRequest> entity = new HttpEntity<>(validOrder, requestHeadersPost);
    ResponseEntity<OrderReceipt> postResponse =
        template.exchange(uri, HttpMethod.POST, entity, OrderReceipt.class);


    assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    OrderReceipt receipt = postResponse.getBody();

    // Get list as JSON
    HttpHeaders requestHeadersGet = new HttpHeaders();
    requestHeadersGet.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    ResponseEntity<OrdersList> response =
        template.exchange(
            uri,
            HttpMethod.GET,
            new HttpEntity<>(requestHeadersGet),
            OrdersList.class
        );
    assertThat(response.getBody()).isEqualTo(new OrdersList(Arrays.asList(
        new OrderResponse(receipt.getOrderId(), Arrays.asList(bookOrder))
        ))
    );

    // Get list as CSV
    // NOTE, OrderControllerTest is a @WebMvcTest so it doesn't wire all the custom
    // CSV support I've added in .framework reason why I'm testing CSV support on the IT only.
    template.getRestTemplate().getUriTemplateHandler().expand(OrderController.PATH);

  }
}
