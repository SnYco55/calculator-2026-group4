package calculator;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

class TestOperation {

	private Operation o;
	private Operation o2;

	@BeforeEach
	void setUp() throws Exception {
		List<Expression> params1 = Arrays.asList(new MyNumber(3), new MyNumber(4), new MyNumber(5));
		List<Expression> params2 = Arrays.asList(new MyNumber(5), new MyNumber(4));
		List<Expression> params3 = Arrays.asList(new Plus(params1), new Minus(params2), new MyNumber(7));
		o = new Divides(params3);
		o2 = new Divides(params3);
	}

	@Test
	void testEquals() {
		assertEquals(o,o2);
	}

	@Test
	void testCountDepth() {
		assertEquals(2, o.countDepth());
	}

	@Test
	void testCountOps() {
		assertEquals(3, o.countOps());
	}

	@Test
	void testCountNbs() {
		assertEquals(Integer.valueOf(6), o.countNbs());
	}

	@Test
	void testSameNotation() throws Exception{
		//Basic case
		List <Expression> params = Arrays.asList(new MyNumber(4), new MyNumber(5));
		List <Expression> params2 = Arrays.asList(new Plus(params, Notation.INFIX), new Minus(params, Notation.POSTFIX), new Times(params, Notation.PREFIX));

		assertThrows(IllegalConstruction.class, () -> new Times(params2));

		List <Expression> params3 = Arrays.asList(new Plus(params), new Minus(params), new Times(params, Notation.PREFIX));

		//Another case
		assertThrows(IllegalConstruction.class, () -> Arrays.asList(new Plus(params3), new MyNumber(6), new MyNumber(7)));

		//If everything is good
		List <Expression> params4 = Arrays.asList(new Plus(params), new Minus(params), new Times(params));
		assertDoesNotThrow(() -> new Plus(params4));
	}
}
