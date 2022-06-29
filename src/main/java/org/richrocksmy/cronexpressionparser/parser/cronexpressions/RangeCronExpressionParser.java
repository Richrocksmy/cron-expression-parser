package org.richrocksmy.cronexpressionparser.parser.cronexpressions;

import org.richrocksmy.cronexpressionparser.parser.validator.TimeUnit;

import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class RangeCronExpressionParser implements CronExpressionParser {

  // Pattern matches format 10-10
  private final Pattern pattern = Pattern.compile("\\d+-\\d+");

  @Override
  public boolean isValidParserFor(final String cronExpression) {
    return pattern.matcher(cronExpression).matches();
  }

  @Override
  public String parse(final String cronExpression, final TimeUnit timeUnit) {
    String[] parts = cronExpression.split("-");
    int rangeStart = Integer.valueOf(parts[0]);
    int rangeEnd = Integer.valueOf(parts[1]);

    return timeUnit.getValuesInRange(rangeStart, rangeEnd).stream()
        .map(String::valueOf)
        .collect(Collectors.joining(" "));
  }
}
