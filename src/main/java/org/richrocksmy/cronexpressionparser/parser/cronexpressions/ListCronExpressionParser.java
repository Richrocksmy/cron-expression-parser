package org.richrocksmy.cronexpressionparser.parser.cronexpressions;

import org.richrocksmy.cronexpressionparser.parser.validator.TimeUnit;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ListCronExpressionParser implements CronExpressionParser {

  private final Pattern pattern = Pattern.compile("(\\d+)(,\\s*\\d+)*");

  @Override
  public boolean isValidParserFor(final String cronExpression) {
    return pattern.matcher(cronExpression).matches();
  }

  @Override
  public String parse(String cronExpression, TimeUnit timeUnit) {
    List<Integer> values =  Stream.of(cronExpression.split(","))
        .map(String::trim)
        .map(Integer::valueOf)
        .collect(Collectors.toList());

    timeUnit.throwIfValuesAreOutOfRange(values);

    return values.stream()
        .map(String::valueOf)
        .collect(Collectors.joining(" "));
  }
}
