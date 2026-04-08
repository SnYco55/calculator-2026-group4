package calculator;

import java.math.BigDecimal;
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
      Value result = dispatch(l, r);

      if (result != null) {return result;}

      if (l instanceof MyNumber numl && r instanceof MyNumber numr) {
          MyRational res =  new MyRational(numl.getValue(), numr.getValue());
          if (res.getDenominator() == 1){
              return new MyNumber(res.getNumerator());
          }else{
              return res;
          }
      }

      if (l instanceof MyReal leftreal && r instanceof MyReal rightreal) {
          return opReal(leftreal, rightreal);
      }

      return compOpLogic(l.toComplex(), r.toComplex());
  }

  public Value opReal(MyReal l, MyReal r) {
      BigDecimal lval = l.getValue();
      if (r.getValue().compareTo(BigDecimal.ZERO) == 0) {
          if (lval.compareTo(BigDecimal.ZERO) == 0) return new MyReal(new BigDecimal(0), MyReal.State.NAN);
          if (lval.compareTo(BigDecimal.ONE) == 0) return new MyReal(new BigDecimal(0), MyReal.State.POSITIVE_INFINITY);
          if (lval.compareTo(BigDecimal.valueOf(-1)) == 0) return new MyReal(new BigDecimal(0), MyReal.State.NEGATIVE_INFINITY);
          throw new ArithmeticException();
      }else{
          return compOpLogic(l.toComplex(), r.toComplex());
      }
  }

  public Value compOpLogic(MyComplex left, MyComplex right) {
      right = (MyComplex) right.invert();
      return Operation.format(new MyComplex(left.getReal().multiply(right.getReal()).subtract(left.getImaginary().multiply(right.getImaginary())), left.getReal().multiply(right.getImaginary()).add(left.getImaginary().multiply(right.getReal()))));
  }

  public Value opRat(MyRational l, MyRational r) {
      MyRational result = new MyRational(l.getNumerator()*r.getDenominator(), l.getDenominator()*r.getNumerator());

      if (result.getDenominator() == 1){
          return new MyNumber(result.getNumerator());
      }
      return result;
  }

    public Value opRatNum(MyRational l, MyNumber r) {
        return op(l, new MyRational(r.getValue(), 1));
    }

    public Value opNumRat(MyNumber l, MyRational r) {
        return  opRat(new MyRational(l.getValue(), 1), r);
    }
}


