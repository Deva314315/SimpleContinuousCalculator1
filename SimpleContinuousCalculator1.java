package pack;

import java.util.Scanner;

public class SimpleContinuousCalculator1 {
	public static void main(String[] args ) {
		Scanner sc = new Scanner(System.in);
		
		CalculatorHelper helper = new CalculatorHelper(sc);
		
		double num1 = helper.readDouble("Enter First Integer");
		double num2 = helper.readDouble("Enter Second Integer");
		
		double result = helper.performOperation(num1,num2);
		System.out.println("Result : "+ result);
		
		while (true) {
		    System.out.print("Enter next operation (add, subtract, multiply, divide, square, sqrt, store, recall, exit): ");
		    String op = sc.nextLine().toLowerCase();
		    if (op.trim().isEmpty()) {
		        System.out.println("No operation entered. Please try again.");
		        continue;
		    }
		    if (op.equals("exit")) {
                System.out.println("Final result: " + result);
                break;
            } else if (op.equals("store")) {
                System.out.print("Enter key to store this result: ");
                String key = sc.nextLine();
                System.out.print("Where do you want to store? (1=Memory, 2=File): ");
                String choice = sc.nextLine();

                if (choice.equals("1")) {
                    helper.storeInMemory(key, result);
                } else if (choice.equals("2")) {
                    helper.storeInFile(key, result);
                } else {
                    System.out.println("Invalid choice. Result not stored.");
                }
                continue;
            } else if (op.equals("recall")) {
                System.out.print("Enter key to recall value: ");
                String key = sc.nextLine();
                System.out.print("Where do you want to recall from? (1=Memory, 2=File): ");
                String choice = sc.nextLine();

                Double recalled = null;
                if (choice.equals("1")) {
                    recalled = helper.getFromMemory(key);
                } else if (choice.equals("2")) {
                    recalled = helper.getFromFile(key);
                } else {
                    System.out.println("Invalid choice.");
                }

                if (recalled != null) {
                    System.out.println("Recalled Value: " + recalled);
                    result = recalled;
                } else {
                    System.out.println("NOT FOUND");
                }
                continue;
            }

		    if (op.equals("square") || op.equals("sqrt")) {
		        result = helper.performNextOperation(op, result);   
		        System.out.printf("Result: %.2f%n", result);
		        continue;
		    }

		     double nextNum = helper.readDouble("Enter another number: ");
		    result = helper.performNextOperation(op, result, nextNum); 
		    System.out.printf("Result: %.2f%n", result);
		}

		sc.close();
	}
}
