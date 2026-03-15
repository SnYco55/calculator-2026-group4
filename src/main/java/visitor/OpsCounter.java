package visitor;

import calculator.Expression;
import calculator.MyNumber;
import calculator.Operation;
import calculator.MyReal;
import calculator.MyRational;
import calculator.MyComplex;

/**
 * OpsCounter is a concrete visitor that serves to
 * count the number of operations recursively contained in an arithmetic expression.
 */
public class OpsCounter extends Visitor {

    private int computedValue;

    public int getResult() {
        return computedValue;
    }

    public void visit(MyNumber n) {
        computedValue = 0;
    }

    public void visit(MyReal r) {
        computedValue = 0;
    }

    public void visit(MyRational r) {
        computedValue = 0;
    }

    public void visit(MyComplex c) {
        computedValue = 0;
    }

    public void visit(Operation o) {
        int sum = 0;
        for (Expression a : o.args) {
            a.accept(this);
            sum += computedValue;
        }
        computedValue = 1 + sum;
    }
}
