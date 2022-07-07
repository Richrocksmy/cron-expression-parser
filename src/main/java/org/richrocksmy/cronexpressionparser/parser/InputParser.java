package org.richrocksmy.cronexpressionparser.parser;

import lombok.AccessLevel;
import lombok.Getter;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public final class InputParser {

  @Getter(AccessLevel.NONE)
  private final String[] input;

  private final String minute;

  private final String hour;

  private final String dayOfMonth;

  private final String month;

  private final String dayOfWeek;

  private final String command;

  public InputParser(final String[] input) {
    if(input == null ||
        input.length == 0 ||
        input[0] == null) {
      throw new ParsingException("Empty input supplied");
    }

    this.input = input[0].split(" ");

    minute = parseMinute();
    hour = parseHour();
    dayOfMonth = parseDayOfMonth();
    month = parseMonth();
    dayOfWeek = parseDayOfWeek();
    command = parseCommand();
  }

    private String parseMinute() {
    if(input.length < 1 || input[0].isBlank()) {
      throw new ParsingException("No value for <minute> supplied");
    }

    return input[0];
  }

  private String parseHour() {
    if(input.length < 2 || input[1].isBlank()) {
      throw new ParsingException("No value for <hour> supplied");
    }

    return input[1];
  }

  private String parseDayOfMonth() {
    if(input.length < 3 || input[2].isBlank()) {
      throw new ParsingException("No value for <day of month> supplied");
    }

    return input[2];
  }

  private String parseMonth() {
    if(input.length < 4 || input[3].isBlank()) {
      throw new ParsingException("No value for <month> supplied");
    }

    return input[3];
  }

  private String parseDayOfWeek() {
    if(input.length < 5 || input[4].isBlank()) {
      throw new ParsingException("No value for <day of week> supplied");
    }

    return input[4];
  }

  private String parseCommand() {
    if(input.length < 6 || input[5].isBlank()) {
      throw new ParsingException("No value for <command> supplied");
    }

    return Stream.of(Arrays.copyOfRange(input, 5, input.length))
        .collect(Collectors.joining(" "));
  }
}
