package calculator;

import visitor.Visitor;

/**
 * MyNumber is a concrete class that represents arithmetic numbers,
 * which are a special kind of Expressions, just like operations are.
 *
 * @see Expression
 * @see Operation
 */
public class MyNumber implements Expression, Value
{
  private final int value;

    /** getter method to obtain the value contained in the object
     *
     * @return The integer number contained in the object
     */
  public Integer getValue() { return value; }

    /**
     * Constructor method
     *
     * @param v The integer value to be contained in the object
     */
    public /*constructor*/ MyNumber(int v) {
	  value=v;
	  }

    /**
     * accept method to implement the visitor design pattern to traverse arithmetic expressions.
     * Each number will pass itself to the visitor object to get processed by the visitor.
     *
     * @param v	The visitor object
     */
  public void accept(Visitor v) {
      v.visit(this);
  }

    @Override
    public Value add(Value other) {
        return other.add(this);
    }

    @Override
    public Value subtract(Value other) {
        return this.add(other.negate());
    }

    @Override
    public Value multiply(Value other) {
        return other.multiply(this);
    }

    @Override
    public Value divide(Value other) {
        if (other instanceof MyNumber) {
            if (((MyNumber)other).getValue() == 0) throw new ArithmeticException("Division by zero");
            return new MyNumber(this.value / ((MyNumber)other).getValue());
        }
        return this.multiply(other.invert());
    }

    @Override
    public Value negate() {
        return new MyNumber(-this.value);
    }

    @Override
    public Value invert() {
        if (this.value == 0) throw new ArithmeticException("Division by zero");
        return new MyRational(1, this.value);
    }

    @Override
    public Value add(MyNumber other) {
        return new MyNumber(this.value + other.getValue());
    }

    @Override
    public Value add(MyRational other) {
        return new MyRational(this.value * other.getDenominator() + other.getNumerator(), other.getDenominator());
    }

    @Override
    public Value add(MyReal other) {
        return new MyReal(this.value + other.getValue());
    }

    @Override
    public Value add(MyComplex other) {
        return new MyComplex(this.value + other.getReal(), other.getImaginary());
    }

    @Override
    public Value multiply(MyNumber other) {
        return new MyNumber(this.value * other.getValue());
    }

    @Override
    public Value multiply(MyRational other) {
        return new MyRational(this.value * other.getNumerator(), other.getDenominator());
    }

    @Override
    public Value multiply(MyReal other) {
        return new MyReal(this.value * other.getValue());
    }

    @Override
    public Value multiply(MyComplex other) {
        return new MyComplex(this.value * other.getReal(), this.value * other.getImaginary());
    }




  /** Two MyNumber expressions are equal if the values they contain are equal
   *
   * @param o The object to compare to
   * @return  A boolean representing the result of the equality test
   */
  @Override
  public boolean equals(Object o) {
      // No object should be equal to null (not including this check can result in an exception if a MyNumber is tested against null)
      if (o == null) return false;

      // If the object is compared to itself then return true
      if (o == this) {
          return true;
      }

      // If the object is of another type then return false
      if (!(o instanceof MyNumber)) {
            return false;
      }
      return this.value == ((MyNumber)o).value;
      // Used == since the contained value is a primitive value
      // If it had been a Java object, .equals() would be needed
  }

    /** The method hashCode needs to be overridden it the equals method is overridden;
     * 	otherwise there may be problems when you use your object in hashed collections
     * 	such as HashMap, HashSet, LinkedHashSet.
     *
     * @return	The result of computing the hash.
     */
  @Override
  public int hashCode() {
		return value;
  }

}
