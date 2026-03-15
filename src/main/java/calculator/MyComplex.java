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
        if (other instanceof MyNumber) return new MyComplex(this.real + ((MyNumber)other).getValue(), this.imaginary);
        if (other instanceof MyComplex) return new MyComplex(this.real + ((MyComplex)other).real, this.imaginary + ((MyComplex)other).imaginary);
        throw new IllegalArgumentException("Unsupported type for addition with MyComplex");
    }

    @Override
    public Value subtract(Value other) {
        if (other instanceof MyNumber) return new MyComplex(this.real - ((MyNumber)other).getValue(), this.imaginary);
        if (other instanceof MyComplex) return new MyComplex(this.real - ((MyComplex)other).real, this.imaginary - ((MyComplex)other).imaginary);
        throw new IllegalArgumentException("Unsupported type for subtraction with MyComplex");
    }

    @Override
    public Value multiply(Value other) {
        if (other instanceof MyNumber) {
            double n = ((MyNumber)other).getValue();
            return new MyComplex(this.real * n, this.imaginary * n);
        }
        if (other instanceof MyComplex) {
            MyComplex c = (MyComplex)other;
            return new MyComplex(this.real * c.real - this.imaginary * c.imaginary, this.real * c.imaginary + this.imaginary * c.real);
        }
        throw new IllegalArgumentException("Unsupported type for multiplication with MyComplex");
    }

    @Override
    public Value divide(Value other) {
        if (other instanceof MyNumber) {
            double n = ((MyNumber)other).getValue();
            if (n == 0) throw new ArithmeticException("Division by zero in MyComplex");
            return new MyComplex(this.real / n, this.imaginary / n);
        }
        if (other instanceof MyComplex) {
            MyComplex c = (MyComplex)other;
            double denominator = c.real * c.real + c.imaginary * c.imaginary;
            if (denominator == 0) throw new ArithmeticException("Division by zero in MyComplex");
            return new MyComplex(
                (this.real * c.real + this.imaginary * c.imaginary) / denominator,
                (this.imaginary * c.real - this.real * c.imaginary) / denominator
            );
        }
        throw new IllegalArgumentException("Unsupported type for division with MyComplex");
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
