package visitor;

import calculator.Expression;
import calculator.MyNumber;
import calculator.Operation;
import calculator.MyReal;
import calculator.MyRational;
import calculator.MyComplex;

/**
 * NbsCounter is a concrete visitor that serves to
 * count the number of values recursively contained in an arithmetic expression.
 */
public class NbsCounter extends Visitor {

    private int computedValue;

    public int getResult() {
        return computedValue;
    }

    public void visit(MyNumber n) {
        computedValue = 1;
    }

    public void visit(MyReal r) {
        computedValue = 1;
    }

    public void visit(MyRational r) {
        computedValue = 1;
    }

    public void visit(MyComplex c) {
        computedValue = 1;
    }

    public void visit(Operation o) {
        int sum = 0;
        for (Expression a : o.args) {
            a.accept(this);
            sum += computedValue;
        }
        computedValue = sum;
    }
}
