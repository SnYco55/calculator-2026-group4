package api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication(scanBasePackages = {
        "api",
        "parser",
        "visitor",
        "services",
        "calculator"
})
public class CalculatorApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalculatorApiApplication.class, args);
    }

}