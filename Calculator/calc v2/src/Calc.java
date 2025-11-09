
// Needed to read console input through BufferedReader
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList; // Need to do more than 2 var operations and store outputs

public class Calc {
    static ArrayList<Double> numbers;
    static ArrayList<Double> calcs;
    static ArrayList<String> invalidInputs;
    static ArrayList<String> operations;

    // run the calc functions here
    public static void main(String[] args) throws Exception {
        boolean userDone = false; // repeats the program for multiple calculations
        boolean validOp = false; // input validation
        int counter = 0;

        numbers = new ArrayList<Double>();
        calcs = new ArrayList<Double>();
        invalidInputs = new ArrayList<String>();
        operations = new ArrayList<String>();

        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

        while (!userDone) {
            /*
             * System.out.
             * print("Enter numbers separated by a space (decimals are allowed): ");
             * String[] nums = r.readLine().split(" ");
             * 
             * // for loop to check the while loop to force user to enter a number until
             * they do
             * for(int i = 0; i < nums.length; i++) {
             * int attempt = 0; //
             * while(!isNumber) {
             * try {
             * double number = 0;
             * if(attempt == 0) {
             * number = Double.parseDouble(nums[i]);
             * } else {
             * number = Double.parseDouble(r.readLine());
             * }
             * 
             * // forces that the user enters a number within the bounds while still
             * checking for non-number inputs
             * while(!Double.isFinite(number)) {
             * System.out.
             * printf("Please enter a number that is within min/max bounds this time for digit %d: "
             * , i + 1);
             * 
             * try {
             * number = Double.parseDouble(r.readLine());
             * } catch (NumberFormatException e) {
             * // if the user enters something that isn't a within the min/max bounds and
             * then enters
             * // something else that isn't a number then print this part out too
             * System.out.print("Error: Invalid input. ");
             * attempt++;
             * }
             * }
             * 
             * numbers.add(number);
             * isNumber = true;
             * } catch (NumberFormatException e) {
             * System.out.
             * printf("Error: Invalid input; please enter a number for digit %d this time: "
             * , i + 1);
             * attempt++;
             * }
             * }
             * 
             * // reset so the next try-catch block repeats while necessary
             * isNumber = false;
             * }
             */

            validateInputs();

            validOp = performOperations();

            // resets for the next iteration of the while program
            validOp = false;
            numbers.clear();

            System.out.printf("%f", calcs.get(counter));
            counter++;

            System.out.print("Would you like to perform more operations (y/n): ");
            String completed = r.readLine();

            // could potentially bust out into a function but eh, i think it's fine atm
            while (!validOp) {
                if (completed.equalsIgnoreCase("no") | completed.equalsIgnoreCase("n")) {
                    userDone = true;
                    validOp = true;
                } else if (completed.equalsIgnoreCase("yes") | completed.equalsIgnoreCase("y")) {
                    userDone = false;
                    validOp = true;
                } else {
                    System.out.print("Please enter yes or no this time: ");
                    completed = r.readLine();
                }
            }
        }

        // calc output report (could extend to maybe show the math when allowing for single line inputs)
        int n = 1;
        System.out.println("Number of operations performed: " + counter);
        for (Double calc : calcs) {
            System.out.printf("Operation %d (%s) output: %f \n", n, operations.get(n - 1), calc);
            n++;
        }
    }

    // Reads in user input for the numbers that they want to use in their calculations
    // Broken out into a function for the sake of testing and refactoring
    public static void validateInputs() {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        boolean isNumber = false;
        String input = "";
        double number = 0;

        System.out.print("Enter numbers separated by a space (decimals are allowed): ");
        // Java requires try-catch blocks for all user input via system.in in functions
        try {
            input = r.readLine();
        } catch (Exception e) {
            System.err.println("I/O Error: " + e.getMessage());
        }

        String[] nums = input.split(" ");

        for (int i = 0; i < nums.length; i++) {
            int attempt = 0; //
            while (!isNumber) {
                try {
                    if (attempt == 0) {
                        number = Double.parseDouble(nums[i]);
                        // forces that the user enters a number within the bounds while still checking
                        // for non-number inputs
                        while (!Double.isFinite(number) || number >= Double.MAX_VALUE || number <= Double.MIN_VALUE) {
                            System.out.printf(
                                    "Please enter a number that is within min/max bounds this time for digit %d: ", i + 1);
                            try {
                                try {
                                    input = r.readLine();
                                } catch (Exception e) {
                                    System.err.println("I/O Error: " + e.getMessage());
                                }
                                number = Double.parseDouble(input);
                            } catch (NumberFormatException e) {
                                // if the user enters something that isn't a within the min/max bounds and then enters
                                // something else that isn't a number then print this part out too
                                System.out.print("Error: Invalid input. ");
                                attempt++;
                            }
                        }
                    } else {
                        try {
                            number = Double.parseDouble(r.readLine());
                        } catch (Exception e) {
                            System.err.println("I/O Error: " + e.getMessage());
                        }
                    }

                    numbers.add(number);
                    isNumber = true;
                } catch (NumberFormatException e) {
                    System.out.printf("Error: Invalid input; please enter a number for digit %d this time: ", i + 1);
                    attempt++;
                }
            }
            // reset so the next try-catch block repeats while necessary
            isNumber = false;
        }
    }

