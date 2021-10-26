package com.adobe.bookstore;


import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public abstract class AbstractOrderControllerTest {
  public abstract MockMvc mvc();

  public MvcResult postOrder(String payload) throws Exception {
    return mvc().perform(
        MockMvcRequestBuilders
            .post("/orders")
            .content(
                payload.getBytes(StandardCharsets.UTF_8)
            )
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
    )
        .andExpect(status().isCreated())
        .andExpect(header().string("Content-Type", "application/json"))
        .andReturn();

  }
}
