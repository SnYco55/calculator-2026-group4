package dto;

public class ParserRequest {
    private String input;
    private String angleMode; // "RAD" ou "DEG"
    private Integer precision;

    public ParserRequest() {}

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getAngleMode() {
        return angleMode;
    }

    public void setAngleMode(String angleMode) {
        this.angleMode = angleMode;
    }

    public Integer getPrecision() {
        return precision;
    }

    public void setPrecision(Integer precision) {
        this.precision = precision;
    }
}