package com.adobe.bookstore;

import com.adobe.bookstore.api.CustomMediaType;
import com.adobe.bookstore.api.OrderReceipt;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerCsvTest extends AbstractOrderControllerTest{

  // NOTE: OrderControllerUnitTest is a WebMvcTest so my custom beans for
  // CSV support are not wired. This is an integration test with the only
  // purpose to test the CSV support.

  @Autowired
  private MockMvc mvc;
  public MockMvc mvc()  { return mvc;}

  @Test
  public void getListOfOrdersInCsvFormat() throws Exception {
    // test loading an empty list
    mvc.perform(MockMvcRequestBuilders.get("/orders").accept(CustomMediaType.TEXT_CSV))
        .andExpect(status().isOk())
        // I decided fields on the CSV should be always quoted and the separator should be a semicolon
        .andExpect(content().string(equalTo("\"order_id\";\"book_id\";\"quantity\"\n")));

    // post an order
    String bookId = "22d580fc-d02e-4f70-9980-f9693c18f6e0";
    int quantity = 1;
    String payload = String.format("{ \"items\": [{ \"book-id\": \"%s\",  \"quantity\": %d }] }", bookId, quantity);
    MvcResult mvcResult = postOrder(payload);
    String contentAsString = mvcResult.getResponse().getContentAsString();
    OrderReceipt receipt = new ObjectMapper().readValue(contentAsString, OrderReceipt.class);

    // retrieve a non-empty list
    mvc.perform(MockMvcRequestBuilders.get("/orders").accept(CustomMediaType.TEXT_CSV))
        .andExpect(status().isOk())
        .andExpect(content().string(equalTo(
            "\"order_id\";\"book_id\";\"quantity\"\n"+
            "\""+receipt.getOrderId().toString()+"\";\"22d580fc-d02e-4f70-9980-f9693c18f6e0\";1\n"
        )));
  }

}

