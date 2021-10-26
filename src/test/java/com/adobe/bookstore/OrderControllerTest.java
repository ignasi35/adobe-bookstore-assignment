package com.adobe.bookstore;


import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

import com.adobe.bookstore.api.CustomMediaType;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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

}

