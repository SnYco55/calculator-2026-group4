package calculator;

/**
 * Value is an interface representing a possible result of an arithmetic evaluation.
 * It provides operations that are overloaded by specific domains like Integer, Rational, Real, Complex.
 */
public interface Value {
    public MyComplex toComplex();
    public boolean equals(Object obj);
}
