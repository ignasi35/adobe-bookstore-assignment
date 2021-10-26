package com.adobe.bookstore;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@AutoConfigureMockMvc
public class OrderControllerJsonTest extends  AbstractOrderControllerTest{

  @Autowired
  private MockMvc mvc;
  @Override
  public MockMvc mvc() {
    return mvc;
  }

  @MockBean
  private OrderRepository orderRepositoryMock;


  @Test
  public void getEmptyListOfOrdersInJsonFormat() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/orders").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        // NOTE: If I was using Java 17 I would use Text Blocks
        .andExpect(content().string(equalTo("{\"orders\":[]}")));
  }

  @Test
  public void defaultToJsonFormatWhenAcceptHeaderIsMissing() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/orders"))
        .andExpect(status().isOk())
        .andExpect(header().string("Content-Type", "application/json"));
  }

  @Test
  public void acceptsNewOrders() throws Exception {
    String bookId = "22d580fc-d02e-4f70-9980-f9693c18f6e0";
    int quantity = 1;
    String payload = String.format("{ \"items\": [{ \"book-id\": \"%s\",  \"quantity\": %d }] }", bookId, quantity);
     postOrder(payload);
    verify(orderRepositoryMock, times(1)).placeOrder(any());
  }

}

