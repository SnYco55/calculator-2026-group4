package calculator;

import java.util.List;

public class Pow extends Operation{
    public /*constructor*/ Pow(List<Expression> elist) throws IllegalConstruction {
        this(elist, null);
    }

    public Pow(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist,n);
    }

    public Value op(Value l, Value r) {
        Value result = dispatch(l, r);

        if (result != null) {return result;}

        MyComplex left = l.toComplex();
        MyComplex right = r.toComplex();

        return Operation.format(new MyReal(left.getReal().pow(right.getReal().intValue())).toComplex());
    }

    @Override
    public Value opRat(MyRational left, MyRational right) {
        return op(new MyReal(left.getValue()), new MyReal(right.getValue()));
    }

    @Override
    public Value opRatNum(MyRational left, MyNumber right) {
        return op(new MyReal(left.getValue()), right);
    }

    @Override
    public Value opNumRat(MyNumber left, MyRational right) {
        return op(left, new MyReal(right.getValue()));
    }
}
