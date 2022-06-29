package org.richrocksmy.cronexpressionparser.parser.cronexpressions;

import org.richrocksmy.cronexpressionparser.parser.ParsingException;
import org.richrocksmy.cronexpressionparser.parser.validator.TimeUnit;

public final class NoOperationCronExpressionParser implements CronExpressionParser {

  @Override
  public boolean isValidParserFor(final String cronExpression) {
    return "?".equals(cronExpression);
  }

  @Override
  public String parse(final String cronExpression, final TimeUnit timeUnit) {
    switch(timeUnit) {
      case DAY_OF_MONTH, DAY_OF_WEEK:
        return "?";
      default:
        throw new ParsingException(String.format("Invalid timeunit %s for expression %s", timeUnit.name(), cronExpression));
    }
  }
}
