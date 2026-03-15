package calculator;

//Import Junit5 libraries for unit testing:
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import visitor.DepthCounter;
import visitor.OpsCounter;
import visitor.NbsCounter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;


class TestCounting {

    private int value1, value2;
    private Expression e;

    @BeforeEach
    void setUp() {
        value1 = 8;
        value2 = 6;
        e = null;
    }

    @Test
    void testNumberCounting() {
        e = new MyNumber(value1);
        DepthCounter dc = new DepthCounter(); e.accept(dc);
        assertEquals(0, dc.getResult());
        OpsCounter oc = new OpsCounter(); e.accept(oc);
        assertEquals(0, oc.getResult());
        NbsCounter nc = new NbsCounter(); e.accept(nc);
        assertEquals(1, nc.getResult());
    }

    @ParameterizedTest
    @ValueSource(strings = {"*", "+", "/", "-"})
    void testOperationCounting(String symbol) {
        List<Expression> params = Arrays.asList(new MyNumber(value1),new MyNumber(value2));
        //Operation op = null;
        try {
            //construct another type of operation depending on the input value
            //of the parameterised test
            switch (symbol) {
                case "+"	->	e = new Plus(params);
                case "-"	->	e = new Minus(params);
                case "*"	->	e = new Times(params);
                case "/"	->	e = new Divides(params);
                default		->	fail();
            }
        } catch (IllegalConstruction e) {
            fail();
        }
        DepthCounter dc = new DepthCounter(); e.accept(dc);
        assertEquals(1, dc.getResult(), "counting depth of an Operation");
        OpsCounter oc = new OpsCounter(); e.accept(oc);
        assertEquals(1, oc.getResult());
        NbsCounter nc = new NbsCounter(); e.accept(nc);
        assertEquals(2, nc.getResult());
    }

}
