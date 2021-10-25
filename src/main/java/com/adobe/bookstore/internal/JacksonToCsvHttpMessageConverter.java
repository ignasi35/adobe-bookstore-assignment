package com.adobe.bookstore.internal;

import com.adobe.bookstore.api.CustomMediaType;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvGenerator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMappingException;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonValue;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

/**
 * Inspired in https://stackoverflow.com/questions/20937842/how-to-make-spring-mvc-return-csv-as-convenient-as-return-json
 */
public class JacksonToCsvHttpMessageConverter extends AbstractJackson2HttpMessageConverter {

  private static final Logger logger = LoggerFactory.getLogger(JacksonToCsvHttpMessageConverter.class);

  protected JacksonToCsvHttpMessageConverter(ObjectMapper objectMapper) {
    super(objectMapper, CustomMediaType.TEXT_CSV);
  }

  @Override
  protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
    if (object instanceof MappingJacksonValue) object = ((MappingJacksonValue) object).getValue();
    CsvMapper mapper = (CsvMapper) getObjectMapper();
    Class<?> klass = getType(object);
    CsvSchema schema = mapper.schemaFor(klass).withHeader().withColumnSeparator(';');
    JsonEncoding encoding = getJsonEncoding(outputMessage.getHeaders().getContentType());

    try (CsvGenerator generator = mapper.getFactory().createGenerator(outputMessage.getBody(), encoding)) {
      ObjectWriter writer = mapper.writer().with(schema);
      writer.writeValue(generator, object);
    } catch (CsvMappingException csvMappingException) {
      logger.debug("Spring tried to use the CSV mapper on an entity that has no CSV metadata (headers).", csvMappingException);
    }
  }

  private Class<?> getType(Object object) {
    if (object instanceof Collection) {
      Collection<?> c = (Collection<?>) object;
      // will fail when the returned collection is empty
      // trade-offs of copy/pasting code from StackOverflow
      return c.iterator().next().getClass();
    }
    if (object.getClass().isArray()) return object.getClass().getComponentType();
    return object.getClass();
  }

}
