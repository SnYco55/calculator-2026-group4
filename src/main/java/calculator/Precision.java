package calculator;

import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Allows to manipulate the precision for real numbers representation.
 * Follows a singleton design pattern.
 */
public class Precision {
    private static MathContext mathContext = new MathContext(2, RoundingMode.HALF_EVEN);

    private Precision(){}

    public static void setPrecision(int precision){
        mathContext = new MathContext(precision, RoundingMode.HALF_EVEN);
    }

    public static MathContext getMathContext(){
        return mathContext;
    }
}
