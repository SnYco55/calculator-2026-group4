package visitor;

import calculator.Operation;
import calculator.Notation;

/**
 * Printer is a concrete visitor that serves to
 * convert arithmetic expressions into formatted Strings.
 */
public class Printer extends Visitor {

    private String computedValue;
    private Notation notation;

    public Printer(Notation n) {
        this.notation = n;
    }

    public Printer() {
        this.notation = Notation.INFIX;
    }

    public String getResult() {
        return computedValue;
    }

    @Override
    public void visit(calculator.Value v) {
        computedValue = v.toString();
    }

    public void visit(Operation o) {
        java.util.List<String> s = o.args.stream().map(a -> {
            a.accept(this);
            return computedValue;
        }).toList();

        String symbol = o.getSymbol();
        
        switch (notation) {
            case INFIX:
                computedValue = "( " + String.join(" " + symbol + " ", s) + " )";
                break;
            case PREFIX:
                computedValue = symbol + " (" + String.join(", ", s) + ")";
                break;
            case POSTFIX:
                computedValue = "(" + String.join(", ", s) + ") " + symbol;
                break;
        }
    }
}
