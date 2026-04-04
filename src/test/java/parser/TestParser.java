package parser;

import calculator.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
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

        res = parser.parse("9/2");
        assertEquals(new MyRational(9,2), calculator.eval(res));

        res = parser.parse("0.2+0.45");
        assertEquals(new MyReal(new BigDecimal("0.65")), calculator.eval(res));

        res = parser.parse("2/3+5");
        assertEquals(new MyRational(17,3), calculator.eval(res));

        res = parser.parse("2+5i");
        assertEquals(new MyComplex(new BigDecimal("2"), new BigDecimal("5")), calculator.eval(res));

        res = parser.parse("3.0+5i");
        assertEquals(new MyComplex(new BigDecimal("3.0"), new BigDecimal("5")), calculator.eval(res));

        res = parser.parse("7.25i");
        assertEquals(new MyComplex(new BigDecimal("0"), new BigDecimal("7.25")), calculator.eval(res));

        res = parser.parse("3+5i+7");
        assertEquals(new MyComplex(new BigDecimal("10"), new BigDecimal("5")), calculator.eval(res));

        Precision.setPrecision(5);
        res = parser.parse("2.00E10");
        assertEquals(new MyReal(new BigDecimal("2E10")), calculator.eval(res));

        Precision.setPrecision(5);
        res = parser.parse("0.9E-5)");
        assertEquals(new MyReal(new BigDecimal("0.9E-5")), calculator.eval(res));

        Precision.setPrecision(2);

        res = parser.parse("2^3");
        assertEquals(new MyReal(new BigDecimal("8")), calculator.eval(res));

        Precision.setPrecision(6);
        res = parser.parse("((4+5+6)*(7+5/2/7)*9)");
        assertEquals(new MyReal(new BigDecimal("993.214")), new MyReal(((MyRational) calculator.eval(res)).getValue()));

        Precision.setPrecision(3);
        res = parser.parse("1+2*3^4/5-6");
        assertEquals(new MyReal(new BigDecimal("27.4")), new MyReal(((MyRational) calculator.eval(res)).getValue()));
    }

}
