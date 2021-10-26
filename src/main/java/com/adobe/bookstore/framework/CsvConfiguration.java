package com.adobe.bookstore.framework;

import com.fasterxml.jackson.dataformat.csv.CsvGenerator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * As seen in https://stackoverflow.com/questions/20937842/how-to-make-spring-mvc-return-csv-as-convenient-as-return-json
 */
@Configuration
public class CsvConfiguration {

  @Bean
  JacksonToCsvHttpMessageConverter csvMessageConverter() {
    CsvMapper mapper = new CsvMapper()
        .configure(CsvGenerator.Feature.ALWAYS_QUOTE_STRINGS, true)
        .configure(CsvGenerator.Feature.ALWAYS_QUOTE_EMPTY_STRINGS, true)
        .configure(CsvGenerator.Feature.ESCAPE_CONTROL_CHARS_WITH_ESCAPE_CHAR, true)
        .configure(CsvGenerator.Feature.ESCAPE_QUOTE_CHAR_WITH_ESCAPE_CHAR, true);
    return new JacksonToCsvHttpMessageConverter(mapper);
  }

}