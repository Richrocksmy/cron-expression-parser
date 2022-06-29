package org.richrocksmy.cronexpressionparser.parser.cronexpressions;

import org.richrocksmy.cronexpressionparser.parser.validator.TimeUnit;

public interface CronExpressionParser {

  boolean isValidParserFor(final String cronExpression);

  String parse(final String cronExpression, final TimeUnit timeUnit);
}
