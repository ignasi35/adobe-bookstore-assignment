package com.adobe.bookstore.api;

import org.springframework.http.MediaType;

public class CustomMediaType {

  // According to RFC 7111: https://www.rfc-editor.org/rfc/rfc7111
  public static final MediaType TEXT_CSV = new MediaType("text", "csv");
  public static final String TEXT_CSV_VALUE = "text/csv";
}
