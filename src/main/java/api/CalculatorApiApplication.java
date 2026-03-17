package api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Entry point of the Calculator API Spring Boot application.
 */
@SpringBootApplication(scanBasePackages = {
        "api",
        "parser",
        "visitor",
        "services",
        "calculator"
})
public class CalculatorApiApplication {
    /**
     * Starts the Spring Boot API application.
     *
     * @param args application arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(CalculatorApiApplication.class, args);
    }

}