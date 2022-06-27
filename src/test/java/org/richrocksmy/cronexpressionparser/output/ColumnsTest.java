package org.richrocksmy.cronexpressionparser.output;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ColumnsTest {

  @Test
  @DisplayName("Column templates should match the code configuration")
  public void columnTemplatesShoutBeCorrect() {
    assertThat(Columns.MINUTE.template).isEqualTo("minute        %s");
    assertThat(Columns.HOUR.template).isEqualTo("hour          %s");
    assertThat(Columns.DAY_OF_MONTH.template).isEqualTo("day of month  %s");
    assertThat(Columns.MONTH.template).isEqualTo("month         %s");
    assertThat(Columns.DAY_OF_WEEK.template).isEqualTo("day of week   %s");
    assertThat(Columns.COMMAND.template).isEqualTo("command       %s");
  }

};
