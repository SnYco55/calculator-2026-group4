package parser;

import calculator.Expression;

public interface ExpressionParser {
    Expression parse(String expression);
}
