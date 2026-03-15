package visitor;

import calculator.Expression;
import calculator.MyNumber;
import calculator.Operation;
import calculator.MyReal;
import calculator.MyRational;
import calculator.MyComplex;

import java.util.ArrayList;
import java.util.Collections;

/**
 * DepthCounter is a concrete visitor that serves to
 * count the depth of nested expressions in an arithmetic expression.
 */
public class DepthCounter extends Visitor {

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
        ArrayList<Integer> depths = new ArrayList<>();
        for (Expression a : o.args) {
            a.accept(this);
            depths.add(computedValue);
        }
        
        int maxDepth = depths.isEmpty() ? 0 : Collections.max(depths);
        computedValue = 1 + maxDepth;
    }
}
