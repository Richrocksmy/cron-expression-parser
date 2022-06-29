package org.richrocksmy.cronexpressionparser.parser.validator;

import lombok.Getter;
import org.richrocksmy.cronexpressionparser.parser.ParsingException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
public enum TimeUnit {
  MINUTE(0, 59),
  HOUR(0, 59),
  DAY_OF_MONTH(1, 31),
  MONTH(1, 12),
  DAY_OF_WEEK(1, 7);

  private final List<Integer> allValues;

  public final int minValue;

  public final int maxValue;

  TimeUnit(final int minValue, final int maxValue) {
    allValues = IntStream.rangeClosed(minValue, maxValue)
        .boxed()
        .collect(Collectors.toList());

    this.minValue = minValue;
    this.maxValue = maxValue;
  }

  public void throwIfRangeEndIsLessThanRangeStart(final int rangeStart, final  int rangeEnd) {
    if(rangeEnd < rangeStart) {
      throw new ParsingException(String.format("End range cannot be less than start range (start: %s, end: %s) for timeunit: %s",
          rangeStart, rangeEnd, this.name()));
    }
  }

  public void throwIfValuesAreOutOfRange(final List<Integer> values) {
    if(!allValues.containsAll(values)) {
      throw new ParsingException(String.format("Invalid range specified (%s) for timeunit: %s",
          values, this.name()));
    }
  }

  public void throwIfValuesAreOutOfRange(final int rangeStart, final int rangeEnd) {
    throwIfValuesAreOutOfRange(List.of(rangeStart, rangeEnd));
  }

  public List<Integer> getValuesInRange(final int rangeStart, final int rangeEnd) {
    throwIfValuesAreOutOfRange(rangeStart, rangeEnd);
    throwIfRangeEndIsLessThanRangeStart(rangeStart, rangeEnd);

    return IntStream.rangeClosed(rangeStart, rangeEnd)
        .boxed()
        .collect(Collectors.toList());
  }
}
