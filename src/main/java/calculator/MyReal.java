package calculator;

import visitor.Visitor;

public class MyReal implements Expression, Value {
    private final double value;

    public MyReal(double v) {
        this.value = v;
    }

    public double getValue() {
        return value;
    }

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
        return new MyReal(-this.value);
    }

    @Override
    public Value invert() {
        return new MyReal(1.0 / this.value);
    }

    @Override
    public Value add(MyNumber other) {
        return new MyReal(this.value + other.getValue());
    }

    @Override
    public Value add(MyRational other) {
        return new MyReal(this.value + (double)other.getNumerator() / other.getDenominator());
    }

    @Override
    public Value add(MyReal other) {
        return new MyReal(this.value + other.getValue());
    }

    @Override
    public Value add(MyComplex other) {
        return new MyComplex(this.value + other.getReal(), other.getImaginary());
    }

    @Override
    public Value multiply(MyNumber other) {
        return new MyReal(this.value * other.getValue());
    }

    @Override
    public Value multiply(MyRational other) {
        return new MyReal(this.value * ((double)other.getNumerator() / other.getDenominator()));
    }

    @Override
    public Value multiply(MyReal other) {
        return new MyReal(this.value * other.getValue());
    }

    @Override
    public Value multiply(MyComplex other) {
        return new MyComplex(this.value * other.getReal(), this.value * other.getImaginary());
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof MyReal)) return false;
        return Double.compare(this.value, ((MyReal)o).value) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }
}
