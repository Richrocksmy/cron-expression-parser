package org.richrocksmy.cronexpressionparser.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.richrocksmy.cronexpressionparser.parser.cronexpressions.CronExpressionParser;
import org.richrocksmy.cronexpressionparser.parser.validator.TimeUnit;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CronParserTest {

  @Test
  @DisplayName("All fields should be correctly parsed when a parser exists for each expression")
  public void shouldCorrectlyParseAllFields() {
    // Given
    InputParser inputParser = mock(InputParser.class);
    when(inputParser.getMinute()).thenReturn("minute");
    when(inputParser.getHour()).thenReturn("hour");
    when(inputParser.getDayOfMonth()).thenReturn("dayOfMonth");
    when(inputParser.getMonth()).thenReturn("month");
    when(inputParser.getDayOfWeek()).thenReturn("dayOfWeek");
    when(inputParser.getCommand()).thenReturn("command");

    // When
    CronParser cronParser = new CronParser(inputParser, createExpressionParsers());

    // Then
    assertThat(cronParser.getMinute()).isEqualTo("minute");
    assertThat(cronParser.getHour()).isEqualTo("hour");
    assertThat(cronParser.getDayOfMonth()).isEqualTo("dayOfMonth");
    assertThat(cronParser.getMonth()).isEqualTo("month");
    assertThat(cronParser.getDayOfWeek()).isEqualTo("dayOfWeek");
    assertThat(cronParser.getCommand()).isEqualTo("command");
  }

  @Test
  @DisplayName("The parser should throw an exception when no appropriate parser exists for an expression")
  public void shouldThrowAnExceptionWhenNoParserExists() {
    // Given
    InputParser inputParser = mock(InputParser.class);
    when(inputParser.getMinute()).thenReturn("minute");
    when(inputParser.getHour()).thenReturn("hour");
    when(inputParser.getDayOfMonth()).thenReturn("dayOfMonth");
    when(inputParser.getMonth()).thenReturn("month");
    when(inputParser.getDayOfWeek()).thenReturn("dayOfWeek");
    when(inputParser.getCommand()).thenReturn("command");

    CronExpressionParser minuteExpressionParser = mock(CronExpressionParser.class);
    when(minuteExpressionParser.isValidParserFor("minute")).thenReturn(true);
    when(minuteExpressionParser.parse("minute", TimeUnit.MINUTE)).thenReturn("minute");

    // When
    ParsingException parsingException =
        assertThrows(ParsingException.class, () -> new CronParser(inputParser, List.of(minuteExpressionParser)));

    // Then
    assertThat(parsingException.getMessage()).isEqualTo("Failed to find parser for expression: hour");
  }
  private List<CronExpressionParser> createExpressionParsers() {
    CronExpressionParser minuteExpressionParser = mock(CronExpressionParser.class);
    when(minuteExpressionParser.isValidParserFor("minute")).thenReturn(true);
    when(minuteExpressionParser.parse("minute", TimeUnit.MINUTE)).thenReturn("minute");

    CronExpressionParser hourExpressionParser = mock(CronExpressionParser.class);
    when(minuteExpressionParser.isValidParserFor("hour")).thenReturn(true);
    when(minuteExpressionParser.parse("hour", TimeUnit.HOUR)).thenReturn("hour");

    CronExpressionParser dayOfMonthExpressionParser = mock(CronExpressionParser.class);
    when(minuteExpressionParser.isValidParserFor("dayOfMonth")).thenReturn(true);
    when(minuteExpressionParser.parse("dayOfMonth", TimeUnit.DAY_OF_MONTH)).thenReturn("dayOfMonth");

    CronExpressionParser monthExpressionParser = mock(CronExpressionParser.class);
    when(minuteExpressionParser.isValidParserFor("month")).thenReturn(true);
    when(minuteExpressionParser.parse("month", TimeUnit.MONTH)).thenReturn("month");

    CronExpressionParser dayOfWeekExpressionParser = mock(CronExpressionParser.class);
    when(minuteExpressionParser.isValidParserFor("dayOfWeek")).thenReturn(true);
    when(minuteExpressionParser.parse("dayOfWeek", TimeUnit.DAY_OF_WEEK)).thenReturn("dayOfWeek");

    return List.of(minuteExpressionParser, hourExpressionParser, dayOfMonthExpressionParser,
        monthExpressionParser, dayOfWeekExpressionParser);
  }
}
