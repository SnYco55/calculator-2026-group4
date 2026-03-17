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

    @Override
    public Value add(Value other) {
        return other.add(this);
    }

    @Override
    public Value subtract(Value other) {
        return this.add(other.negate());
    }

    @Override
    public Value multiply(Value other) {
        return other.multiply(this);
    }

    @Override
    public Value divide(Value other) {
        return this.multiply(other.invert());
    }

    @Override
    public Value negate() {
        return new MyComplex(-this.real, -this.imaginary);
    }

    @Override
    public Value invert() {
        double divisor = this.real * this.real + this.imaginary * this.imaginary;
        if (divisor == 0) throw new ArithmeticException("Division by zero");
        return new MyComplex(this.real / divisor, -this.imaginary / divisor);
    }

    @Override
    public Value add(MyNumber other) {
        return new MyComplex(this.real + other.getValue(), this.imaginary);
    }

    @Override
    public Value add(MyRational other) {
        return new MyComplex(this.real + (double)other.getNumerator() / other.getDenominator(), this.imaginary);
    }

    @Override
    public Value add(MyReal other) {
        return new MyComplex(this.real + other.getValue(), this.imaginary);
    }

    @Override
    public Value add(MyComplex other) {
        return new MyComplex(this.real + other.getReal(), this.imaginary + other.getImaginary());
    }

    @Override
    public Value multiply(MyNumber other) {
        return new MyComplex(this.real * other.getValue(), this.imaginary * other.getValue());
    }

    @Override
    public Value multiply(MyRational other) {
        double r = (double)other.getNumerator() / other.getDenominator();
        return new MyComplex(this.real * r, this.imaginary * r);
    }

    @Override
    public Value multiply(MyReal other) {
        return new MyComplex(this.real * other.getValue(), this.imaginary * other.getValue());
    }

    @Override
    public Value multiply(MyComplex other) {
        return new MyComplex(this.real * other.getReal() - this.imaginary * other.getImaginary(),
                             this.real * other.getImaginary() + this.imaginary * other.getReal());
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
