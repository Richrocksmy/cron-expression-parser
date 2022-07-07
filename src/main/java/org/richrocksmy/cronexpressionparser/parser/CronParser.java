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

  @Getter(AccessLevel.NONE)
  private final CronExpression cronExpression;

  public CronParser(final InputParser inputParser,
                    final List<CronExpressionParser> cronExpressionParsers) {
    this.inputParser = inputParser;
    this.cronExpressionParsers = cronExpressionParsers;

    cronExpression = CronExpression.builder()
        .minute(parse(inputParser.getMinute(), TimeUnit.MINUTE))
        .hour(parse(inputParser.getHour(), TimeUnit.HOUR))
        .dayOfMonth(parse(inputParser.getDayOfMonth(), TimeUnit.DAY_OF_MONTH))
        .month(parse(inputParser.getMonth(), TimeUnit.MONTH))
        .dayOfWeek(parse(inputParser.getDayOfWeek(), TimeUnit.DAY_OF_WEEK))
        .command(inputParser.getCommand())
        .build();
  }

  private String parse(final String expression, final TimeUnit timeUnit) {
    CronExpressionParser validCronExpressionParser = cronExpressionParsers.stream()
        .filter(cronExpressionParser -> cronExpressionParser.isValidParserFor(expression))
        .findFirst()
        .orElseThrow(() -> new ParsingException(String.format("Failed to find parser for expression: %s", expression)));

    return validCronExpressionParser.parse(expression, timeUnit);
  }

  public String getMinute() {
    return cronExpression.getMinute();
  }

  public String getHour() {
    return cronExpression.getHour();
  }

  public String getDayOfMonth() {
    return cronExpression.getDayOfMonth();
  }

  public String getMonth() {
    return cronExpression.getMonth();
  }

  public String getDayOfWeek() {
    return cronExpression.getDayOfWeek();
  }

  public String getCommand() {
    return cronExpression.getCommand();
  }

}
