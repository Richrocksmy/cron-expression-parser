package org.richrocksmy.cronexpressionparser.parser;

import lombok.AccessLevel;
import lombok.Getter;
import org.richrocksmy.cronexpressionparser.parser.cronexpressions.CronExpressionParser;
import org.richrocksmy.cronexpressionparser.parser.validator.TimeUnit;

import java.util.List;

@Getter
public final class CronParser {

  @Getter(AccessLevel.NONE)
  private final InputParser inputParser;

  @Getter(AccessLevel.NONE)
  private final List<CronExpressionParser> cronExpressionParsers;

  private final String minute;

  private final String hour;

  private final String dayOfMonth;

  private final String month;

  private final String dayOfWeek;

  private final String command;

  public CronParser(final InputParser inputParser,
                    final List<CronExpressionParser> cronExpressionParsers) {
    this.inputParser = inputParser;
    this.cronExpressionParsers = cronExpressionParsers;

    minute = parse(inputParser.getMinute(), TimeUnit.MINUTE);
    hour = parse(inputParser.getHour(), TimeUnit.HOUR);
    dayOfMonth = parse(inputParser.getDayOfMonth(), TimeUnit.DAY_OF_MONTH);
    month = parse(inputParser.getMonth(), TimeUnit.MONTH);
    dayOfWeek = parse(inputParser.getDayOfWeek(), TimeUnit.DAY_OF_WEEK);
    command = inputParser.getCommand();
  }

  private String parse(final String expression, final TimeUnit timeUnit) {
    CronExpressionParser validCronExpressionParser = cronExpressionParsers.stream()
        .filter(cronExpressionParser -> cronExpressionParser.isValidParserFor(expression))
        .findFirst()
        .orElseThrow(() -> new ParsingException(String.format("Failed to find parser for expression: %s", expression)));

    return validCronExpressionParser.parse(expression, timeUnit);
  }
}
