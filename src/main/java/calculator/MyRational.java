package calculator;

import visitor.Visitor;

public class MyRational implements Expression, Value {
    private final int numerator;
    private final int denominator;

    public MyRational(int numerator, int denominator) {
        if (denominator == 0) throw new ArithmeticException("Division by zero in MyRational");
        // Simplify rational
        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        this.numerator = (denominator < 0 ? -numerator : numerator) / gcd;
        this.denominator = Math.abs(denominator) / gcd;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public int getNumerator() { return numerator; }
    public int getDenominator() { return denominator; }

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
        return new MyRational(-this.numerator, this.denominator);
    }

    @Override
    public Value invert() {
        if (this.numerator == 0) throw new ArithmeticException("Division by zero");
        return new MyRational(this.denominator, this.numerator);
    }

    @Override
    public Value add(MyNumber other) {
        return new MyRational(other.getValue() * this.denominator + this.numerator, this.denominator);
    }

    @Override
    public Value add(MyRational other) {
        return new MyRational(this.numerator * other.getDenominator() + other.getNumerator() * this.denominator, this.denominator * other.getDenominator());
    }

    @Override
    public Value add(MyReal other) {
        return new MyReal((double)this.numerator / this.denominator + other.getValue());
    }

    @Override
    public Value add(MyComplex other) {
        return new MyComplex((double)this.numerator / this.denominator + other.getReal(), other.getImaginary());
    }

    @Override
    public Value multiply(MyNumber other) {
        return new MyRational(this.numerator * other.getValue(), this.denominator);
    }

    @Override
    public Value multiply(MyRational other) {
        return new MyRational(this.numerator * other.getNumerator(), this.denominator * other.getDenominator());
    }

    @Override
    public Value multiply(MyReal other) {
        return new MyReal((double)this.numerator / this.denominator * other.getValue());
    }

    @Override
    public Value multiply(MyComplex other) {
        return new MyComplex((double)this.numerator / this.denominator * other.getReal(), (double)this.numerator / this.denominator * other.getImaginary());
    }

    @Override
    public String toString() {
        if (denominator == 1) return Integer.toString(numerator);
        return numerator + "/" + denominator;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof MyRational)) return false;
        MyRational r = (MyRational)o;
        return this.numerator == r.numerator && this.denominator == r.denominator;
    }

    @Override
    public int hashCode() {
        return 31 * numerator + denominator;
    }
}
