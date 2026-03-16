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

    private double getDoubleValue(Value v) {
        if (v instanceof MyReal) return ((MyReal)v).getValue();
        if (v instanceof MyRational) return (double)((MyRational)v).getNumerator() / ((MyRational)v).getDenominator();
        if (v instanceof MyNumber) return ((MyNumber)v).getValue();
        throw new IllegalArgumentException("Unsupported type");
    }

    @Override
    public Value add(Value other) {
        if (other instanceof MyComplex) return other.add(this);
        return new MyReal(this.value + getDoubleValue(other));
    }

    @Override
    public Value subtract(Value other) {
        if (other instanceof MyComplex) return new MyComplex(this.value - ((MyComplex)other).getReal(), -((MyComplex)other).getImaginary());
        return new MyReal(this.value - getDoubleValue(other));
    }

    @Override
    public Value multiply(Value other) {
        if (other instanceof MyComplex) return other.multiply(this);
        return new MyReal(this.value * getDoubleValue(other));
    }

    @Override
    public Value divide(Value other) {
        if (other instanceof MyComplex) {
            MyComplex c = (MyComplex)other;
            double denominator = c.getReal() * c.getReal() + c.getImaginary() * c.getImaginary();
            if (denominator == 0) throw new ArithmeticException("Division by zero in MyReal");
            return new MyComplex((this.value * c.getReal()) / denominator, (-this.value * c.getImaginary()) / denominator);
        }
        return new MyReal(this.value / getDoubleValue(other));
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
