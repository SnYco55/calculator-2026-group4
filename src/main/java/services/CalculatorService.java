package services;

import calculator.Expression;
import org.springframework.stereotype.Service;
import parser.ExpressionParser;

@Service
public class CalculatorService {

    private final ExpressionParser parser;

    public CalculatorService(ExpressionParser parser) {
        this.parser = parser;
    }

    public Expression parseExpression(String input) {
        return parser.parse(input);
    }
}