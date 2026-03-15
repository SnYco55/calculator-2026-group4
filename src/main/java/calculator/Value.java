package calculator;

/**
 * Value is an interface representing a possible result of an arithmetic evaluation.
 * It provides operations that are overloaded by specific domains like Integer, Rational, Real, Complex.
 */
public interface Value {

    Value add(Value other);
    
    Value subtract(Value other);
    
    Value multiply(Value other);
    
    Value divide(Value other);
    
}
