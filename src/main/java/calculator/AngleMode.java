package calculator;

public class AngleMode {
    public enum Mode {
        DEG,
        RAD
    }

    private static Mode mode = Mode.RAD;

    public static void setMode(Mode m) {
        mode = m;
    }

    public static Mode getMode() {return mode;}
}
