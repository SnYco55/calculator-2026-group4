package calculator;

import visitor.Visitor;

import java.math.BigDecimal;

public class MyReal  implements Expression, Value{
    private final BigDecimal value;
    private final State state;

    public enum State {
        VALID, NAN, POSITIVE_INFINITY, NEGATIVE_INFINITY, UNDEFINED
    }

    public MyReal(BigDecimal v) {
        this.value = v;
        this.state = State.VALID;
    }

    public MyReal(BigDecimal v, State s) {
        this.value = v;
        this.state = s;
    }

    public BigDecimal getValue() {
        return value;
    }

    public State getState() {return state;}
    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public String toString() {
        return this.value.stripTrailingZeros().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof MyReal)) return false;
        return this.value.stripTrailingZeros().equals(((MyReal)o).value.stripTrailingZeros());
    }

    public MyComplex toComplex() {
        return new MyComplex(this.value, new BigDecimal("0"));
    }

    @Override
    public int hashCode() {
        return this.value.stripTrailingZeros().hashCode();
    }

    public MyReal degreesToRadians() {
        return new MyReal(BigDecimal.valueOf(Math.toRadians(this.value.stripTrailingZeros().doubleValue())));
    }

    public MyReal radiansToDegrees() {
        return new MyReal(BigDecimal.valueOf(Math.toDegrees(this.value.stripTrailingZeros().doubleValue())));
    }
}
