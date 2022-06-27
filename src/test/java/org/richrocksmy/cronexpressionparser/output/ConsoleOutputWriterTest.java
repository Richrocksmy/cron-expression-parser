package org.richrocksmy.cronexpressionparser.output;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.richrocksmy.cronexpressionparser.parser.CronParser;

import java.io.PrintWriter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ConsoleOutputWriterTest {

  @Test
  @DisplayName("The console output write should output the results to a table with the field name taking the first 14 columns and the times as a space separated list")
  public void shouldOutputToConsoleCorrectly() {
    // Given
    CronParser cronParser = mock(CronParser.class);
    when(cronParser.getMinute()).thenReturn("minute");
    when(cronParser.getHour()).thenReturn("hour");
    when(cronParser.getDayOfMonth()).thenReturn("day of month");
    when(cronParser.getMonth()).thenReturn("month");
    when(cronParser.getDayOfWeek()).thenReturn("day of week");
    when(cronParser.getCommand()).thenReturn("command");

    PrintWriter printWriter = mock(PrintWriter.class);

    ConsoleOutputWriter consoleOutputWriter = new ConsoleOutputWriter(cronParser, printWriter);

    // When
    consoleOutputWriter.write();

    // Then
    verify(printWriter).println("minute        minute");
    verify(printWriter).println("hour          hour");
    verify(printWriter).println("day of month  day of month");
    verify(printWriter).println("month         month");
    verify(printWriter).println("day of week   day of week");
    verify(printWriter).println("command       command");
  }
}
