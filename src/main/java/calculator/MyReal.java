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
        if (other instanceof MyNumber) return new MyReal(this.value + ((MyNumber)other).getValue());
        if (other instanceof MyReal) return new MyReal(this.value + ((MyReal)other).getValue());
        throw new IllegalArgumentException("Unsupported type for addition with MyReal");
    }

    @Override
    public Value subtract(Value other) {
        if (other instanceof MyNumber) return new MyReal(this.value - ((MyNumber)other).getValue());
        if (other instanceof MyReal) return new MyReal(this.value - ((MyReal)other).getValue());
        throw new IllegalArgumentException("Unsupported type for subtraction with MyReal");
    }

    @Override
    public Value multiply(Value other) {
        if (other instanceof MyNumber) return new MyReal(this.value * ((MyNumber)other).getValue());
        if (other instanceof MyReal) return new MyReal(this.value * ((MyReal)other).getValue());
        throw new IllegalArgumentException("Unsupported type for multiplication with MyReal");
    }

    @Override
    public Value divide(Value other) {
        double otherVal;
        if (other instanceof MyNumber) {
            otherVal = ((MyNumber)other).getValue();
        } else if (other instanceof MyReal) {
            otherVal = ((MyReal)other).getValue();
        } else {
            throw new IllegalArgumentException("Unsupported type for division with MyReal");
        }
        return new MyReal(this.value / otherVal); // In Java, X.X / 0.0 results in Infinity or NaN, mapping to the requirements natively
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
