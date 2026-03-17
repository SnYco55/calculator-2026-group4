package api;

import calculator.Expression;
import org.springframework.web.bind.annotation.*;
import services.CalculatorService;

/**
 * REST controller exposing calculator endpoints.
 */
@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    /**
     * Parses a mathematical expression.
     *
     * @param input the expression provided by the user
     * @return the parsed expression representation
     */
    @GetMapping("/parse")
    public Expression parseExpression(@RequestParam("input") String input) {
        return calculatorService.parseExpression(input);
    }
}