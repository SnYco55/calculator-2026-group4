package visitor;

import calculator.Operation;

/**
 * OpsCounter is a concrete visitor that serves to
 * count the number of operations recursively contained in an arithmetic expression.
 */
public class OpsCounter extends Visitor {

    private int computedValue;

    public int getResult() {
        return computedValue;
    }

    @Override
    public void visit(calculator.Value v) {
        computedValue = 0;
    }

    public void visit(Operation o) {
        int sum = o.args.stream().mapToInt(a -> {
            a.accept(this);
            return computedValue;
        }).sum();
        computedValue = 1 + sum;
    }
}
