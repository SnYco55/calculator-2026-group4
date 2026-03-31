package calculator;

import visitor.Visitor;

import java.math.BigDecimal;

import static java.lang.Math.abs;

public class MyRational implements Expression, Value{
    private final int numerator;
    private final int denominator;

    // Constructor also is the simplifier
    public MyRational(int numerator, int denominator) {
        if (denominator == 0) throw new ArithmeticException("Division by zero in MyRational");
        // Simplify rational
        int gcd = gcd(abs(numerator), abs(denominator));
        this.numerator = (denominator < 0 ? -numerator : numerator) / gcd;
        this.denominator = abs(denominator) / gcd;
    }

    public double getValue(){
        return (double) this.numerator / this.denominator;
    }

    @Override
    public MyComplex toComplex() {
        return new MyComplex(new BigDecimal(Float.toString((float) numerator / denominator)), new BigDecimal("0"));
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public int getNumerator() { return numerator; }
    public int getDenominator() { return denominator; }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public MyRational negate() {
        return new MyRational(-this.numerator, this.denominator);
    }

    public MyRational invert() {
        if (this.numerator == 0) throw new ArithmeticException("Division by zero");
        return new MyRational(this.denominator, this.numerator);
    }

    public int ppcm(MyRational other){
        return Math.abs(this.denominator * other.denominator) / gcd(this.denominator, other.denominator);
    }

    @Override
    public String toString() {
        if (denominator == 1) return Integer.toString(numerator);
        return numerator + "/" + denominator;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MyRational rational) {
            return (rational.numerator == this.numerator && rational.denominator == this.denominator);
        }
        if (obj instanceof MyReal real) {
            return (new BigDecimal(this.numerator/this.denominator).compareTo(real.getValue()) == 0);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * numerator + denominator;
    }
}
