package visitor;

import calculator.MyNumber;
import calculator.Operation;
import calculator.MyReal;
import calculator.MyRational;
import calculator.MyComplex;

/**
 * Visitor design pattern
 */
public abstract class Visitor {

    /**
     * The Visitor can traverse a number (a subtype of Expression)
     *
     * @param n The number being visited
     */
    /**
     * Fallback visit method for Value objects
     */
    public void visit(calculator.Value v) {}

    public abstract void visit(Operation o);

    public void visit(MyNumber n) { visit((calculator.Value) n); }
    public void visit(MyReal r) { visit((calculator.Value) r); }
    public void visit(MyRational r) { visit((calculator.Value) r); }
    public void visit(MyComplex c) { visit((calculator.Value) c); }
}
