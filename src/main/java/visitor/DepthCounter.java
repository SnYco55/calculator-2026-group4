package visitor;

import calculator.Operation;

/**
 * DepthCounter is a concrete visitor that serves to
 * count the depth of nested expressions in an arithmetic expression.
 */
public class DepthCounter extends Visitor {

    private int computedValue;

    public int getResult() {
        return computedValue;
    }

    @Override
    public void visit(calculator.Value v) {
        computedValue = 0;
    }

    public void visit(Operation o) {
        int maxDepth = o.args.stream().mapToInt(a -> {
            a.accept(this);
            return computedValue;
        }).max().orElse(0);
        
        computedValue = 1 + maxDepth;
    }
}
