package calculator;

import visitor.Evaluator;
import visitor.DepthCounter;
import visitor.OpsCounter;
import visitor.NbsCounter;
import visitor.Printer;

/**
 * This class represents the core logic of a Calculator.
 * It can be used to print and evaluate artihmetic expressions.
 *
 * @author tommens
 */
public class Calculator {

    /**
     * Default constructor of the class.
     * Does nothing since the class does not have any variables that need to be initialised.
     */
    public Calculator() {}

    /*
     For the moment the calculator only contains a print method and an eval method
     It would be useful to complete this with a read method, so that we would be able
     to implement a full REPL cycle (Read-Eval-Print loop) such as in Scheme, Python, R and other languages.
     To do so would require to implement a method with the following signature, converting an input string
     into an arithmetic expression:
     public Expression read(String s)
    */

    /**
     * Prints an arithmetic expression provided as input parameter.
     * @param e the arithmetic Expression to be printed
     * @see #printExpressionDetails(Expression) 
     */
    public void print(Expression e) {
        Printer p = new Printer();
        e.accept(p);
        System.out.println("The result of evaluating expression " + p.getResult());
        try {
            Value result = eval(e);
            System.out.println("is: " + result + ".");
        } catch (ArithmeticException ex) {
            System.out.println("Error: Division by zero");
        }
        System.out.println();
    }

    /**
     * Prints verbose details of an arithmetic expression provided as input parameter.
     * @param e the arithmetic Expression to be printed
     * @see #print(Expression)
     */
    public void printExpressionDetails(Expression e) {
        print(e);
        DepthCounter dc = new DepthCounter(); e.accept(dc);
        System.out.print("It contains " + dc.getResult() + " levels of nested expressions, ");
        OpsCounter oc = new OpsCounter(); e.accept(oc);
        System.out.print(oc.getResult() + " operations");
        NbsCounter nc = new NbsCounter(); e.accept(nc);
        System.out.println(" and " + nc.getResult() + " numbers.");
        System.out.println();
    }

    /**
     * Evaluates an arithmetic expression and returns its result
     * @param e the arithmetic Expression to be evaluated
     * @return The result of the evaluation
     */
    public Value eval(Expression e) {
        // create a new visitor to evaluate expressions
        Evaluator v = new Evaluator();
        // and ask the expression to accept this visitor to start the evaluation process
        try{
            e.accept(v);
        }catch (ArithmeticException ex){
            throw ex;
        }

        // and return the result of the evaluation at the end of the process
        return v.getResult();
    }

    /*
     We could also have other methods, e.g. to verify whether an expression is syntactically correct
     public Boolean validate(Expression e)
     or to simplify some expression
     public Expression simplify(Expression e)
    */
}
