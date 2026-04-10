package calculator;

import visitor.Visitor;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * MyComplex represents a complex number with real and imaginary parts.
 * Implements Expression and Value to support arithmetic operations and visitor pattern traversal.
 *
 * @see Expression
 * @see Value
 */
public class MyComplex implements Expression, Value {
    private final BigDecimal real;
    private final BigDecimal imaginary;

    /**
     * Constructs a MyComplex number with the specified real and imaginary parts.
     *
     * @param r the real part
     * @param i the imaginary part
     */
    public MyComplex(BigDecimal r, BigDecimal i) {
        this.real = r;
        this.imaginary = i;
    }

    /**
     * Returns the real part of this complex number.
     *
     * @return the real part
     */
    public BigDecimal getReal() { return real; }

    /**
     * Returns the imaginary part of this complex number.
     *
     * @return the imaginary part
     */
    public BigDecimal getImaginary() { return imaginary; }

    @Override
    public MyComplex toComplex() {
        return this;
    }

    /**
     * Accepts a visitor for the visitor design pattern.
     *
     * @param v the visitor object
     */
    public void accept(Visitor v) {
        v.visit(this);
    }

    /**
     * Returns the negation of this complex number.
     *
     * @return a new MyComplex with negated real and imaginary parts
     */
    public Value negate() {
        return new MyComplex(this.real.multiply(new BigDecimal("-1")), this.imaginary.multiply(new BigDecimal("-1")));
    }

    /**
     * Returns the multiplicative inverse of this complex number.
     *
     * @return a new MyComplex representing 1/this
     * @throws ArithmeticException if this complex number is zero
     */
    public Value invert() {
        BigDecimal divisor = this.real.multiply(this.real).add(this.imaginary.multiply(this.imaginary));
        if (divisor.compareTo(BigDecimal.ZERO) == 0) throw new ArithmeticException("Division by zero");

        int decimalPlaces = Precision.getDecimalPlaces();
        return new MyComplex(
            this.real.divide(divisor, decimalPlaces, RoundingMode.HALF_EVEN),
            this.imaginary.multiply(new BigDecimal("-1")).divide(divisor, decimalPlaces, RoundingMode.HALF_EVEN)
        );
    }


    @Override
    public String toString() {
        if (imaginary.compareTo(BigDecimal.ZERO) == 0) return real.toString();
        if (real.compareTo(BigDecimal.ZERO) == 0) return imaginary + "i";
        return real.stripTrailingZeros() + (imaginary.compareTo(BigDecimal.ZERO) > 0 ? "+" : "") + imaginary.stripTrailingZeros() + "i";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof MyComplex)) return false;
        MyComplex c = (MyComplex)o;

        return this.real.stripTrailingZeros().equals(c.real.stripTrailingZeros()) && this.imaginary.stripTrailingZeros().equals(c.imaginary.stripTrailingZeros());
    }

    @Override
    public int hashCode() {
        return this.real.stripTrailingZeros().hashCode()+this.imaginary.stripTrailingZeros().hashCode();
    }
}
