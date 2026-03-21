package api;

import calculator.Calculator;
import calculator.Expression;
import dto.ParserRequest;
import org.springframework.web.bind.annotation.*;
import services.CalculatorService;

/**
 * REST controller exposing calculator endpoints.
 */
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
    public Integer parseExpression(@RequestBody ParserRequest request) {
        String input = request.getInput();
        return calculator.eval(calculatorService.parseExpression(input));
    }
}