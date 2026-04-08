package calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TestOtherNumbers {

    Calculator calc = new Calculator();

    @BeforeEach
    void resetPrecision() {
        Precision.setPrecision(2);
    }

    @Test
    void testMyReal() throws IllegalConstruction {
        MyReal r1 = new MyReal(new BigDecimal("3.5"));
        MyReal r2 = new MyReal(new BigDecimal("2.0"));

        assertEquals(new MyReal(new BigDecimal("5.5")), calc.eval(new Plus(List.of(r1, r2))));
        assertEquals(new MyReal(new BigDecimal("1.5")), calc.eval(new Minus(List.of(r1, r2))));
        assertEquals(new MyNumber(7), calc.eval(new Times(List.of(r1, r2))));
        assertEquals(new MyReal(new BigDecimal("1.75")), calc.eval(new Divides(List.of(r1, r2))));

        MyReal r4 = new MyReal(new BigDecimal("0.0"));
        MyReal r3 = new MyReal(new BigDecimal("0.0"));
        assertEquals(MyReal.State.NAN, ((MyReal) calc.eval(new Divides(List.of(r4, r3)))).getState());

        assertEquals("NaN", ((MyReal) calc.eval(new Divides(List.of(r4, r3)))).toString());

        r4 = new MyReal(new BigDecimal("1.0"));
        assertEquals(MyReal.State.POSITIVE_INFINITY, ((MyReal) calc.eval(new Divides(List.of(r4, r3)))).getState());
        assertEquals("+Inf", ((MyReal) calc.eval(new Divides(List.of(r4, r3)))).toString());

        r4 = new MyReal(new BigDecimal("-1.0"));
        assertEquals(MyReal.State.NEGATIVE_INFINITY, ((MyReal) calc.eval(new Divides(List.of(r4, r3)))).getState());
        assertEquals("-Inf", ((MyReal) calc.eval(new Divides(List.of(r4, r3)))).toString());

        Divides test = new Divides(List.of(r1, r3));
        assertThrows(ArithmeticException.class,
                () -> calc.eval(test));
    }

    @Test
    void testMyRational() throws IllegalConstruction {
        MyRational r1 = new MyRational(1, 3);
        MyRational r2 = new MyRational(1, 6);

        assertEquals(new MyRational(1, 2), calc.eval(new Plus(List.of(r1, r2))));
        assertEquals(new MyRational(1, 6), calc.eval(new Minus(List.of(r1, r2))));
        assertEquals(new MyRational(1, 18), calc.eval(new Times(List.of(r1, r2))));
        assertEquals(new MyNumber(2), calc.eval(new Divides(List.of(r1, r2))));
        assertEquals(new MyNumber(3), r1.invert());
        assertEquals(new MyRational(-1, 3), r1.negate());

        MyRational r3 = new MyRational(2, 4);
        assertEquals(new MyRational(1, 2),r3);

        MyRational r4 = new MyRational(0, 5);

        Divides test = new Divides(List.of(r1, r4));
        assertThrows(ArithmeticException.class,
                () -> calc.eval(test));
    }

    @Test
    void testMyComplex() throws IllegalConstruction {
        MyComplex c1 = new MyComplex(new BigDecimal("1"), new BigDecimal("2"));
        MyComplex c2 = new MyComplex(new BigDecimal("3"), new BigDecimal("4"));

        assertEquals(new MyComplex(new BigDecimal("4"), new BigDecimal("6")), calc.eval(new Plus(List.of(c1, c2))));
        assertEquals(new MyComplex(new BigDecimal("-2"), new BigDecimal("-2")), calc.eval(new Minus(List.of(c1, c2))));
        assertEquals(new MyComplex(new BigDecimal("-5"), new BigDecimal("10")), calc.eval(new Times(List.of(c1, c2))));

        MyComplex c3 = new MyComplex(BigDecimal.ZERO, BigDecimal.ZERO);

        Divides test = new Divides(List.of(c1, c3));
        assertThrows(ArithmeticException.class,
                () -> calc.eval(test));
    }

    @Test
    void testMixedTypesPromotion() throws IllegalConstruction {
        MyNumber n = new MyNumber(5);
        MyRational r = new MyRational(1, 2);
        MyComplex c = new MyComplex(new BigDecimal("2"), new BigDecimal("3"));

        assertEquals(new MyRational(11, 2), calc.eval(new Plus(List.of(r, n))));
        assertEquals(new MyComplex(new BigDecimal("10"), new BigDecimal("15")), calc.eval(new Times(List.of(c, n))));
    }

    @Test
    void testRealWithComplex() throws IllegalConstruction {
        MyReal r = new MyReal(new BigDecimal("2.0"));
        MyComplex c = new MyComplex(new BigDecimal("1"), new BigDecimal("2"));

        assertEquals(new MyComplex(new BigDecimal("3.0"), new BigDecimal("2.0")), calc.eval(new Plus(List.of(r, c))));
        assertEquals(new MyComplex(new BigDecimal("1.0"), new BigDecimal("-2.0")), calc.eval(new Minus(List.of(r, c))));
        assertEquals(new MyComplex(new BigDecimal("2.0"), new BigDecimal("4.0")), calc.eval(new Times(List.of(r, c))));
        assertEquals(new MyComplex(new BigDecimal("0.4"), new BigDecimal("-0.8")), calc.eval(new Divides(List.of(r, c))));
    }

    @Test
    void testNumberWithAll() throws IllegalConstruction {
        MyNumber n = new MyNumber(10);
        MyRational r = new MyRational(1, 2);
        MyReal d = new MyReal(new BigDecimal("2.5"));
        MyComplex c = new MyComplex(new BigDecimal("1"), new BigDecimal("1"));

        assertEquals(new MyComplex(new BigDecimal("-1"), new BigDecimal("-1")), c.negate());

        assertEquals(new MyRational(21, 2), calc.eval(new Plus(List.of(n, r))));
        assertEquals(new MyRational(19, 2), calc.eval(new Minus(List.of(n, r))));
        assertEquals(new MyNumber(5), calc.eval(new Times(List.of(n, r))));
        assertEquals(new MyNumber(20), calc.eval(new Divides(List.of(n, r))));

        assertEquals(new MyReal(new BigDecimal("12.5")), calc.eval(new Plus(List.of(n, d))));
        assertEquals(new MyReal(new BigDecimal("7.5")), calc.eval(new Minus(List.of(n, d))));
        assertEquals(new MyNumber(25), calc.eval(new Times(List.of(n, d))));
        assertEquals(new MyNumber(4), calc.eval(new Divides(List.of(n, d))));

        assertEquals(new MyComplex(new BigDecimal("11"), new BigDecimal("1")), calc.eval(new Plus(List.of(n, c))));
        assertEquals(new MyComplex(new BigDecimal("9"), new BigDecimal("-1")), calc.eval(new Minus(List.of(n, c))));
        assertEquals(new MyComplex(new BigDecimal("10"), new BigDecimal("10")), calc.eval(new Times(List.of(n, c))));
        assertEquals(new MyComplex(new BigDecimal("5"), new BigDecimal("-5")), calc.eval(new Divides(List.of(n, c))));
    }


    @Test
    void testPrecision(){
        MyReal r1 = new MyReal(new BigDecimal("1"));
        MyReal r2 = new MyReal(new BigDecimal("3"));

        Precision.setPrecision(2);
        assertEquals(new MyReal(new BigDecimal("0.33")), calc.eval(new Divides(List.of(r1, r2))));

        Precision.setPrecision(5);
        assertEquals(new MyReal(new BigDecimal("0.33333")), calc.eval(new Divides(List.of(r1, r2))));

        Precision.setPrecision(2);
        r1 = new MyReal(new BigDecimal("0.6"));
        r2 = new MyReal(new BigDecimal("0.056"));

        assertEquals(new MyReal(new BigDecimal("0.656")), calc.eval(new Plus(List.of(r1, r2))));
    }
}