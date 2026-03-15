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
    public abstract void visit(MyNumber n);

    public abstract void visit(Operation o);

    public abstract void visit(MyReal r);

    public abstract void visit(MyRational r);

    public abstract void visit(MyComplex c);
}
