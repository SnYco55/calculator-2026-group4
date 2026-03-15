package visitor;

import calculator.Expression;
import calculator.MyNumber;
import calculator.Operation;
import calculator.Notation;
import calculator.MyReal;
import calculator.MyRational;
import calculator.MyComplex;

import java.util.ArrayList;

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

    public void visit(MyNumber n) {
        computedValue = Integer.toString(n.getValue());
    }

    public void visit(MyReal r) {
        computedValue = r.toString();
    }

    public void visit(MyRational r) {
        computedValue = r.toString();
    }

    public void visit(MyComplex c) {
        computedValue = c.toString();
    }

    public void visit(Operation o) {
        ArrayList<String> s = new ArrayList<>();
        for (Expression a : o.args) {
            a.accept(this);
            s.add(computedValue);
        }

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
