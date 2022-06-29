package org.richrocksmy.cronexpressionparser.parser.cronexpressions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.richrocksmy.cronexpressionparser.parser.validator.TimeUnit;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WildcardCronExpressionParserTest {

  @Test
  @DisplayName("The wildcard expression parser should handle wildcard expressions")
  public void shouldHandleWildcardExpressions() {
    // Given
    String expression = "*";
    WildcardCronExpressionParser wildcardCronExpressionParser = new WildcardCronExpressionParser();

    // When / Then
    assertThat(wildcardCronExpressionParser.isValidParserFor(expression)).isTrue();
  }

  @Test
  @DisplayName("The wildcard expression parser should not handle invalid wildcard expressions")
  public void shouldNotHandleInvalidWildcardExpressionsWithInteger() {
    // Given
    String expression = "*1";
    WildcardCronExpressionParser wildcardCronExpressionParser = new WildcardCronExpressionParser();

    // When / Then
    assertThat(wildcardCronExpressionParser.isValidParserFor(expression)).isFalse();
  }

  @Test
  @DisplayName("The wildcard expression parser should not handle invalid wildcard expressions")
  public void shouldNotHandleInvalidWildcardExpressionsWithSlash() {
    // Given
    String expression = "/*";
    WildcardCronExpressionParser wildcardCronExpressionParser = new WildcardCronExpressionParser();

    // When / Then
    assertThat(wildcardCronExpressionParser.isValidParserFor(expression)).isFalse();
  }

  @Test
  @DisplayName("The wildcard expression parser should parse a valid wildcard expression")
  public void shouldNotHandleInvalidWildcardExpressions() {
    // Given
    String expression = "*";
    WildcardCronExpressionParser wildcardCronExpressionParser = new WildcardCronExpressionParser();
    TimeUnit timeUnit = mock(TimeUnit.class);
    when(timeUnit.getAllValues()).thenReturn(List.of(1, 2, 3));

    // When
    String parsedExpression = wildcardCronExpressionParser.parse(expression, timeUnit);

    // Then
    assertThat(parsedExpression).isEqualTo("1 2 3");
  }
}
