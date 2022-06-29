package org.richrocksmy.cronexpressionparser;

import org.richrocksmy.cronexpressionparser.output.ConsoleOutputWriter;
import org.richrocksmy.cronexpressionparser.parser.CronParser;
import org.richrocksmy.cronexpressionparser.parser.InputParser;
import org.richrocksmy.cronexpressionparser.parser.ParsingException;
import org.richrocksmy.cronexpressionparser.parser.cronexpressions.CronExpressionParser;
import org.richrocksmy.cronexpressionparser.parser.cronexpressions.ListCronExpressionParser;
import org.richrocksmy.cronexpressionparser.parser.cronexpressions.NoOperationCronExpressionParser;
import org.richrocksmy.cronexpressionparser.parser.cronexpressions.RangeCronExpressionParser;
import org.richrocksmy.cronexpressionparser.parser.cronexpressions.SkipCronExpressionParser;
import org.richrocksmy.cronexpressionparser.parser.cronexpressions.WildcardCronExpressionParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.util.List;

public class Application {

  Logger LOG = LoggerFactory.getLogger(Application.class);

  public static void main(String... args) {
    Application application = new Application();
    application.run(args);
  }

  private void run(final String[] input) {
    try {
      InputParser inputParser = new InputParser(input);
      CronParser cronParser = new CronParser(inputParser, createExpressionParsers());

      PrintWriter printWriter = new PrintWriter(System.out);
      new ConsoleOutputWriter(cronParser, printWriter).write();
      printWriter.close();
    } catch (ParsingException e) {
      LOG.error(String.format("Failed to parse cron expression - %s", e.getMessage()));
    }
  }

  private List<CronExpressionParser> createExpressionParsers() {
    return List.of(new ListCronExpressionParser(), new NoOperationCronExpressionParser(), new RangeCronExpressionParser(),
        new SkipCronExpressionParser(), new WildcardCronExpressionParser());
  }
}
