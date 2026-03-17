package calculator;

/**
 * Value is an interface representing a possible result of an arithmetic evaluation.
 * It provides operations that are overloaded by specific domains like Integer, Rational, Real, Complex.
 */
public interface Value {

    Value add(Value other);
    Value add(MyNumber other);
    Value add(MyRational other);
    Value add(MyReal other);
    Value add(MyComplex other);
    
    Value subtract(Value other);
    
    Value multiply(Value other);
    Value multiply(MyNumber other);
    Value multiply(MyRational other);
    Value multiply(MyReal other);
    Value multiply(MyComplex other);
    
    Value divide(Value other);
    
    Value negate();
    Value invert();
    
}
