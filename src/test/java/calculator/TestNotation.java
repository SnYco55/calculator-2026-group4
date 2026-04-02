package calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import visitor.Printer;


class TestNotation {

    /* This is an auxilary method to avoid code duplication.
     */
	void testNotation(String s,Operation o,Notation n) {
		Printer p = new Printer(n);
		o.accept(p);
		assertEquals(s, p.getResult());
	}

	/* This is an auxilary method to avoid code duplication.
     */
	void testNotations(String symbol,int value1,int value2,Operation op) {
		//prefix notation:
		testNotation(symbol +" (" + value1 + ", " + value2 + ")", op, Notation.PREFIX);
		//infix notation:
		testNotation("( " + value1 + " " + symbol + " " + value2 + " )", op, Notation.INFIX);
		//postfix notation:
		testNotation("(" + value1 + ", " + value2 + ") " + symbol, op, Notation.POSTFIX);
	}

    @Test
    void testNotationValues() {
        Printer p = new Printer(Notation.INFIX);
        MyReal real = new MyReal(new BigDecimal("1.5"));
        real.accept(p);
        assertEquals("1.5", p.getResult());
        
        MyRational rat = new MyRational(1, 2);
        rat.accept(p);
        assertEquals("1/2", p.getResult());
        
        MyComplex comp = new MyComplex(new BigDecimal("1"), new BigDecimal("2"));
        comp.accept(p);
        assertEquals("1.00+2.00i", p.getResult());
    }

	@ParameterizedTest
	@ValueSource(strings = {"*", "+", "/", "-"})
	void testOutput(String symbol) {
		int value1 = 8;
		int value2 = 6;
		Operation op = null;

		List<Expression> params = Arrays.asList(new MyNumber(value1),new MyNumber(value2));
		try {
			//construct another type of operation depending on the input value
			//of the parameterised test
			switch (symbol) {
				case "+"	->	op = new Plus(params);
				case "-"	->	op = new Minus(params);
				case "*"	->	op = new Times(params);
				case "/"	->	op = new Divides(params);
				default		->	fail();
			}
		} catch (IllegalConstruction _) {
			fail();
		}
		testNotations(symbol, value1, value2, op);
	}

}
