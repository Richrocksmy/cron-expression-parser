package org.richrocksmy.cronexpressionparser.parser.cronexpressions;

import org.richrocksmy.cronexpressionparser.parser.validator.TimeUnit;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class SkipCronExpressionParser implements CronExpressionParser {

  // Pattern matches format */10
  private final Pattern wildcardPattern = Pattern.compile("[*]\\/(\\d+)");

  // Pattern matches format 1-10/10
  private final Pattern rangePattern = Pattern.compile("(\\d+-\\d+)\\/(\\d+)");

  @Override
  public boolean isValidParserFor(final String cronExpression) {
    return wildcardPattern.matcher(cronExpression).matches() || rangePattern.matcher(cronExpression).matches();
  }

  @Override
  public String parse(final String cronExpression, final TimeUnit timeUnit) {
    List<Integer> values = Collections.emptyList();

    String[] splitCronExpression = cronExpression.split("/");
    int skip = Integer.parseInt(splitCronExpression[1]);

    if(wildcardPattern.matcher(cronExpression).matches()) {
      values = createdSkippedValues(timeUnit.getMinValue(), timeUnit.getMaxValue(), skip);

    } else if (rangePattern.matcher(cronExpression).matches()) {
      String[] cronRange = splitCronExpression[0].split("-");
      int rangeStart = Integer.parseInt(cronRange[0]);
      int rangeEnd = Integer.parseInt(cronRange[1]);

      timeUnit.throwIfValuesAreOutOfRange(rangeStart, rangeEnd);
      timeUnit.throwIfRangeEndIsLessThanRangeStart(rangeStart, rangeEnd);

      values = createdSkippedValues(rangeStart, rangeEnd, skip);
    }

    return values.stream()
        .map(String::valueOf)
        .collect(Collectors.joining(" "));
  }

  private List<Integer> createdSkippedValues(final int rangeStart, final int rangeEnd, final int skip) {
    return Stream.iterate(rangeStart, i -> i <= rangeEnd, i -> i + skip)
        .collect(Collectors.toList());
  }

}
