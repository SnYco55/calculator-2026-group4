package calculator;

import visitor.Visitor;
import static java.lang.Math.abs;

public class MyRational implements Expression, Value{
    private final int numerator;
    private final int denominator;

    public MyRational(int numerator, int denominator) {
        if (denominator == 0) throw new ArithmeticException("Division by zero in MyRational");
        // Simplify rational
        int gcd = gcd(abs(numerator), abs(denominator));
        this.numerator = (denominator < 0 ? -numerator : numerator) / gcd;
        this.denominator = abs(denominator) / gcd;
    }

    public int getMyNumber(){
        return numerator/denominator;
    }
    public double getValue(){
        return (double) this.numerator / this.denominator;
    }

    @Override
    public MyComplex toComplex() {
        return new MyComplex((double) numerator /denominator, 0);
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
        return abs(this.denominator*this.numerator) / gcd(this.denominator, other.denominator);
    }

    @Override
    public String toString() {
        if (denominator == 1) return Integer.toString(numerator);
        return numerator + "/" + denominator;
    }

    public boolean equals(MyRational o) {
        return this.numerator == o.numerator && this.denominator == o.denominator;
    }

    public boolean equals(MyNumber o) {
        return this.numerator/this.denominator == o.getValue();
    }

    public boolean equals(MyReal o) {
        return (double) this.numerator /this.denominator == o.getValue();
    }

    @Override
    public int hashCode() {
        return 31 * numerator + denominator;
    }
}
