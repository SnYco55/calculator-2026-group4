package calculator;

/**
 * Manages angle mode (degrees or radians) for trigonometric operations.
 * Follows a singleton design pattern with static methods for global access.
 * Default mode is radians.
 */
public class AngleMode {
    /**
     * Enumeration for supported angle modes.
     */
    public enum Mode {
        /** Degrees mode (360 degrees = full circle) */
        DEG,
        /** Radians mode (2π radians = full circle) */
        RAD
    }

    /**
     * Private constructor to prevent instantiation (singleton pattern).
     */
    private AngleMode(){}

    private static Mode mode = Mode.RAD;

    /**
     * Sets the global angle mode for trigonometric operations.
     *
     * @param m the angle mode to use
     */
    public static void setMode(Mode m) {
        mode = m;
    }

    /**
     * Gets the current angle mode.
     *
     * @return the current angle mode
     */
    public static Mode getMode() {return mode;}
}
