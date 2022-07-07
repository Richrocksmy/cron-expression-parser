package org.richrocksmy.cronexpressionparser.parser;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class CronExpression {

  private final String minute;

  private final String hour;

  private final String dayOfMonth;

  private final String month;

  private final String dayOfWeek;

  private final String command;

  private final List<String> futureExecutions = new ArrayList();

  public void setMinute(final String minute) {
    this.minute = minute;

    futureExecutions.stream()
        .forEach();
  }
}
