package calculator;

import java.util.List;

/**
 * Pow represents exponentiation (power) operation.
 * Extends Operation to compute base raised to the power of exponent.
 *
 * @see Operation
 */
public class Pow extends Operation{
    /**
     * Constructs a Pow operation with default notation.
     *
     * @param elist the list of expressions
     * @throws IllegalConstruction if the expression list is invalid
     */
    public /*constructor*/ Pow(List<Expression> elist) throws IllegalConstruction {
        this(elist, null);
    }

    /**
     * Constructs a Pow operation with specified notation.
     *
     * @param elist the list of expressions
     * @param n the notation for this operation
     * @throws IllegalConstruction if the expression list is invalid
     */
    public Pow(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist,n);
    }

    /**
     * Computes the exponentiation of two values.
     * Converts operands to complex numbers and performs power operation.
     *
     * @param l the base value
     * @param r the exponent value
     * @return the result of l raised to the power r
     */
    public Value op(Value l, Value r) {
        Value result = dispatch(l, r);

        if (result != null) {return result;}

        MyComplex left = l.toComplex();
        MyComplex right = r.toComplex();

        return Operation.format(new MyReal(left.getReal().pow(right.getReal().intValue())).toComplex());
    }

    /**
     * Computes the exponentiation of two rational numbers.
     *
     * @param left the base rational
     * @param right the exponent rational
     * @return the result of exponentiation
     */
    @Override
    public Value opRat(MyRational left, MyRational right) {
        return op(new MyReal(left.getValue()), new MyReal(right.getValue()));
    }

    /**
     * Computes the exponentiation of a rational and a number.
     *
     * @param left the base rational
     * @param right the exponent number
     * @return the result of exponentiation
     */
    @Override
    public Value opRatNum(MyRational left, MyNumber right) {
        return op(new MyReal(left.getValue()), right);
    }

    /**
     * Computes the exponentiation of a number and a rational.
     *
     * @param left the base number
     * @param right the exponent rational
     * @return the result of exponentiation
     */
    @Override
    public Value opNumRat(MyNumber left, MyRational right) {
        return op(left, new MyReal(right.getValue()));
    }
}
