package org.richrocksmy.cronexpressionparser.parser.cronexpressions;

import org.richrocksmy.cronexpressionparser.parser.validator.TimeUnit;

import java.util.stream.Collectors;

public final class WildcardCronExpressionParser implements CronExpressionParser {

  @Override
  public boolean isValidParserFor(final String cronExpression) {
    return "*".equals(cronExpression);
  }

  @Override
  public String parse(final String cronExpression, final TimeUnit timeUnit) {
    return timeUnit.getAllValues().stream()
        .map(String::valueOf)
        .collect(Collectors.joining(" "));
  }
}
