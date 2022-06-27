package org.richrocksmy.cronexpressionparser.parser;

public final class CronParser {

  private final InputParser inputParser;

  private String minute;

  private String hour;

  private String dayOfMonth;

  private String month;

  private String dayOfWeek;

  private String command;

  public CronParser(final InputParser inputParser) {
    this.inputParser = inputParser;
  }

  public String getMinute() {
    return inputParser.getMinute();
  }

  public String getHour() {
    return inputParser.getHour();
  }

  public String getDayOfMonth() {
    return inputParser.getDayOfMonth();
  }

  public String getMonth() {
    return inputParser.getMonth();
  }

  public String getDayOfWeek() {
    return inputParser.getDayOfWeek();
  }

  public String getCommand() {
    return inputParser.getCommand();
  }
}
