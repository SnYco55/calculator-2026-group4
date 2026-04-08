package calculator;

import visitor.Visitor;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class MyComplex implements Expression, Value {
    private final BigDecimal real;
    private final BigDecimal imaginary;

    public MyComplex(BigDecimal r, BigDecimal i) {
        this.real = r;
        this.imaginary = i;
    }

    public BigDecimal getReal() { return real; }
    public BigDecimal getImaginary() { return imaginary; }

    @Override
    public MyComplex toComplex() {
        return this;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Value negate() {
        return new MyComplex(this.real.multiply(new BigDecimal("-1")), this.imaginary.multiply(new BigDecimal("-1")));
    }

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
