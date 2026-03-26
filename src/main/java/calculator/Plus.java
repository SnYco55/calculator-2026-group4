package calculator;

import java.util.List;

/** This class represents the arithmetic sum operation "+".
 * The class extends an abstract superclass Operation.
 * Other subclasses of Operation represent other arithmetic operations.
 * @see Operation
 * @see Minus
 * @see Times
 * @see Divides
 */
public final class Plus extends Operation
 {

  /**
   * Class constructor specifying a number of Expressions to add.
   *
   * @param elist The list of Expressions to add
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Plus(List<Expression>,Notation)
   */
  public /*constructor*/ Plus(List<Expression> elist) throws IllegalConstruction {
	this(elist, null);
  }

  /**
   * Class constructor specifying a number of Expressions to add,
   * as well as the Notation used to represent the operation.
   *
   * @param elist The list of Expressions to add
   * @param n The Notation to be used to represent the operation
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Plus(List<Expression>)
   * @see Operation#Operation(List<Expression>,Notation)
   */
  public Plus(List<Expression> elist, Notation n) throws IllegalConstruction {
  	super(elist,n);
  	symbol = "+";
  	neutral = 0;
  }

  /**
   * The actual computation of the (binary) arithmetic addition of two integers
   * @param l The first integer
   * @param r The second integer that should be added to the first
   * @return The integer that is the result of the addition
   */
  public Value op(Value l, Value r) {
      MyComplex left = l.toComplex();
      MyComplex right = r.toComplex();

      return super.format(new MyComplex(left.getReal()+right.getReal(), left.getImaginary()+right.getImaginary()));
  }

  public MyRational op(MyRational l, MyRational r) {
      int pp = l.ppcm(r);
      int numerator_l = l.getNumerator()*(pp/l.getDenominator());
      int numerator_r = r.getNumerator()*(pp/r.getDenominator());
      return new MyRational(numerator_l+numerator_r, pp);
  }
}
