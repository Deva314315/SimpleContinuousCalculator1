package pack;

import java.util.HashMap;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class CalculatorHelper {
	private Scanner sc;
	private HashMap<String, Double> memory;
	
	public CalculatorHelper(Scanner sc) {
		this.sc = sc;
		this.memory = new HashMap<>();
	}
	
	public static class Operations{
		
		public static double add(double a , double b) {
			return a+b;
		}
		public static double subtract(double a , double b) {
			return a - b;
		}
		public static double multiply(double a , double b) {
			return a * b;
		}
		public static double divide(double a , double b) {
			return a / b;
		}
		public static double square(double a) {
			return a * a;
		}
		public static double sqrt(double a) {
			if(a<0) {
				System.out.println("Square root of negative number is not real, Returning 0");
				return 0;
			}
			return Math.sqrt(a);
		}
		
	}
	
	public double readDouble(String message) {
		while(true) {
			try {
				System.out.print(message);
				return Double.parseDouble(sc.nextLine());
			}
			catch(NumberFormatException e) {
				System.out.println("Invalid Input.");
				System.out.println("Please enter again");
			}
			
		}
	}
	
	public double performOperation(double num1, double num2) {
		while(true) {
			System.out.println("Enter operation (add, subtract, multiply, divide): ");
			String op = sc.nextLine().toLowerCase();
			
			switch(op) {
			case "add":
				return Operations.add(num1, num2);
			case "subtract":
				return Operations.subtract(num1, num2);
			case "multiply":
				return Operations.multiply(num1, num2);
			case "divide":
				if(num2 != 0) {
					return Operations.divide(num1, num2);
				}else {
					 System.out.println("Cannot divide by zero! Try again.");
					 break;
				}
				default:
					System.out.println("Invalid Input, Try again");
							
			}
		}
	}
	
	public double performNextOperation(String op, double result, double nextNum) {
		switch(op) {
		case "add":
			return Operations.add(result, nextNum);
		case "subtract":
			return Operations.subtract(result, nextNum);
		case "multiply":
			return Operations.multiply(result, nextNum);
		case "divide":
			if(nextNum!=0) {
				return Operations.divide(result, nextNum);
			}else {
				System.out.println("Cannot divide by zero! Try again.");
				return result;
			}
		
		default:
				System.out.println("Enter valid input");
				return result;
		}
	}
	
	public double performNextOperation(String op, double result) {
	    switch (op) {
	        case "square": 
	        	return Operations.square(result);
	        case "sqrt":   
	        	return Operations.sqrt(result);
	        default:
	            System.out.println("Invalid operation. Try again.");
	            return result;
	    }
	}
	
	public void storeInMemory(String key, double value) {
        memory.put(key, value);
        System.out.println("Stored in memory: " + key + " = " + value);
    }

    public Double getFromMemory(String key) {
        return memory.get(key);
    }

    public void storeInFile(String key, double value) {
        try (FileWriter writer = new FileWriter("calculator_storage.txt", true)) {
            writer.write(key + "=" + value + "\n");
            System.out.println("Stored in file: " + key + " = " + value);
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }

    public Double getFromFile(String key) {
        try (BufferedReader reader = new BufferedReader(new FileReader("calculator_storage.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2 && parts[0].equals(key)) {
                    return Double.parseDouble(parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file.");
        }
        return null;
    }
}
