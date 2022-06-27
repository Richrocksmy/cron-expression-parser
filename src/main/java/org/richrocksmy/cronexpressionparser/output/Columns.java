package org.richrocksmy.cronexpressionparser.output;

public enum Columns {

  MINUTE("minute        %s"),
  HOUR("hour          %s"),
  DAY_OF_MONTH("day of month  %s"),
  MONTH("month         %s"),
  DAY_OF_WEEK("day of week   %s"),
  COMMAND("command       %s");

  public final String template;

  Columns(String template) {
    this.template = template;
  }
}
