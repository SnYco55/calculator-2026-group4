package parser;

import calculator.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestParser {
    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
    }

    @Test
    public void testParse() throws IllegalConstruction {
        Expression res = parser.parse("2");
        assertEquals(new MyNumber(2), res);

        res = parser.parse("2+2");
        assertEquals(new Plus(Arrays.asList(new MyNumber(2), new MyNumber(2))), res);

        res = parser.parse("3*5");
        assertEquals(new Times(Arrays.asList(new MyNumber(3), new MyNumber(5))), res);
    }
}
