package com.adobe.bookstore;


import com.adobe.bookstore.api.CustomMediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void getEmptyListOfOrdersInJsonFormat() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/orders").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        // NOTE: If I was using Java 17 I would use Text Blocks
        .andExpect(content().string(equalTo("{\"orders\":[]}")));
  }

  @Test
  public void getEmptyListOfOrdersInCsvFormat() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/orders").accept(CustomMediaType.TEXT_CSV))
        .andExpect(status().isOk())
        // I decided fields on the CSV should be always quoted and the separator should be a semicolon
        .andExpect(content().string(equalTo("\"order_id\";\"book_id\";\"quantity\"\n")));
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
    mvc.perform(
        MockMvcRequestBuilders
            .post("/orders")
            .content(
                payload.getBytes(StandardCharsets.UTF_8)
            )
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
    )
        .andExpect(status().isCreated())
        .andExpect(header().string("Content-Type", "application/json"));
  }

}

