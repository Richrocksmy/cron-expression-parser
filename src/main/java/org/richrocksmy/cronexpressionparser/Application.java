package org.richrocksmy.cronexpressionparser;

import org.richrocksmy.cronexpressionparser.output.ConsoleOutputWriter;
import org.richrocksmy.cronexpressionparser.parser.CronParser;
import org.richrocksmy.cronexpressionparser.parser.InputParser;

import java.io.PrintWriter;

public class Application {

  public static void main(String... args) {
    InputParser inputParser = new InputParser(args);
    CronParser cronParser = new CronParser(inputParser);

    PrintWriter printWriter = new PrintWriter(System.out);
    new ConsoleOutputWriter(cronParser, printWriter).write();
    printWriter.close();
  }
}
