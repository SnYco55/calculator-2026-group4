package visitor;

import calculator.Expression;
import calculator.MyNumber;
import calculator.Operation;
import calculator.Value;
import calculator.MyReal;
import calculator.MyRational;
import calculator.MyComplex;

import java.util.ArrayList;

/** Evaluation is a concrete visitor that serves to
 * compute and evaluate the results of arithmetic expressions.
 */
public class Evaluator extends Visitor {

    /**
     * Default constructor of the class. Does not initialise anything.
     */
    public Evaluator() {}

    /** The result of the evaluation will be stored in this private variable */
    private Value computedValue;

    /** getter method to obtain the result of the evaluation
     *
     * @return a Value object containing the result of the evaluation
     */
    public Value getResult() { return computedValue; }

    /** Use the visitor design pattern to visit a number.
     *
     * @param n The number being visited
     */
    public void visit(MyNumber n) {
        computedValue = n;
    }

    public void visit(MyReal r) {
        computedValue = r;
    }

    public void visit(MyRational r) {
        computedValue = r;
    }

    public void visit(MyComplex c) {
        computedValue = c;
    }

    /** Use the visitor design pattern to visit an operation
     *
     * @param o The operation being visited
     */
    public void visit(Operation o) {
        ArrayList<Value> evaluatedArgs = new ArrayList<>();
        //first loop to recursively evaluate each subexpression
        for(Expression a:o.args) {
            a.accept(this);
            evaluatedArgs.add(computedValue);
        }
        //second loop to accumulate all the evaluated subresults
        Value temp = evaluatedArgs.get(0);
        int max = evaluatedArgs.size();

        for(int counter=1; counter<max; counter++) {
            try {
                temp = o.op(temp,evaluatedArgs.get(counter));
            }
            catch (ArithmeticException e) {
                throw e;
            }

        }
        // store the accumulated result
        computedValue = temp;
    }

}
