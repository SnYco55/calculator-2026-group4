package calculator;

import visitor.Visitor;

public class MyComplex implements Expression, Value {
    private final double real;
    private final double imaginary;

    public MyComplex(double r, double i) {
        this.real = r;
        this.imaginary = i;
    }

    public double getReal() { return real; }
    public double getImaginary() { return imaginary; }

    public void accept(Visitor v) {
        v.visit(this);
    }

    private double getRealPart(Value v) {
        if (v instanceof MyComplex) return ((MyComplex)v).getReal();
        if (v instanceof MyReal) return ((MyReal)v).getValue();
        if (v instanceof MyRational) return (double)((MyRational)v).getNumerator() / ((MyRational)v).getDenominator();
        if (v instanceof MyNumber) return ((MyNumber)v).getValue();
        throw new IllegalArgumentException("Unsupported type");
    }

    private double getImaginaryPart(Value v) {
        if (v instanceof MyComplex) return ((MyComplex)v).getImaginary();
        return 0.0;
    }

    @Override
    public Value add(Value other) {
        return new MyComplex(this.real + getRealPart(other), this.imaginary + getImaginaryPart(other));
    }

    @Override
    public Value subtract(Value other) {
        return new MyComplex(this.real - getRealPart(other), this.imaginary - getImaginaryPart(other));
    }

    @Override
    public Value multiply(Value other) {
        double oR = getRealPart(other);
        double oI = getImaginaryPart(other);
        return new MyComplex(this.real * oR - this.imaginary * oI, this.real * oI + this.imaginary * oR);
    }

    @Override
    public Value divide(Value other) {
        double oR = getRealPart(other);
        double oI = getImaginaryPart(other);
        double denominator = oR * oR + oI * oI;
        if (denominator == 0) throw new ArithmeticException("Division by zero in MyComplex");
        return new MyComplex(
            (this.real * oR + this.imaginary * oI) / denominator,
            (this.imaginary * oR - this.real * oI) / denominator
        );
    }

    @Override
    public String toString() {
        if (imaginary == 0) return Double.toString(real);
        if (real == 0) return imaginary + "i";
        return real + (imaginary > 0 ? "+" : "") + imaginary + "i";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof MyComplex)) return false;
        MyComplex c = (MyComplex)o;
        return Double.compare(this.real, c.real) == 0 && Double.compare(this.imaginary, c.imaginary) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(real) ^ Double.hashCode(imaginary);
    }
}
