package calculator;

import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Allows to manipulate the precision for real numbers representation.
 * Follows a singleton design pattern.
 */
public class Precision {
    private static MathContext mathContext = new MathContext(2, RoundingMode.HALF_EVEN);
    private static int decimalPlaces = 2;

    private Precision(){}

    /**
     * Sets precision as number of decimal places (digits after decimal point).
     * @param precision number of decimal places (0-10)
     */
    public static void setPrecision(int precision){
        int p = Math.max(0, Math.min(10, precision));
        decimalPlaces = p;
        mathContext = new MathContext(Math.max(p + 5, 7), RoundingMode.HALF_EVEN);
    }

    public static void setPrecision(MathContext mc){
        mathContext = mc;
    }

    public static MathContext getMathContext(){
        return mathContext;
    }

    /**
     * Gets the number of decimal places (digits after decimal point).
     * This is what you should use with BigDecimal.setScale().
     * @return number of decimal places
     */
    public static int getDecimalPlaces(){
        return decimalPlaces;
    }
}
