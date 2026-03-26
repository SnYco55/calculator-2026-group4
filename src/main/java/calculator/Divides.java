package calculator;

import java.util.List;

/** This class represents the arithmetic division operation "/".
 * The class extends an abstract superclass Operation.
 * Other subclasses of Operation represent other arithmetic operations.
 * @see Operation
 * @see Minus
 * @see Times
 * @see Plus
 */
public final class Divides extends Operation
{

  /**
   * Class constructor specifying a number of Expressions to divide.
   *
   * @param elist The list of Expressions to divide
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Divides(List<Expression>,Notation)
   */
  public /*constructor*/ Divides(List<Expression> elist) throws IllegalConstruction {
	this(elist, null);
  }

    /**
     * Class constructor specifying a number of Expressions to divide,
     * as well as the notation used to represent the operation.
     *
     * @param elist The list of Expressions to divide
     * @param n The Notation to be used to represent the operation
     * @throws IllegalConstruction  If an empty list of expressions if passed as parameter
     * @see #Divides(List<Expression>)
     * @see Operation#Operation(List<Expression>,Notation)
     */
  public Divides(List<Expression> elist, Notation n) throws IllegalConstruction {
	super(elist,n);
	symbol = "/";
	neutral = 1;
  }

    /**
     * The actual computation of the (binary) arithmetic division of two integers
     * @param l The first integer
     * @param r The second integer that should divide the first
     * @return The integer that is the result of the division
     */
  public Value op(Value l, Value r) {
      if (l instanceof MyReal && r instanceof MyReal) {
          return op((MyReal) l, (MyReal) r);
      }

      MyComplex left = l.toComplex();
      MyComplex right = r.toComplex();

      right = (MyComplex) right.invert();

      return super.format(new MyComplex((left.getReal()*right.getReal())-(left.getImaginary()*right.getImaginary()), (left.getReal()*right.getImaginary())+(left.getImaginary()*right.getReal())));
  }

  public Value op(MyReal l, MyReal r) {
      double lval = l.getValue();
      if (r.getValue() == 0.0) {
          if (lval == 0.0) return new MyReal(Double.NaN);
          if (lval == 1.0) return new MyReal(Double.POSITIVE_INFINITY);
          if (lval == -1.0) return new MyReal(Double.NEGATIVE_INFINITY);
          throw new ArithmeticException();
      }else{
          MyComplex left = l.toComplex();
          MyComplex right = r.toComplex();
          return op(left, right);
      }
  }

  public MyRational op(MyRational l, MyRational r) {
      return new MyRational(l.getNumerator()*r.getDenominator(), l.getDenominator()*r.getNumerator());
  }
}


