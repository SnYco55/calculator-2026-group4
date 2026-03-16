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
        if (other instanceof MyReal || other instanceof MyComplex) return other.add(this);
        if (other instanceof MyNumber) {
            int n = ((MyNumber)other).getValue();
            return new MyRational(this.numerator + n * this.denominator, this.denominator);
        }
        MyRational r = (MyRational)other;
        return new MyRational(this.numerator * r.denominator + r.numerator * this.denominator, this.denominator * r.denominator);
    }

    @Override
    public Value subtract(Value other) {
        if (other instanceof MyReal) return new MyReal((double)this.numerator/this.denominator - ((MyReal)other).getValue());
        if (other instanceof MyComplex) return new MyComplex((double)this.numerator/this.denominator - ((MyComplex)other).getReal(), -((MyComplex)other).getImaginary());
        if (other instanceof MyNumber) {
            int n = ((MyNumber)other).getValue();
            return new MyRational(this.numerator - n * this.denominator, this.denominator);
        }
        MyRational r = (MyRational)other;
        return new MyRational(this.numerator * r.denominator - r.numerator * this.denominator, this.denominator * r.denominator);
    }

    @Override
    public Value multiply(Value other) {
        if (other instanceof MyReal || other instanceof MyComplex) return other.multiply(this);
        if (other instanceof MyNumber) {
            return new MyRational(this.numerator * ((MyNumber)other).getValue(), this.denominator);
        }
        MyRational r = (MyRational)other;
        return new MyRational(this.numerator * r.numerator, this.denominator * r.denominator);
    }

    @Override
    public Value divide(Value other) {
        if (other instanceof MyReal) return new MyReal((double)this.numerator/this.denominator / ((MyReal)other).getValue());
        if (other instanceof MyComplex) {
            MyComplex c = (MyComplex)other;
            double nr = (double)this.numerator/this.denominator;
            double den = c.getReal()*c.getReal() + c.getImaginary()*c.getImaginary();
            if (den == 0) throw new ArithmeticException("Division by zero in MyRational");
            return new MyComplex((nr * c.getReal())/den, (-nr * c.getImaginary())/den);
        }
        if (other instanceof MyNumber) {
            int n = ((MyNumber)other).getValue();
            if (n == 0) throw new ArithmeticException("Division by zero in MyRational");
            return new MyRational(this.numerator, this.denominator * n);
        }
        MyRational r = (MyRational)other;
        if (r.numerator == 0) throw new ArithmeticException("Division by zero in MyRational");
        return new MyRational(this.numerator * r.denominator, this.denominator * r.numerator);
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
