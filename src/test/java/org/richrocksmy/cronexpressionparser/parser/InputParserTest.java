package org.richrocksmy.cronexpressionparser.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InputParserTest {

  @Test
  @DisplayName("Input parser should correctly parse when the input contains a value for <minute>, <hour>, <day of month>, <month>, <day of week> and <command>")
  public void shouldCorrectlyParseIfInputContainsCorrectInput() {
    // Given
    String[] input = {"minute", "hour", "day of month", "month", "day of week", "command"};

    // When
    InputParser parsedInputParser = new InputParser(input);

    // Then
    assertThat(parsedInputParser.getMinute()).isEqualTo("minute");
    assertThat(parsedInputParser.getHour()).isEqualTo("hour");
    assertThat(parsedInputParser.getDayOfMonth()).isEqualTo("day of month");
    assertThat(parsedInputParser.getMonth()).isEqualTo("month");
    assertThat(parsedInputParser.getDayOfWeek()).isEqualTo("day of week");
    assertThat(parsedInputParser.getCommand()).isEqualTo("command");
  }

  @Test
  @DisplayName("Input parser should throw an exception when the input doesn't contain a value for <minute>")
  public void shouldThrowExceptionIfInputDoesntContainMinute() {
    // Given
    String[] input = {};

    // When
    ParsingException parsingException = assertThrows(ParsingException.class, () -> new InputParser(input));

    // Then
    assertThat(parsingException.getMessage()).isEqualTo("No value for <minute> supplied");
  }

  @Test
  @DisplayName("Input parser should throw an exception when the input doesn't contain a value for <hour>")
  public void shouldThrowExceptionIfInputDoesntContainHour() {
    // Given
    String[] input = {"minute"};

    // When
    ParsingException parsingException = assertThrows(ParsingException.class, () -> new InputParser(input));

    // Then
    assertThat(parsingException.getMessage()).isEqualTo("No value for <hour> supplied");
  }

  @Test
  @DisplayName("Input parser should throw an exception when the input doesn't contain a value for <day of month>")
  public void shouldThrowExceptionIfInputDoesntContainDayOfMonth() {
    // Given
    String[] input = {"minute", "hour"};

    // When
    ParsingException parsingException = assertThrows(ParsingException.class, () -> new InputParser(input));

    // Then
    assertThat(parsingException.getMessage()).isEqualTo("No value for <day of month> supplied");

  }

  @Test
  @DisplayName("Input parser should throw an exception when the input doesn't contain a value for <month>")
  public void shouldThrowExceptionIfInputDoesntContainMonth() {
    // Given
    String[] input = {"minute", "hour", "day of month"};

    // When
    ParsingException parsingException = assertThrows(ParsingException.class, () -> new InputParser(input));

    // Then
    assertThat(parsingException.getMessage()).isEqualTo("No value for <month> supplied");
  }


  @Test
  @DisplayName("Input parser should throw an exception when the input doesn't contain a value for <day of week>")
  public void shouldThrowExceptionIfInputDoesntContainDayOfWeek() {
    // Given
    String[] input = {"minute", "hour", "day of month", "month"};

    // When
    ParsingException parsingException = assertThrows(ParsingException.class, () -> new InputParser(input));

    // Then
    assertThat(parsingException.getMessage()).isEqualTo("No value for <day of week> supplied");
  }

  @Test
  @DisplayName("Input parser should throw an exception when the input doesn't contain a value for <command>")
  public void shouldThrowExceptionIfInputDoesntContainCommand() {
    // Given
    String[] input = {"minute", "hour", "day of month", "month", "day of week"};

    // When
    ParsingException parsingException = assertThrows(ParsingException.class, () -> new InputParser(input));

    // Then
    assertThat(parsingException.getMessage()).isEqualTo("No value for <command> supplied");
  }

}
