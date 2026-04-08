package services;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;

import calculator.Expression;
import parser.ExpressionParser;


/**
 * Unit tests for CalculatorService.
 * Tests parsing, angle mode resolution, precision sanitization,
 * and result conversion (fraction <-> decimal).
 */
class TestCalculatorService {

    private CalculatorService service;
    private ExpressionParser mockParser;

    @BeforeEach
    void setUp() {
        mockParser = new MockExpressionParser();
        service = new CalculatorService(mockParser);
    }

    // ==================== parseExpression() Tests ====================

    @Test
    void testParseExpressionWithValidInput() {
        assertDoesNotThrow(() -> {
            Expression result = service.parseExpression("1+2", "RAD", 2);
            assertNotNull(result);
        });
    }

    @Test
    void testParseExpressionWithNullAngleMode() {
        assertDoesNotThrow(() -> {
            service.parseExpression("1+2", null, 2);
        });
    }

    @Test
    void testParseExpressionWithEmptyAngleMode() {
        assertDoesNotThrow(() -> {
            service.parseExpression("1+2", "", 2);
        });
    }

    @Test
    void testParseExpressionWithWhitespaceAngleMode() {
        assertDoesNotThrow(() -> {
            service.parseExpression("1+2", "   ", 2);
        });
    }

    @Test
    void testParseExpressionWithDEGMode() {
        assertDoesNotThrow(() -> {
            service.parseExpression("1+2", "DEG", 2);
        });
    }

    @Test
    void testParseExpressionWithLowercaseAngleMode() {
        assertDoesNotThrow(() -> {
            service.parseExpression("1+2", "rad", 2);
        });
    }

    @Test
    void testParseExpressionWithMixedCaseAngleMode() {
        assertDoesNotThrow(() -> {
            service.parseExpression("1+2", "DeG", 2);
        });
    }

