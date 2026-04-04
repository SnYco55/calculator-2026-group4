package visitor;

import calculator.Operation;

/**
 * NbsCounter is a concrete visitor that serves to
 * count the number of values recursively contained in an arithmetic expression.
 */
public class NbsCounter extends Visitor {

    private int computedValue;

    public int getResult() {
        return computedValue;
    }

    @Override
    public void visit(calculator.Value v) {
        computedValue = 1;
    }

    public void visit(Operation o) {
        computedValue = o.args.stream().mapToInt(a -> {
            a.accept(this);
            return computedValue;
        }).sum();
    }
}
