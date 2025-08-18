package pack;

import java.util.Scanner;

public class SimpleContinuousCalculator1 {

    public static class Operations {
        public static double add(double a, double b) {
            return a + b;
        }

        
        public static double subtract(double a, double b) {
            return a - b;
          }

        public static double multiply(double a, double b) {
            return a * b;
         }

        public static double divide(double a, double b) {
            return a / b;
           }
    }

    
    public static double readDouble(Scanner sc, String message) {
        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
             }
        }
       }

    
    public static double performOperation(Scanner sc, double num1, double num2) {
        while (true) {
            System.out.print("Enter operation (add, subtract, multiply, divide): ");
            String op = sc.nextLine().toLowerCase();

            switch (op) {
                case "add":
                    return Operations.add(num1, num2);
                    
                case "subtract":
                	
                    return Operations.subtract(num1, num2);
                case "multiply":
                    return Operations.multiply(num1, num2);
                case "divide":
                
                	if (num2 != 0) {
                		
                        return Operations.divide(num1, num2);
                    }
                	else {
                        System.out.println("Cannot divide by zero! Try again.");
                    }
                	
                    break;
                default:
                    System.out.println("Invalid operation. Try again.");
            }
        }
    }

    public static double performNextOperation(String op, double result, double nextNum) {
        switch (op) {
            case "add":
                return Operations.add(result, nextNum);
            case "subtract":
                return Operations.subtract(result, nextNum);
            case "multiply":
                return Operations.multiply(result, nextNum);
            case "divide":
                if (nextNum != 0) {
                    return Operations.divide(result, nextNum);
                }
                else
                {
                    System.out.println("Cannot divide by zero! Skipping operation.");
                    return result;
                }
            default:
                System.out.println("Invalid operation. Try again.");
                return result;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double num1 = readDouble(sc, "Enter first number: ");
        double num2 = readDouble(sc, "Enter second number: ");

        double result = performOperation(sc, num1, num2);
        System.out.println("Result: " + result);

        while (true) {
            System.out.print("Enter next operation (add, subtract, multiply, divide) or 'exit': ");
            String op = sc.nextLine().toLowerCase();

            if (op.equals("exit")) {
                System.out.println("Final result: " + result);
                break;
            }

            double nextNum = readDouble(sc, "Enter another number: ");
            
            result = performNextOperation(op, result, nextNum);
            System.out.println("Result: " + result);
        }

        sc.close();
    }
}
