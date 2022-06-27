package org.richrocksmy.cronexpressionparser.parser;

import lombok.Getter;

@Getter
public final class InputParser {

  private final String[] input;

  private final String minute;

  private final String hour;

  private final String dayOfMonth;

  private final String month;

  private final String dayOfWeek;

  private final String command;

  public InputParser(final String[] input) {
    this.input = input;

    minute = parseMinute();
    hour = parseHour();
    dayOfMonth = parseDayOfMonth();
    month = parseMonth();
    dayOfWeek = parseDayOfWeek();
    command = parseCommand();
  }

    private String parseMinute() {
    if(input.length < 1) {
      throw new ParsingException("No value for <minute> supplied");
    }

    return input[0];
  }

  private String parseHour() {
    if(input.length < 2) {
      throw new ParsingException("No value for <hour> supplied");
    }

    return input[1];
  }

  private String parseDayOfMonth() {
    if(input.length < 3) {
      throw new ParsingException("No value for <day of month> supplied");
    }

    return input[2];
  }

  private String parseMonth() {
    if(input.length < 4) {
      throw new ParsingException("No value for <month> supplied");
    }

    return input[3];
  }

  private String parseDayOfWeek() {
    if(input.length < 5) {
      throw new ParsingException("No value for <day of week> supplied");
    }

    return input[4];
  }

  private String parseCommand() {
    if(input.length < 6) {
      throw new ParsingException("No value for <command> supplied");
    }

    return input[5];
  }
}
