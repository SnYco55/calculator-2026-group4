package calculator;

import visitor.Visitor;

import java.math.BigDecimal;

/**
 * MyReal represents a real number with arbitrary precision using BigDecimal.
 * Supports special states like NaN, infinity, and undefined values.
 * Implements Expression and Value to support arithmetic operations and visitor pattern traversal.
 *
 * @see Expression
 * @see Value
 */
public class MyReal  implements Expression, Value{
    private final BigDecimal value;
    private final State state;

    /**
     * Enumeration for special states of a real number.
     */
    public enum State {
        VALID, NAN, POSITIVE_INFINITY, NEGATIVE_INFINITY, UNDEFINED
    }

    /**
     * Constructs a MyReal number with the specified value in VALID state.
     *
     * @param v the BigDecimal value
     */
    public MyReal(BigDecimal v) {
        this.value = v;
        this.state = State.VALID;
    }

    /**
     * Constructs a MyReal number with the specified value and state.
     *
     * @param v the BigDecimal value
     * @param s the state of this real number
     */
    public MyReal(BigDecimal v, State s) {
        this.value = v;
        this.state = s;
    }

    /**
     * Returns the BigDecimal value of this real number.
     *
     * @return the BigDecimal value
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * Returns the state of this real number.
     *
     * @return the state
     */
    public State getState() {return state;}

    /**
     * Accepts a visitor for the visitor design pattern.
     *
     * @param v the visitor object
     */
    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public String toString() {
        return switch (state) {
            case NAN -> "NaN";
            case POSITIVE_INFINITY -> "+Inf";
            case NEGATIVE_INFINITY -> "-Inf";
            case UNDEFINED -> "Undefined";
            default -> this.value.stripTrailingZeros().toString();
        };

    }

    /**
     * Checks equality based on stripped trailing zero comparison of values.
     *
     * @param o the object to compare to
     * @return true if both are MyReal with equal values
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof MyReal)) return false;
        return this.value.stripTrailingZeros().equals(((MyReal)o).value.stripTrailingZeros());
    }

    /**
     * Converts this real number to a complex number with zero imaginary part.
     *
     * @return a new MyComplex with this value as the real part
     */
    public MyComplex toComplex() {
        return new MyComplex(this.value, new BigDecimal("0"));
    }

    /**
     * Returns the hash code based on the stripped trailing zero value.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return this.value.stripTrailingZeros().hashCode();
    }
}
