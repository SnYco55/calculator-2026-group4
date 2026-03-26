package calculator;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TestOtherNumbers {

    Calculator calc = new Calculator();

    @Test
    void testMyReal() throws IllegalConstruction {
        MyReal r1 = new MyReal(3.5);
        MyReal r2 = new MyReal(2.0);

        assertEquals(new MyReal(5.5), calc.eval(new Plus(List.of(r1, r2))));
        assertEquals(new MyReal(1.5), calc.eval(new Minus(List.of(r1, r2))));
        assertEquals(new MyNumber(7), calc.eval(new Times(List.of(r1, r2))));
        assertEquals(new MyReal(1.75), calc.eval(new Divides(List.of(r1, r2))));

        MyReal r4 = new MyReal(0.0);
        MyReal r3 = new MyReal(0.0);
        assertEquals(Double.NaN,
                ((MyReal) calc.eval(new Divides(List.of(r4, r3)))).getValue());

        r4 = new MyReal(1.0);
        assertEquals(new MyReal(Double.POSITIVE_INFINITY), calc.eval(new Divides(List.of(r4, r3))));

        r4 = new MyReal(-1.0);
        assertEquals(new MyReal(Double.NEGATIVE_INFINITY), calc.eval(new Divides(List.of(r4, r3))));

        assertThrows(ArithmeticException.class,
                () -> calc.eval(new Divides(List.of(r1, r3))));
    }

    @Test
    void testMyRational() throws IllegalConstruction {
        MyRational r1 = new MyRational(1, 3);
        MyRational r2 = new MyRational(1, 6);

        assertEquals(new MyRational(1, 2).getValue(), ((MyReal) calc.eval(new Plus(List.of(r1, r2)))).getValue());
        assertEquals(new MyRational(1, 6).getValue(), ((MyReal) calc.eval(new Minus(List.of(r1, r2)))).getValue());
        assertEquals(new MyRational(1, 18).getValue(), ((MyReal) calc.eval(new Times(List.of(r1, r2)))).getValue());
        assertEquals(new MyNumber(2), calc.eval(new Divides(List.of(r1, r2))));

        MyRational r3 = new MyRational(2, 4);
        assertTrue(new MyRational(1, 2).equals(r3));

        MyRational r4 = new MyRational(0, 5);
        assertThrows(ArithmeticException.class,
                () -> calc.eval(new Divides(List.of(r1, r4))));
    }

    @Test
    void testMyComplex() throws IllegalConstruction {
        MyComplex c1 = new MyComplex(1, 2);
        MyComplex c2 = new MyComplex(3, 4);

        assertEquals(new MyComplex(4, 6), calc.eval(new Plus(List.of(c1, c2))));
        assertEquals(new MyComplex(-2, -2), calc.eval(new Minus(List.of(c1, c2))));
        assertEquals(new MyComplex(-5, 10), calc.eval(new Times(List.of(c1, c2))));

        MyComplex c3 = new MyComplex(0, 0);
        assertThrows(ArithmeticException.class,
                () -> calc.eval(new Divides(List.of(c1, c3))));
    }

    @Test
    void testMixedTypesPromotion() throws IllegalConstruction {
        MyNumber n = new MyNumber(5);
        MyRational r = new MyRational(1, 2);
        MyComplex c = new MyComplex(2, 3);

        assertEquals(new MyRational(11, 2).getValue(), ((MyReal)calc.eval(new Plus(List.of(r, n)))).getValue());
        assertEquals(new MyComplex(10, 15), calc.eval(new Times(List.of(c, n))));
    }

    @Test
    void testRealWithComplex() throws IllegalConstruction {
        MyReal r = new MyReal(2.0);
        MyComplex c = new MyComplex(1, 2);

        assertEquals(new MyComplex(3.0, 2.0), calc.eval(new Plus(List.of(r, c))));
        assertEquals(new MyComplex(1.0, -2.0), calc.eval(new Minus(List.of(r, c))));
        assertEquals(new MyComplex(2.0, 4.0), calc.eval(new Times(List.of(r, c))));
        assertEquals(new MyComplex(0.4, -0.8), calc.eval(new Divides(List.of(r, c))));
    }

    @Test
    void testNumberWithAll() throws IllegalConstruction {
        MyNumber n = new MyNumber(10);
        MyRational r = new MyRational(1, 2);
        MyReal d = new MyReal(2.5);
        MyComplex c = new MyComplex(1, 1);

        assertEquals(new MyRational(21, 2).getValue(), ((MyReal) calc.eval(new Plus(List.of(n, r)))).getValue());
        assertEquals(new MyRational(19, 2).getValue(), ((MyReal) calc.eval(new Minus(List.of(n, r)))).getValue());
        assertEquals(new MyNumber(5), calc.eval(new Times(List.of(n, r))));
        assertEquals(new MyNumber(20), calc.eval(new Divides(List.of(n, r))));

        assertEquals(new MyReal(12.5), calc.eval(new Plus(List.of(n, d))));
        assertEquals(new MyReal(7.5), calc.eval(new Minus(List.of(n, d))));
        assertEquals(new MyNumber(25), calc.eval(new Times(List.of(n, d))));
        assertEquals(new MyNumber(4), calc.eval(new Divides(List.of(n, d))));

        assertEquals(new MyComplex(11, 1), calc.eval(new Plus(List.of(n, c))));
        assertEquals(new MyComplex(9, -1), calc.eval(new Minus(List.of(n, c))));
        assertEquals(new MyComplex(10, 10), calc.eval(new Times(List.of(n, c))));
        assertEquals(new MyComplex(5, -5), calc.eval(new Divides(List.of(n, c))));
    }
}