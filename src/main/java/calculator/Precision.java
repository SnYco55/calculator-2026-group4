package calculator;

import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Manages precision for real number representation using BigDecimal.
 * Follows a singleton design pattern with static methods for global access.
 * Controls both decimal places and MathContext for arithmetic operations.
 */
public class Precision {
    private static MathContext mathContext = new MathContext(2, RoundingMode.HALF_EVEN);
    private static int decimalPlaces = 2;

    /**
     * Private constructor to prevent instantiation (singleton pattern).
     */
    private Precision(){}

    /**
     * Sets precision as number of decimal places (digits after decimal point).
     * Valid range is 0-10; values outside this range are clamped.
     *
     * @param precision number of decimal places (0-10)
     */
    public static void setPrecision(int precision){
        int p = Math.max(0, Math.min(10, precision));
        decimalPlaces = p;
        mathContext = new MathContext(Math.max(p + 5, 7), RoundingMode.HALF_EVEN);
    }

    /**
     * Sets a custom MathContext for arithmetic operations.
     *
     * @param mc the MathContext to use
     */
    public static void setPrecision(MathContext mc){
        mathContext = mc;
    }

    /**
     * Gets the current MathContext used for arithmetic operations.
     *
     * @return the current MathContext
     */
    public static MathContext getMathContext(){
        return mathContext;
    }

    /**
     * Gets the number of decimal places (digits after decimal point).
     * Use this value with BigDecimal.divide() for consistent precision.
     *
     * @return number of decimal places
     */
    public static int getDecimalPlaces(){
        return decimalPlaces;
    }
}