    @Test
    void testParseExpressionWithInvalidAngleMode() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> service.parseExpression("1+2", "GRAD", 2)
        );
        assertTrue(exception.getMessage().contains("Invalid angleMode"));
    }

    @Test
    void testParseExpressionWithNullPrecision() {
        assertDoesNotThrow(() -> {
            service.parseExpression("1+2", "RAD", null);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 5, 10})
    void testParseExpressionWithValidPrecision(int precision) {
        assertDoesNotThrow(() -> {
            service.parseExpression("1+2", "RAD", precision);
        });
    }

    // ==================== resolveAngleMode() Tests ====================

    @Test
    void testResolveAngleModeRAD() {
        assertDoesNotThrow(() -> {
            service.parseExpression("1+2", "RAD", 2);
        });
    }

    @Test
    void testResolveAngleModeDEG() {
        assertDoesNotThrow(() -> {
            service.parseExpression("1+2", "DEG", 2);
        });
    }

    @Test
    void testResolveAngleModeNull() {
        assertDoesNotThrow(() -> {
            service.parseExpression("1+2", null, 2);
        });
    }

    @Test
    void testResolveAngleModeEmpty() {
        assertDoesNotThrow(() -> {
            service.parseExpression("1+2", "", 2);
        });
    }

    @Test
    void testResolveAngleModeInvalid() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> service.parseExpression("1+2", "INVALID", 2)
        );
        assertTrue(exception.getMessage().contains("Invalid angleMode"));
    }

    // ==================== sanitizePrecision() Tests ====================


    @Test
    void testSanitizePrecisionNegative() {
        String result = service.convertResult("1/2", -5);
        assertNotNull(result);
    }

    @Test
    void testSanitizePrecisionTooHigh() {
        String result = service.convertResult("1/2", 15);
        assertNotNull(result);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 5, 10})
    void testSanitizePrecisionValid(int precision) {
        String result = service.convertResult("1/2", precision);
        assertNotNull(result);
    }

    // ==================== convertResult() Tests - Fraction to Decimal ====================

    @Test
    void testConvertResultSimpleFraction() {
        String result = service.convertResult("1/2", 2);
        assertNotNull(result);
        assertTrue(result.contains("5") || result.equals("0.5"));
    }

    @Test
    void testConvertResultPositiveFraction() {
        String result = service.convertResult("3/4", 2);
        assertNotNull(result);
    }

    @Test
    void testConvertResultNegativeFraction() {
        String result = service.convertResult("-1/2", 2);
        assertNotNull(result);
    }

    @Test
    void testConvertResultFractionWithNegativeDenominator() {
        String result = service.convertResult("1/-2", 2);
        assertNotNull(result);
    }

    @Test
    void testConvertResultFractionWithBothNegative() {
        String result = service.convertResult("-1/-2", 2);
        assertNotNull(result);
    }

    @Test
    void testConvertResultFractionWithSpaces() {
        String result = service.convertResult("  1  /  2  ", 2);
        assertNotNull(result);
    }

    @Test
    void testConvertResultFractionWithZeroDenominator() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> service.convertResult("1/0", 2)
        );
        assertTrue(exception.getMessage().contains("Division by zero"));
    }

    @Test
    void testConvertResultLargeFraction() {
        String result = service.convertResult("999/1000", 3);
        assertNotNull(result);
    }

    @ParameterizedTest
    @CsvSource({
        "1/2, 0, 0",
        "1/2, 1, 1",
        "1/2, 2, 2",
        "1/2, 5, 5",
        "1/2, 10, 10"
    })
    void testConvertResultFractionWithDifferentPrecisions(String fraction, int precision, int expected) {
        String result = service.convertResult(fraction, precision);
        assertNotNull(result);
        assertNotEquals("", result);
    }

    // ==================== convertResult() Tests - Decimal to Fraction ====================

    @Test
    void testConvertResultSimpleDecimal() {
        String result = service.convertResult("0.5", 2);
        assertNotNull(result);
    }

    @Test
    void testConvertResultIntegerValue() {
        String result = service.convertResult("5", 2);
        assertNotNull(result);
    }

    @Test
    void testConvertResultNegativeDecimal() {
        String result = service.convertResult("-0.5", 2);
        assertNotNull(result);
    }

    @Test
    void testConvertResultZero() {
        String result = service.convertResult("0", 2);
        assertNotNull(result);
    }

    @Test
    void testConvertResultSmallDecimal() {
        String result = service.convertResult("0.25", 2);
        assertNotNull(result);
    }

    // ==================== convertResult() Tests - Edge Cases ====================

    @Test
    void testConvertResultNull() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> service.convertResult(null, 2)
        );
        assertTrue(exception.getMessage().contains("Result is empty"));
    }

    @Test
    void testConvertResultEmpty() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> service.convertResult("", 2)
        );
        assertTrue(exception.getMessage().contains("Result is empty"));
    }

    @Test
    void testConvertResultOnlySpaces() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> service.convertResult("   ", 2)
        );
        assertTrue(exception.getMessage().contains("Result is empty"));
    }

    @Test
    void testConvertResultInvalidFormat() {
        assertThrows(
            IllegalArgumentException.class,
            () -> service.convertResult("abc/def", 2)
        );
    }

    @Test
    void testConvertResultInvalidDecimalFormat() {
        assertThrows(
            IllegalArgumentException.class,
            () -> service.convertResult("12@34", 2)
        );
    }

    // ==================== Integration Tests ====================

    @Test
    void testParseAndConvertResult() {
        assertDoesNotThrow(() -> {
            Expression expr = service.parseExpression("1/2", "RAD", 2);
            assertNotNull(expr);
        });
    }

    @Test
    void testMultiplePrecisionLevels() {
        String result2 = service.convertResult("1/3", 2);
        String result5 = service.convertResult("1/3", 5);
        assertNotNull(result2);
        assertNotNull(result5);
    }

    @Test
    void testAngleModeIsCaseInsensitive() {
        assertDoesNotThrow(() -> {
            service.parseExpression("1+2", "rad", 2);
            service.parseExpression("1+2", "RAD", 2);
            service.parseExpression("1+2", "RaD", 2);
        });
    }

    @Test
    void testFractionPatternMatching() {
        String result1 = service.convertResult("1/2", 2);  // fraction
        String result2 = service.convertResult("0.5", 2);  // decimal
        assertNotNull(result1);
        assertNotNull(result2);
    }

    // ==================== Mock Implementation ====================

    /**
     * Mock ExpressionParser for testing purposes.
     */
    static class MockExpressionParser implements ExpressionParser {
        @Override
        public Expression parse(String expression) {
            return new MockExpression();
        }
    }

    /**
     * Mock Expression for testing purposes.
     */
    static class MockExpression implements Expression {
        @Override
        public void accept(visitor.Visitor v) {
        }

        @Override
        public String toString() {
            return "MockExpression";
        }
    }
}

