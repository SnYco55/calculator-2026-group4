package calculator;

import java.math.BigDecimal;
import java.util.List;

/**
 * Scientific represents scientific notation computation (base E power).
 * Extends Operation to support arithmetic expressions with scientific notation formatting.
 *
 * @see Operation
 */
public class Scientific extends Operation {
    /**
     * Constructs a Scientific operation with default notation.
     *
     * @param elist the list of expressions
     * @throws IllegalConstruction if the expression list is invalid
     */
    public Scientific(List<Expression> elist) {
        this(elist, null);
    }

    /**
     * Constructs a Scientific operation with specified notation.
     *
     * @param elist the list of expressions
     * @param n the notation for this operation
     * @throws IllegalConstruction if the expression list is invalid
     */
    public Scientific(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist,n);
    }

    @Override
    public Value op(Value l, Value r) {
        String base = l.toString();
        String power = r.toString();

        return new MyReal(new BigDecimal(base + "E" +  power));
    }

    /**
     * Performs scientific notation on two rational numbers.
     * Not supported in this implementation.
     *
     * @param left the left operand
     * @param right the right operand
     * @return null
     */
    @Override
    public Value opRat(MyRational left, MyRational right) {
        return null;
    }

    /**
     * Performs scientific notation on a rational and a number.
     * Not supported in this implementation.
     *
     * @param left the left rational operand
     * @param right the right number operand
     * @return null
     */
    @Override
    public Value opRatNum(MyRational left, MyNumber right) {
        return null;
    }

    /**
     * Performs scientific notation on a number and a rational.
     * Not supported in this implementation.
     *
     * @param left the left number operand
     * @param right the right rational operand
     * @return null
     */
    @Override
    public Value opNumRat(MyNumber left, MyRational right) {
        return null;
    }
}
