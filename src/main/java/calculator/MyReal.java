package calculator;

import visitor.Visitor;

public class MyReal  implements Expression, Value{
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

    public MyComplex toComplex() {
        return new MyComplex(this.value, 0);
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }

}
