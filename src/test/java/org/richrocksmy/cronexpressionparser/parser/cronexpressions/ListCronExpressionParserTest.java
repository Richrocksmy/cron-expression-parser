package org.richrocksmy.cronexpressionparser.parser.cronexpressions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.richrocksmy.cronexpressionparser.parser.validator.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class ListCronExpressionParserTest {

  @Test
  @DisplayName("The list expression parser should handle valid list expressions")
  public void shouldHandleValidListExpressions() {
    // Given
    String expression = "1,2,3,4";
    ListCronExpressionParser listCronExpressionParser = new ListCronExpressionParser();

    // When / Then
    assertThat(listCronExpressionParser.isValidParserFor(expression)).isTrue();
  }

  @Test
  @DisplayName("The list expression parser should not handle invalid list expressions")
  public void shouldNotHandleInvalidListExpressions() {
    // Given
    String expression = "1-";
    ListCronExpressionParser listCronExpressionParser = new ListCronExpressionParser();

    // When / Then
    assertThat(listCronExpressionParser.isValidParserFor(expression)).isFalse();
  }

  @Test
  @DisplayName("The list expression parser should correctly parse valid list expressions")
  public void shouldParseValidListExpressions() {
    // Given
    String expression = "1,2,3";
    ListCronExpressionParser listCronExpressionParser = new ListCronExpressionParser();
    TimeUnit timeUnit = mock(TimeUnit.class);

    // When
    String parsed = listCronExpressionParser.parse(expression, timeUnit);

    // Then
    assertThat(parsed).isEqualTo("1 2 3");

  }
}
