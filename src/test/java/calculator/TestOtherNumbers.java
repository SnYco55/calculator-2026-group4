package calculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestOtherNumbers {

    @Test
    void testMyReal() {
        MyReal r1 = new MyReal(3.5);
        MyReal r2 = new MyReal(2.0);
        assertEquals(new MyReal(5.5), r1.add(r2));
        assertEquals(new MyReal(1.5), r1.subtract(r2));
        assertEquals(new MyReal(7.0), r1.multiply(r2));
        assertEquals(new MyReal(1.75), r1.divide(r2));

        // Testing Division by zero which returns Infinity in Double
        MyReal r3 = new MyReal(0.0);
        assertEquals(Double.POSITIVE_INFINITY, ((MyReal)r1.divide(r3)).getValue());
    }

    @Test
    void testMyRational() {
        MyRational r1 = new MyRational(1, 3);
        MyRational r2 = new MyRational(1, 6);
        assertEquals(new MyRational(1, 2), r1.add(r2));
        assertEquals(new MyRational(1, 6), r1.subtract(r2));
        assertEquals(new MyRational(1, 18), r1.multiply(r2));
        assertEquals(new MyRational(2, 1), r1.divide(r2));

        // Testing simplification
        MyRational r3 = new MyRational(2, 4);
        assertEquals(new MyRational(1, 2), r3);

        // Testing Division by zero Exception
        MyRational r4 = new MyRational(0, 5); // = 0
        assertThrows(ArithmeticException.class, () -> r1.divide(r4));
    }

    @Test
    void testMyComplex() {
        MyComplex c1 = new MyComplex(1, 2);
        MyComplex c2 = new MyComplex(3, 4);
        
        // (1+2i) + (3+4i) = 4 + 6i
        assertEquals(new MyComplex(4, 6), c1.add(c2));
        
        // (1+2i) - (3+4i) = -2 - 2i
        assertEquals(new MyComplex(-2, -2), c1.subtract(c2));
        
        // (1+2i) * (3+4i) = 3 + 4i + 6i - 8 = -5 + 10i
        assertEquals(new MyComplex(-5, 10), c1.multiply(c2));

        // Division by zero Exception
        MyComplex c3 = new MyComplex(0, 0);
        assertThrows(ArithmeticException.class, () -> c1.divide(c3));
    }

    @Test
    void testMixedTypesPromotion() {
        MyNumber n = new MyNumber(5);
        
        MyRational r = new MyRational(1, 2);
        // 1/2 + 5 = 11/2
        assertEquals(new MyRational(11, 2), r.add(n));

        MyComplex c = new MyComplex(2, 3);
        // (2+3i) * 5 = 10+15i
        assertEquals(new MyComplex(10, 15), c.multiply(n));
    }

    @Test
    void testRationalWithReal() {
        MyRational r = new MyRational(1, 4); // 0.25
        MyReal d = new MyReal(0.5);
        assertEquals(new MyReal(0.75), r.add(d));
        assertEquals(new MyReal(-0.25), r.subtract(d));
        assertEquals(new MyReal(0.125), r.multiply(d));
        assertEquals(new MyReal(0.5), r.divide(d));
    }

    @Test
    void testRationalWithComplex() {
        MyRational r = new MyRational(1, 2); // 0.5
        MyComplex c = new MyComplex(1, 1);
        assertEquals(new MyComplex(1.5, 1), r.add(c));
        assertEquals(new MyComplex(-0.5, -1), r.subtract(c));
        assertEquals(new MyComplex(0.5, 0.5), r.multiply(c));
        assertEquals(new MyComplex(0.25, -0.25), r.divide(c)); // 0.5 / (1+i) = 0.5(1-i)/2 = 0.25 - 0.25i
    }

    @Test
    void testRealWithComplex() {
        MyReal r = new MyReal(2.0);
        MyComplex c = new MyComplex(1, 2);
        assertEquals(new MyComplex(3.0, 2.0), r.add(c));
        assertEquals(new MyComplex(1.0, -2.0), r.subtract(c));
        assertEquals(new MyComplex(2.0, 4.0), r.multiply(c));
        assertEquals(new MyComplex(0.4, -0.8), r.divide(c)); // 2 / (1+2i) = 2(1-2i)/5 = 0.4 - 0.8i
    }

    @Test
    void testNumberWithAll() {
        MyNumber n = new MyNumber(10);
        MyRational r = new MyRational(1, 2);
        MyReal d = new MyReal(2.5);
        MyComplex c = new MyComplex(1, 1);

        assertEquals(new MyRational(21, 2), n.add(r)); // 10 + 1/2
        assertEquals(new MyRational(19, 2), n.subtract(r)); // 10 - 1/2
        assertEquals(new MyRational(5, 1), n.multiply(r)); // 10 * 1/2
        assertEquals(new MyRational(20, 1), n.divide(r)); // 10 / (1/2)

        assertEquals(new MyReal(12.5), n.add(d));
        assertEquals(new MyReal(7.5), n.subtract(d));
        assertEquals(new MyReal(25.0), n.multiply(d));
        assertEquals(new MyReal(4.0), n.divide(d));

        assertEquals(new MyComplex(11, 1), n.add(c));
        assertEquals(new MyComplex(9, -1), n.subtract(c));
        assertEquals(new MyComplex(10, 10), n.multiply(c));
        assertEquals(new MyComplex(5, -5), n.divide(c)); // 10 / (1+i) = 10(1-i)/2 = 5 - 5i
    }
}
