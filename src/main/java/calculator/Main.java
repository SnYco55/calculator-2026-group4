package calculator;

import java.util.Scanner;
import parser.Parser;
import parser.ExpressionParser;

/**
 * A very simple calculator in Java
 * University of Mons - UMONS
 * Software Engineering Lab
 * Faculty of Sciences
 *
 * @author tommens
 */
public class Main {

	/**
	 * This is the main method of the application.
	 * It provides an interactive Read-Eval-Print Loop (REPL) to evaluate arithmetic expressions.
	 * <p>
	 * To run the CLI from a terminal, navigate to the project root and use the following Maven command:
	 * <pre>
	 * mvn compile exec:java -Dexec.mainClass="calculator.Main"
	 * </pre>
	 * </p>
	 *
	 * @param args	Command-line parameters are not used in this version
	 */
	@SuppressWarnings("java:S106") // CLI requires System.out
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ExpressionParser parser = new Parser();
		Calculator calculator = new Calculator();

		System.out.println("Calculator REPL. Type 'exit' to quit.");
		boolean running = true;
		while (running) {
			System.out.print("> ");
			if (!scanner.hasNextLine()) {
				running = false;
			} else {
				String input = scanner.nextLine().trim();
				
				if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit")) {
					running = false;
				} else if (input.equalsIgnoreCase("help")) {
					System.out.println("""
						Calculator CLI Help:
						--------------------
						Evaluate Expressions: Enter any arithmetic expression (e.g., '2 + 2', '(4 * 5) / 2').
						Supported Numbers: Integers, Reals (e.g., '1.5'), Rationals (e.g., '1/2'), Complex (e.g., '1+2i').
						Commands:
						  help - Display this help message.
						  exit - Quit the calculator.""");
				} else if (!input.isEmpty()) {
					try {
						Expression expr = parser.parse(input);
						Value result = calculator.eval(expr);
						System.out.println(result);
					} catch (IllegalConstruction e) {
						System.out.println("Invalid expression structure: " + e.getMessage());
					} catch (RuntimeException e) {
						System.out.println("Error evaluating expression: " + e.getMessage());
					}
				}
			}
		}
		scanner.close();
 	}
}
