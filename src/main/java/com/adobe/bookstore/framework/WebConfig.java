package com.adobe.bookstore.framework;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * As suggested in https://stackoverflow.com/questions/20937842/how-to-make-spring-mvc-return-csv-as-convenient-as-return-json
 * <p>
 * Configures the default Content Type of the responses to JSON
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
    configurer.defaultContentType(MediaType.APPLICATION_JSON);
  }
}