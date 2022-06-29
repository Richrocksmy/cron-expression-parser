package org.richrocksmy.cronexpressionparser.parser.cronexpressions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.richrocksmy.cronexpressionparser.parser.ParsingException;
import org.richrocksmy.cronexpressionparser.parser.validator.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NoOperationCronExpressionParserTest {

  @Test
  @DisplayName("The no operation expression parser should handle no operation expressions")
  public void shouldHandleNoOperationExpressions() {
    // Given
    String expression = "?";
    NoOperationCronExpressionParser noOperationCronExpressionParser = new NoOperationCronExpressionParser();

    // When / Then
    assertThat(noOperationCronExpressionParser.isValidParserFor(expression)).isTrue();
  }

  @Test
  @DisplayName("The no operation expression parser should not handle invalid no operation expressions")
  public void shouldNotHandleInvalidNoOperationExpressions() {
    // Given
    String expression = "1-";
    NoOperationCronExpressionParser noOperationCronExpressionParser = new NoOperationCronExpressionParser();

    // When / Then
    assertThat(noOperationCronExpressionParser.isValidParserFor(expression)).isFalse();
  }

  @Test
  @DisplayName("The no operation expression parser should correctly parse valid no operation expressions with day of week timeunit")
  public void shouldParseValidNoOperationDayOfWeekExpressions() {
    // Given
    String expression = "?";
    NoOperationCronExpressionParser noOperationCronExpressionParser = new NoOperationCronExpressionParser();

    // When
    String parsedExpression = noOperationCronExpressionParser.parse(expression, TimeUnit.DAY_OF_WEEK);

    // Then
    assertThat(parsedExpression).isEqualTo("?");
  }

  @Test
  @DisplayName("The no operation expression parser should correctly parse valid no operation expressions with day of month timeunit")
  public void shouldParseValidNoOperationDayOfMonthExpressions() {
    // Given
    String expression = "?";
    NoOperationCronExpressionParser noOperationCronExpressionParser = new NoOperationCronExpressionParser();

    // When
    String parsedExpression = noOperationCronExpressionParser.parse(expression, TimeUnit.DAY_OF_MONTH);

    // Then
    assertThat(parsedExpression).isEqualTo("?");
  }

  @Test
  @DisplayName("The no operation expression parser should throw an exception when valid no operation expressions with invalid timeunit")
  public void shouldParseValidNoOperationInvalidTimeUnitExpressions() {
    // Given
    String expression = "?";
    NoOperationCronExpressionParser noOperationCronExpressionParser = new NoOperationCronExpressionParser();
    TimeUnit timeUnit = mock(TimeUnit.class);
    when(timeUnit.name()).thenReturn("HOUR");

    // When
    ParsingException parsingException = assertThrows(ParsingException.class, () ->
        noOperationCronExpressionParser.parse(expression, timeUnit));

    // Then
    assertThat(parsingException.getMessage()).isEqualTo("Invalid timeunit HOUR for expression ?");
  }
}
