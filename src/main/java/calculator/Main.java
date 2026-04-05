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
	 *
	 * @param args	Command-line parameters are not used in this version
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ExpressionParser parser = new Parser();
		Calculator calculator = new Calculator();

		System.out.println("Calculator REPL. Type 'exit' to quit.");
		while (true) {
			System.out.print("> ");
			if (!scanner.hasNextLine()) break;
			String input = scanner.nextLine().trim();
			
			if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit")) {
				break;
			}
			if (input.equalsIgnoreCase("help")) {
				System.out.println("Calculator CLI Help:");
				System.out.println("--------------------");
				System.out.println("Evaluate Expressions: Enter any arithmetic expression (e.g., '2 + 2', '(4 * 5) / 2').");
				System.out.println("Supported Numbers: Integers, Reals (e.g., '1.5'), Rationals (e.g., '1/2'), Complex (e.g., '1+2i').");
				System.out.println("Commands:");
				System.out.println("  help - Display this help message.");
				System.out.println("  exit - Quit the calculator.");
				continue;
			}
			if (input.isEmpty()) continue;

			try {
				Expression expr = parser.parse(input);
				Value result = calculator.eval(expr);
				System.out.println(result);
			} catch (IllegalConstruction e) {
				System.out.println("Invalid expression structure: " + e.getMessage());
			} catch (Exception e) {
				System.out.println("Error evaluating expression: " + e.getMessage());
			}
		}
		scanner.close();
 	}
}
