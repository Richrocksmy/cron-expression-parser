package org.richrocksmy.cronexpressionparser.parser.cronexpressions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.richrocksmy.cronexpressionparser.parser.validator.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SkipCronExpressionParserTest {

  @Test
  @DisplayName("The skip expression parser should handle valid skip with wildcard expressions")
  public void shouldHandleValidSkipWithWildcardExpressions() {
    // Given
    String expression = "*/5";
    SkipCronExpressionParser skipCronExpressionParser = new SkipCronExpressionParser();

    // When / Then
    assertThat(skipCronExpressionParser.isValidParserFor(expression)).isTrue();
  }

  @Test
  @DisplayName("The skip expression parser should handle valid skip with range expressions")
  public void shouldHandleValidSkipWithRangeExpressions() {
    // Given
    String expression = "0-30/5";
    SkipCronExpressionParser skipCronExpressionParser = new SkipCronExpressionParser();

    // When / Then
    assertThat(skipCronExpressionParser.isValidParserFor(expression)).isTrue();
  }

  @Test
  @DisplayName("The skip expression parser should not handle invalid skip expressions")
  public void shouldNotHandleinValidSkipExpressions() {
    // Given
    String expression = "?";
    SkipCronExpressionParser skipCronExpressionParser = new SkipCronExpressionParser();

    // When / Then
    assertThat(skipCronExpressionParser.isValidParserFor(expression)).isFalse();
  }

  @Test
  @DisplayName("The skip expression parser should correctly parse valid skip with wildcard expressions")
  public void shouldCorrectlyParseValidSkipWithWildcardExpressions() {
    // Given
    String expression = "*/5";
    SkipCronExpressionParser skipCronExpressionParser = new SkipCronExpressionParser();

    TimeUnit timeUnit = mock(TimeUnit.class);
    when(timeUnit.getMinValue()).thenReturn(0);
    when(timeUnit.getMaxValue()).thenReturn(15);

    // When
    String parsed = skipCronExpressionParser.parse(expression, timeUnit);

    // Then
    assertThat(parsed).isEqualTo("0 5 10 15");
  }

  @Test
  @DisplayName("The skip expression parser should correctly parse valid skip with range expressions")
  public void shouldCorrectlyParseValidSkipWithRangeExpressions() {
    // Given
    String expression = "0-10/2";
    SkipCronExpressionParser skipCronExpressionParser = new SkipCronExpressionParser();

    TimeUnit timeUnit = mock(TimeUnit.class);
    when(timeUnit.getMinValue()).thenReturn(0);
    when(timeUnit.getMaxValue()).thenReturn(10);

    // When
    String parsed = skipCronExpressionParser.parse(expression, timeUnit);

    // Then
    assertThat(parsed).isEqualTo("0 2 4 6 8 10");
  }

  @Test
  @DisplayName("The skip expression parser should check that the range expression values are in time unit range and start range is less than end range")
  public void shouldThrowExceptionIfRangeExpressionValuesOutOfTimeUnitRange() {
    // Given
    String expression = "0-10/2";
    SkipCronExpressionParser skipCronExpressionParser = new SkipCronExpressionParser();

    TimeUnit timeUnit = mock(TimeUnit.class);
    when(timeUnit.getMinValue()).thenReturn(0);
    when(timeUnit.getMaxValue()).thenReturn(10);

    // When
    String parsed = skipCronExpressionParser.parse(expression, timeUnit);

    // Then
    assertThat(parsed).isEqualTo("0 2 4 6 8 10");

    verify(timeUnit).throwIfValuesAreOutOfRange(0, 10);
    verify(timeUnit).throwIfRangeEndIsLessThanRangeStart(0, 10);
  }

}
