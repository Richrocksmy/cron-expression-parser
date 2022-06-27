package org.richrocksmy.cronexpressionparser.parser;

public class ParsingException extends RuntimeException {

  public ParsingException(final String parsingErrorMessage) {
    super(parsingErrorMessage);
  }
}
