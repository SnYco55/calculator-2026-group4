package parser;

import calculator.Expression;
import calculator.MyNumber;
import org.springframework.stereotype.Component;

@Component
public class FakeParser implements  ExpressionParser {

    @Override
    public Expression parse(String expression) {
        return new MyNumber(5);
    }
}
