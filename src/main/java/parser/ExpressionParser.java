package parser;

import calculator.Expression;

/**
 * Defines a parser capable of converting a textual
 * mathematical expression into an Expression object.
 */
public interface ExpressionParser {
    Expression parse(String expression);
}
