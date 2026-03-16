package api;

import calculator.Expression;
import org.springframework.web.bind.annotation.*;
import services.CalculatorService;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/parse")
    public Expression parseExpression(@RequestParam("input") String input) {
        return calculatorService.parseExpression(input);
    }
}