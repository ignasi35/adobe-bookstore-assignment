package com.adobe.bookstore;


import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
  public void getHello() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/orders").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        // NOTE: If I was using Java 17 I would use Text Blocks
        .andExpect(content().string(equalTo("{\"orders\":[]}")));
  }
}
