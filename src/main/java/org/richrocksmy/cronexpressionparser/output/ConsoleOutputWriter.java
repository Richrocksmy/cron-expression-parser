package org.richrocksmy.cronexpressionparser.output;

import org.richrocksmy.cronexpressionparser.parser.CronParser;

import java.io.PrintWriter;

public final record ConsoleOutputWriter(CronParser cronParser, PrintWriter printWriter) {

  public void write() {
    printWriter.println(String.format(Columns.MINUTE.template, cronParser.getMinute()));
    printWriter.println(String.format(Columns.HOUR.template, cronParser.getHour()));
    printWriter.println(String.format(Columns.DAY_OF_MONTH.template, cronParser.getDayOfMonth()));
    printWriter.println(String.format(Columns.MONTH.template, cronParser.getMonth()));
    printWriter.println(String.format(Columns.DAY_OF_WEEK.template, cronParser.getDayOfWeek()));
    printWriter.println(String.format(Columns.COMMAND.template, cronParser.getCommand()));
  }
}
