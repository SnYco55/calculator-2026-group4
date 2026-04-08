package calculator;

import java.math.BigDecimal;
import java.util.List;

public class Scientific extends Operation {
    public Scientific(List<Expression> elist) {
        this(elist, null);
    }

    public Scientific(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist,n);
    }

    @Override
    public Value op(Value l, Value r) {
        String base = l.toString();
        String power = r.toString();

        return new MyReal(new BigDecimal(base + "E" +  power));
    }

    @Override
    public Value opRat(MyRational left, MyRational right) {
        return null;
    }

    @Override
    public Value opRatNum(MyRational left, MyNumber right) {
        return null;
    }

    @Override
    public Value opNumRat(MyNumber left, MyRational right) {
        return null;
    }
}
