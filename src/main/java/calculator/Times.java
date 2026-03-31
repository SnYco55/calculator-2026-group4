package calculator;

import java.util.List;

/** This class represents the arithmetic multiplication operation "*".
 * The class extends an abstract superclass Operation.
 * Other subclasses of Operation represent other arithmetic operations.
 * @see Operation
 * @see Minus
 * @see Plus
 * @see Divides
 */
public final class Times extends Operation
 {
  /**
   * Class constructor specifying a number of Expressions to multiply.
   *
   * @param elist The list of Expressions to multiply
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Times(List<Expression>,Notation)
   */
  public /*constructor*/ Times(List<Expression> elist) throws IllegalConstruction {
  	this(elist, null);
  }

  /**
   * Class constructor specifying a number of Expressions to multiply,
   * as well as the Notation used to represent the operation.
   *
   * @param elist The list of Expressions to multiply
   * @param n The Notation to be used to represent the operation
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Times(List<Expression>)
   * @see Operation#Operation(List<Expression>,Notation)
   */
  public Times(List<Expression> elist, Notation n) throws IllegalConstruction {
  	super(elist,n);
  	symbol = "*";
  	neutral = 1;
  }

  /**
   * The actual computation of the (binary) arithmetic multiplication of two integers
   * @param l The first integer
   * @param r The second integer that should be multiplied with the first
   * @return The integer that is the result of the multiplication
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

      return super.format(new MyComplex(left.getReal().multiply(right.getReal()).subtract(left.getImaginary().multiply(right.getImaginary())), left.getReal().multiply(right.getImaginary()).add(left.getImaginary().multiply(right.getReal()))));
  }

  public Value op(MyRational l, MyRational r) {
      // We call this here to simplify the rationnal
      MyRational result = new MyRational(l.getNumerator()*r.getNumerator(), l.getDenominator()*r.getDenominator());

      if (result.getDenominator() == 1){
          return new MyNumber(result.getNumerator());
      }
      return result;
  }

  public Value op(MyRational l, MyNumber r) {
      return op(l, new MyRational(r.getValue(), 1));
  }

  public Value op(MyNumber l, MyRational r) {
      return  op(r, l);
  }
}
