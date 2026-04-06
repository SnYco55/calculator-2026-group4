package services;

import calculator.Expression;
import calculator.MyRational;
import calculator.MyReal;
import calculator.Precision;
import org.springframework.stereotype.Service;
import parser.ExpressionParser;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Service responsible for calculator operations.
 */
@Service
public class CalculatorService {

    private static final Pattern FRACTION_PATTERN = Pattern.compile("^([+-]?\\d+)\\s*/\\s*([+-]?\\d+)$");

    private final ExpressionParser parser;

    public CalculatorService(ExpressionParser parser) {
        this.parser = parser;
    }

    /**
     * Parses an input expression using the configured parser.
     *
     * @param input user expression
     * @return parsed expression
     */
    public Expression parseExpression(String input, String angleMode, int precision) {
        Precision.setPrecision(precision);
        return parser.parse(input);
    }

    /**
     * Converts a result between fraction and decimal representations.
     * If the input is a fraction, returns its decimal equivalent (with precision).
     * If the input is a decimal, returns its simplified fraction.
     *
     * @param rawResult the result to convert (fraction or decimal)
     * @param precision decimal precision for fraction->decimal conversion
     * @return the converted result
     */
    public String convertResult(String rawResult, int precision) {
        if (rawResult == null || rawResult.trim().isEmpty()) {
            throw new IllegalArgumentException("Result is empty");
        }

        int normalizedPrecision = sanitizePrecision(precision);
        Precision.setPrecision(normalizedPrecision);
        String value = rawResult.trim();

        Matcher fractionMatcher = FRACTION_PATTERN.matcher(value);
        if (fractionMatcher.matches()) {
            return fractionToDecimal(fractionMatcher, normalizedPrecision);
        }

        return decimalToFraction(value);
    }

    private String fractionToDecimal(Matcher fractionMatcher, int precision) {
        int numerator = Integer.parseInt(fractionMatcher.group(1));
        int denominator = Integer.parseInt(fractionMatcher.group(2));

        if (denominator == 0) {
            throw new IllegalArgumentException("Division by zero in fraction");
        }

        BigDecimal num = new BigDecimal(numerator);
        BigDecimal den = new BigDecimal(denominator);

        BigDecimal decimal = num.divide(den, precision, RoundingMode.HALF_EVEN);
        return decimal.stripTrailingZeros().toPlainString();
    }

    private String decimalToFraction(String value) {
        try {
            MyRational fraction = MyRational.fromDecimal(value);
            return fraction.toString();
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Unsupported result format", ex);
        }
    }


    private int sanitizePrecision(Integer precision) {
        if (precision == null) {
            return 2;
        }
        if (precision < 0) {
            return 0;
        }
        if (precision > 10) {
            return 10;
        }
        return precision;
    }
}