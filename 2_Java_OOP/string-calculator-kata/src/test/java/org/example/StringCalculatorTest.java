package org.example;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {
    private StringCalculator calc;

    @BeforeEach
    void setUp() {
        calc = new StringCalculator();
    }

    // Step 1 testing
    @Test
    @DisplayName("Empty string returns zero")
    void testEmptyStringReturnsZero() {
        int expected = 0;
        int actual = calc.add("");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Single number returns number")
    void testSingleNumberReturnsThatNumber() {
        int expected = 5;
        int actual = calc.add("5");
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
            "'1,2', 3",
            "'10,20', 30"
    })
    @DisplayName("Two numbers returns sum")
    void testTwoNumbersReturnsSum(String input, int expected) {
        int actual = calc.add(input);
        assertEquals(expected, actual);
    }

    // Step 2 testing
    @ParameterizedTest
    @CsvSource({
            "'1,2,3', 6",
            "'2,2,2,2', 8",
            "'5,10,15,20,25', 75"
    })
    @DisplayName("Unknown amount of numbers in the string are accepted")
    void testUnknownAmountOfNumbers(String input, int expected) {
        int actual = calc.add(input);
        assertEquals(expected, actual);
    }

    // Step 3 testing
    @ParameterizedTest
    @CsvSource({
            "'1\n2,3', 6",
            "'10\n20\n30', 60"
    })
    @DisplayName("New lines as delimiters are accepted")
    void testNewLinesBetweenNumbers(String input, int expected) {
        int actual = calc.add(input);
        assertEquals(expected, actual);
    }

    // Step 4 testing
    @ParameterizedTest
    @CsvSource({
            "'//;\n4;7', 11",
            "'//@\n10@20', 30",
            "'//|\n10|23', 33",
            "'//\n1\n2', 3"
    })
    @DisplayName("Any custom delimiter is accepted")
    void testCustomDelimiters(String input, int expected) {
        int actual = calc.add(input);
        assertEquals(expected, actual);
    }

    // Step 5 testing
    @Test
    @DisplayName("Negative numbers are not allowed")
    void testNegativeNumbersNotAllowed() {
        String input = "2,-4,-5";
        String expected = "negatives not allowed: -4, -5";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calc.add(input);
        });
        assertEquals(expected, exception.getMessage());
    }

    // Step 6 Testing
    @ParameterizedTest
    @CsvSource({
            "'2,1001', 2",
            "'10,2000', 10"
    })
    @DisplayName("Numbers bigger than a thousand are ignored")
    void testNumbersBiggerThanThousandAreIgnored(String input, int expected) {
        int actual = calc.add(input);
        assertEquals(expected, actual);
    }
}
