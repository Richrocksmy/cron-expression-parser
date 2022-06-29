package org.richrocksmy.cronexpressionparser.parser.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.richrocksmy.cronexpressionparser.parser.ParsingException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TimeUnitTest {

  @Test
  @DisplayName("The TimeUnit Should generate the correct full set of integer values for MINUTE")
  public void shouldGenerateCorrectValuesForMinute() {
    // Given
    List<Integer> expectedValues = generateValuesForRange(0, 59);

    // When
    List<Integer> retrievedValues = TimeUnit.MINUTE.getAllValues();

    // Then
    assertThat(retrievedValues).containsAll(expectedValues);

  }

  @Test
  @DisplayName("The TimeUnit Should generate the correct full set of integer values for HOUR")
  public void shouldGenerateCorrectValuesForHour() {
    // Given
    List<Integer> expectedValues = generateValuesForRange(0, 59);

    // When
    List<Integer> retrievedValues = TimeUnit.HOUR.getAllValues();

    // Then
    assertThat(retrievedValues).containsAll(expectedValues);
  }

  @Test
  @DisplayName("The TimeUnit Should generate the correct full set of integer values for DAY_OF_MONTH")
  public void shouldGenerateCorrectValuesForDayOfMonth() {
    // Given
    List<Integer> expectedValues = generateValuesForRange(1, 31);

    // When
    List<Integer> retrievedValues = TimeUnit.DAY_OF_MONTH.getAllValues();

    // Then
    assertThat(retrievedValues).containsAll(expectedValues);
  }

  @Test
  @DisplayName("The TimeUnit Should generate the correct full set of integer values for MONTH")
  public void shouldGenerateCorrectValuesForMonth() {
    // Given
    List<Integer> expectedValues = generateValuesForRange(1, 12);

    // When
    List<Integer> retrievedValues = TimeUnit.MONTH.getAllValues();

    // Then
    assertThat(retrievedValues).containsAll(expectedValues);
  }

  @Test
  @DisplayName("The TimeUnit Should generate the correct full set of integer values for DAY_OF_WEEK")
  public void shouldGenerateCorrectValuesForDayOfWeek() {
    // Given
    List<Integer> expectedValues = generateValuesForRange(1, 7);

    // When
    List<Integer> retrievedValues = TimeUnit.DAY_OF_WEEK.getAllValues();

    // Then
    assertThat(retrievedValues).containsAll(expectedValues);
  }

  @Test
  @DisplayName("The TimeUnit should generate a correct subset of values given a range")
  public void shouldGenerateSubsetOfValues() {
    // Given
    List<Integer> expectedValues = generateValuesForRange(1, 4);

    // When
    List<Integer> retrievedValues = TimeUnit.DAY_OF_WEEK.getValuesInRange(1, 4);

    // Then
    assertThat(retrievedValues).containsAll(expectedValues);
  }

  @Test
  @DisplayName("The TimeUnit should throw an exception when the range end is less than the range start")
  public void shouldThrowExceptionWhenRangeEndLessThanStart() {
    // Given / When
    ParsingException parsingException = assertThrows(ParsingException.class,
        () -> TimeUnit.DAY_OF_WEEK.getValuesInRange(4, 1));

    // Then
    assertThat(parsingException.getMessage()).isEqualTo("End range cannot be less than start range (start: 4, end: 1) for timeunit: DAY_OF_WEEK");
  }

  @Test
  @DisplayName("The TimeUnit should throw and exception when values are out of range for TimeUnit type")
  public void shouldThrowExceptionWhenValuesOutOfRange() {
    // Given/ When
    ParsingException parsingException = assertThrows(ParsingException.class,
        () -> TimeUnit.DAY_OF_WEEK.getValuesInRange(1, 10));

    // Then
    assertThat(parsingException.getMessage()).isEqualTo("Invalid range specified ([1, 10]) for timeunit: DAY_OF_WEEK");
  }

  @Test
  @DisplayName("The TimeUnit should throw and exception when values are out of range for TimeUnit type given a start and end range")
  public void shouldThrowExceptionWhenValuesOutOfRangeGivenStartEndValues() {
    // Given / When
    ParsingException parsingException = assertThrows(ParsingException.class,
        () -> TimeUnit.DAY_OF_WEEK.throwIfValuesAreOutOfRange(1, 10));

    // Then
    assertThat(parsingException.getMessage()).isEqualTo("Invalid range specified ([1, 10]) for timeunit: DAY_OF_WEEK");
  }

  @Test
  @DisplayName("The TimeUnit should throw and exception when values are out of range for TimeUnit type given a list of values")
  public void shouldThrowExceptionWhenListValuesOutOfRangeGivenValues() {
    // Given
    List<Integer> values = generateValuesForRange(1, 10);

    // When
    ParsingException parsingException = assertThrows(ParsingException.class,
        () -> TimeUnit.DAY_OF_WEEK.throwIfValuesAreOutOfRange(values));

    // Then
    assertThat(parsingException.getMessage()).isEqualTo("Invalid range specified ([1, 2, 3, 4, 5, 6, 7, 8, 9, 10]) for timeunit: DAY_OF_WEEK");
  }

  private List<Integer> generateValuesForRange(final int rangeStart, final int rangeEnd) {
    return IntStream.rangeClosed(rangeStart, rangeEnd)
        .boxed()
        .collect(Collectors.toList());
  }

}
