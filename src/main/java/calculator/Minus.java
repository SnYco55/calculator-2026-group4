package calculator;

import java.util.List;

/** This class represents the arithmetic operation "-".
 * The class extends an abstract superclass Operation.
 * Other subclasses of Operation represent other arithmetic operations.
 * @see Operation
 * @see Plus
 * @see Times
 * @see Divides
 */
public final class Minus extends Operation
 {

  /**
   * Class constructor specifying a number of Expressions to subtract.
   *
   * @param elist The list of Expressions to subtract
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Minus(List<Expression>,Notation)
   */
  public /*constructor*/ Minus(List<Expression> elist) throws IllegalConstruction {
  	this(elist, null);
  }

  /**
   * Class constructor specifying a number of Expressions to subtract,
   * as well as the Notation used to represent the operation.
   *
   * @param elist The list of Expressions to subtract
   * @param n The Notation to be used to represent the operation
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Minus(List<Expression>)
   * @see Operation#Operation(List<Expression>,Notation)
   */
  public Minus(List<Expression> elist, Notation n) throws IllegalConstruction {
  	super(elist,n);
  	symbol = "-";
  	neutral = 0;
  }

    /**
     * The actual computation of the (binary) arithmetic subtraction of two integers
     * @param l The first integer
     * @param r The second integer that should be subtracted from the first
     * @return The integer that is the result of the subtraction
     */
    public Value op(Value l, Value r) {
        Value res = dispatch(l, r);

        if (res != null){
            return res;
        }

        MyComplex left = l.toComplex();
        MyComplex right = r.toComplex();

        return super.format(new MyComplex(left.getReal().subtract(right.getReal()), left.getImaginary().subtract(right.getImaginary())));
    }

     public Value opRat(MyRational l, MyRational r) {
         int pp = l.ppcm(r);
         int numeratorL = l.getNumerator()*(pp/l.getDenominator());
         int numeratorR = r.getNumerator()*(pp/r.getDenominator());

         if (pp == 1){
             return new MyNumber(numeratorL-numeratorR);
         }

         return new MyRational(numeratorL-numeratorR, pp);
     }

     public Value opRatNum(MyRational l, MyNumber r) {
         return opRat(l, new MyRational(r.getValue(), 1));
     }

     public Value opNumRat(MyNumber l, MyRational r) {
         return  opRat(new MyRational(l.getValue(), 1), r);
     }
}
