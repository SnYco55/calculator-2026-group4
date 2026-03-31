package parser;

import calculator.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestParser {
    private Parser parser;
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        parser = new Parser();
        calculator = new Calculator();
    }

    @Test
    void testParse() throws IllegalConstruction {
        Expression res = parser.parse("2");
        assertEquals(new MyNumber(2), res);

        res = parser.parse("2+2");
        assertEquals(new Plus(Arrays.asList(new MyNumber(2), new MyNumber(2))), res);

        res = parser.parse("3*5");
        assertEquals(new Times(Arrays.asList(new MyNumber(3), new MyNumber(5))), res);

        res = parser.parse("(3*5)+2");
        assertEquals(new MyNumber(17), calculator.eval(res));

        res = parser.parse("5-2+7");
        assertEquals(new MyNumber(10), calculator.eval(res));

        res = parser.parse("6/2");
        assertEquals(new MyNumber(3), calculator.eval(res));


    }
}
