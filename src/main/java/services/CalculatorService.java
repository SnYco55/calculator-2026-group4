package services;

import calculator.Expression;
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
    public Expression parseExpression(String input) {
        return parser.parse(input);
    }
}