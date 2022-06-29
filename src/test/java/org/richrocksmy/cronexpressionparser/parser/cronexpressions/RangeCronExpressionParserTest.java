package org.richrocksmy.cronexpressionparser.parser.cronexpressions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.richrocksmy.cronexpressionparser.parser.validator.TimeUnit;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RangeCronExpressionParserTest {

  @Test
  @DisplayName("The range expression parser should handle range expressions")
  public void shouldHandleRangeExpressions() {
    // Given
    String expression = "10-20";
    RangeCronExpressionParser rangeCronExpressionParser = new RangeCronExpressionParser();

    // When / Then
    assertThat(rangeCronExpressionParser.isValidParserFor(expression)).isTrue();
  }

  @Test
  @DisplayName("The range expression parser should not handle invalid range expressions")
  public void shouldNotHandleInvalidRangeExpressionsWithSingleLeadingInteger() {
    // Given
    String expression = "1-";
    RangeCronExpressionParser rangeCronExpressionParser = new RangeCronExpressionParser();

    // When / Then
    assertThat(rangeCronExpressionParser.isValidParserFor(expression)).isFalse();
  }

  @Test
  @DisplayName("The range expression parser should not handle invalid range expressions")
  public void shouldNotHandleInvalidRangeExpressionsWithSingleTrailingInteger() {
    // Given
    String expression = "-1";
    RangeCronExpressionParser rangeCronExpressionParser = new RangeCronExpressionParser();

    // When / Then
    assertThat(rangeCronExpressionParser.isValidParserFor(expression)).isFalse();
  }

  @Test
  @DisplayName("The range expression parser should not handle invalid range expressions")
  public void shouldNotHandleInvalidRangeExpressionsWithSlash() {
    // Given
    String expression = "*";
    RangeCronExpressionParser rangeCronExpressionParser = new RangeCronExpressionParser();

    // When / Then
    assertThat(rangeCronExpressionParser.isValidParserFor(expression)).isFalse();
  }

  @Test
  @DisplayName("The range expression parser should correctly parse valid range expressions")
  public void shouldParseValidRangeExpressions() {
    // Given
    String expression = "10-20";
    RangeCronExpressionParser rangeCronExpressionParser = new RangeCronExpressionParser();
    TimeUnit timeUnit = mock(TimeUnit.class);
    when(timeUnit.getValuesInRange(10, 20)).thenReturn(List.of(1, 2, 3));

    // When
    String parsedExpression = rangeCronExpressionParser.parse(expression, timeUnit);

    // Then
    assertThat(parsedExpression).isEqualTo("1 2 3");
  }
}
