package services;

import calculator.Expression;
import calculator.MyReal;
import calculator.Precision;
import org.springframework.stereotype.Service;
import parser.ExpressionParser;

/**
 * Service responsible for calculator operations.
 */
@Service
public class CalculatorService {

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
        Expression Eresult = parser.parse(input);

        if (Eresult instanceof MyReal real && angleMode.equals("DEG")) {
            Eresult = real.radiansToDegrees();
        } else if (Eresult instanceof MyReal real && angleMode.equals("RAD")) {
            Eresult =real.degreesToRadians();
        }

        return Eresult;
    }
}