    // Tests the core functionality of the try-catch & input validation
    public static boolean validateInputs(String input) {
        double number = 0;
        // for the sake of testing add the validity of all inputs to an ArrayList
        ArrayList<Boolean> wereInputsValid = new ArrayList<Boolean>();

        String[] nums = input.split(" ");

        for (int i = 0; i < nums.length; i++) {
            try {
                number = Double.parseDouble(nums[i]);
                if(!Double.isFinite(number) || number >= Double.MAX_VALUE || number <= Double.MIN_VALUE) {
                    wereInputsValid.add(false);
                }
                wereInputsValid.add(true);
            } catch (NumberFormatException e) {
                wereInputsValid.add(false);
            }
        }

        if(wereInputsValid.contains(false)) {
            return false;
        }

        return true;
    }

    // Reads in the user input for operation type and then performs the operation based on it
    // Broken out from main for the sake of enhancing code test-ability and readability
    public static boolean performOperations() {
        boolean validOperation = false;
        BufferedReader op = new BufferedReader(new InputStreamReader(System.in));
        String operation = "";

        System.out.print("What operation would you like to perform (Add/Subtract/Multiply/Divide): ");
        try {
            operation = op.readLine();
        } catch (Exception e) {
            System.err.println("I/O Error: " + e.getMessage());
        }

        while (!validOperation) {
            switch (operation.toLowerCase()) {
                case "add":
                case "+":
                    calcs.add(add(numbers));
                    operations.add("Addition");
                    validOperation = true;
                    break;
                case "subtract":
                case "-":
                    calcs.add(subtract(numbers));
                    operations.add("Subtraction");
                    validOperation = true;
                    break;
                case "multiply":
                case "*":
                    calcs.add(multiply(numbers));
                    operations.add("Multiplication");
                    validOperation = true;
                    break;
                case "divide":
                case "/":
                    calcs.add(divide(numbers));
                    operations.add("Division");
                    validOperation = true;
                    break;
                default:
                    System.out.print("Please enter a valid operation this time (Add/Subtract/Multiply/Divide): ");
                    // for some reason Java throws an error when trying to use readLine() only in
                    // functions outside of main so I had to put a try-catch block
                    try {
                        operation = op.readLine();
                    } catch (Exception e) {
                        System.err.println("I/O Error: " + e.getMessage());
                    }
                    break;
            }
        }
        return validOperation;
    }


    // Tests the core functionality of the input validation of the performOperations function
    public static boolean performOperations(String operation, ArrayList<Double> numbers) {
        boolean validOp = false;
        // used if statement at first but switches exist lol
        switch (operation.toLowerCase()) {
            case "add":
            case "+":
                add(numbers);
                validOp = true;
                break;
            case "subtract":
            case "-":
                subtract(numbers);
                validOp = true;
                break;
            case "multiply":
            case "*":
                multiply(numbers);
                validOp = true;
                break;
            case "divide":
            case "/":
                divide(numbers);
                validOp = true;
                break;
            default:
                break;
        }
        return validOp;
    }

    // add two variables
    public static double add(ArrayList<Double> list) {
        double result = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            result += list.get(i);
        }

        return result;
    }

    // subtract two variables
    public static double subtract(ArrayList<Double> list) {
        double result = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            result -= list.get(i);
        }

        return result;
    }

    // multiply two variables
    public static double multiply(ArrayList<Double> list) {
        double result = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            result *= list.get(i);
        }

        // there's a potential speedup if i check that the list contains a 0 and just returning 0 immediately
        return result;
    }

    // divide two variables
    public static double divide(ArrayList<Double> list) {
        // if a divisor is 0, return NaN as a stand-in for undefined
        double divisorCheck = 0;
        if (list.contains(divisorCheck) & list.lastIndexOf(divisorCheck) != 0) {
            return Double.NaN;
        }

        // theoretically there's a speedup for division if i check that the first number is 0
        // cause i could just return 0 then, but running the if statement is probably slower 
        // than just doing the calculations until the list of numbers reaches a certain point
        double result = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            result /= list.get(i);
        }

        return result;
    }

}