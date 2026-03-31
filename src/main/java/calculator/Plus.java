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
      if (l instanceof MyRational leftrat){
          if (r instanceof MyRational rightrat){
              return op(leftrat, rightrat);
          }
          else if (r instanceof MyNumber number){
              return op(leftrat, number);
          }
      }else if (l instanceof MyNumber num && r instanceof MyRational rat){
          return op(num, rat);
      }

      MyComplex left = l.toComplex();
      MyComplex right = r.toComplex();

      return super.format(new MyComplex(left.getReal().add(right.getReal()), left.getImaginary().add(right.getImaginary())));
  }

  //Returns a rationnal but if x/1 return integer
  public Value op(MyRational l, MyRational r) {
      int pp = l.ppcm(r);
      int numeratorL = l.getNumerator()*(pp/l.getDenominator());
      int numeratorR = r.getNumerator()*(pp/r.getDenominator());

      // If this is an integer we return the result as such
      if (pp == 1){
          return new MyNumber(numeratorL+numeratorR);
      }

      return new MyRational(numeratorL+numeratorR, pp);
  }

  public Value op(MyRational l, MyNumber r) {
      return op(l, new MyRational(r.getValue(), 1));
  }

  public Value op(MyNumber l, MyRational r) {
      return  op(r, l);
  }
}
