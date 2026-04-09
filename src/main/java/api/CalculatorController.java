package api;

import calculator.Calculator;
import dto.ConversionRequest;
import dto.ParserRequest;
import dto.ParserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import services.CalculatorService;

/**
 * REST controller exposing calculator endpoints.
 */
@CrossOrigin(origins = {
        "http://localhost:3000",
        "https://calculator-2026-group4.vercel.app"
})
@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    private Calculator calculator = new Calculator();

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    /**
     * Evaluates a mathematical expression provided in the request body.
     *
     * @param request the DTO containing the raw expression string (e.g., { "input": "3 + 5" })
     * @return the integer result of the expression evaluation
     */
    @PostMapping("/parse")
    public ParserResponse parseExpression(@RequestBody ParserRequest request) {
        String input = request.getInput();
        String angleMode = request.getAngleMode();
        Integer precision = request.getPrecision();

        try {
            String result = calculator.eval(calculatorService.parseExpression(input, angleMode, precision)).toString();
            return new ParserResponse(result);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @PostMapping("/convert-result")
    public ParserResponse convertResult(@RequestBody ConversionRequest request) {
        String value = request.getResult();
        int precision = request.getPrecision() == null ? 2 : request.getPrecision();

        try {
            String converted = calculatorService.convertResult(value, precision);
            return new ParserResponse(converted);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }

}