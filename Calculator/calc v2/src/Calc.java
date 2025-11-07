// Needed to read console input through BufferedReader
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList; // Need to do more than 2 var operations and store outputs

public class Calc {

    // run the calc functions here
    public static void main(String[] args) throws Exception {
        boolean userDone = false; // repeats the program for multiple calculations
        // input validation
        boolean isNumber = false;
        boolean validOp = false;

        ArrayList<Double> numbers = new ArrayList<Double>();
        ArrayList<Double> calcs = new ArrayList<Double>();
        ArrayList<String> invalidInputs = new ArrayList<String>();
        ArrayList<String> operations = new ArrayList<String>();
        int counter = 0;

        while(userDone == false)
        {
            BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Enter numbers separated by a space (decimals are allowed): ");
            String[] nums = r.readLine().split(" ");

            // for loop to check the while loop to force user to enter a number until they do
            for(int i = 0; i < nums.length; i++) {
                int attempt = 0; // 
                while(!isNumber) {
                //if(!isNumber) {
                    try {
                        double number = 0;
                        if(attempt == 0) {
                            number = Double.parseDouble(nums[i]);
                        } else {
                            number = Double.parseDouble(r.readLine());
                        }

                        // TO-DO: fix this part that checks if it's w/in bounds
                        // forces that the user enters a number within the bounds while still checking for non-number inputs
                        while(!Double.isFinite(number)) {
                            System.out.printf("Please enter a number that is within min/max bounds this time for digit %d: ", i + 1);

                            try {
                                number = Double.parseDouble(r.readLine());
                            } catch (NumberFormatException e) {
                                // if the user enters something that isn't a within the min/max bounds and then enters
                                // something else that isn't a number then print this part out too
                                System.out.print("Error: Invalid input. ");
                                attempt++;
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

            // should block from this until all numbers are validated, but the while loop is messing up
            System.out.print("What operation would you like to perform (Add/Subtract/Multiply/Divide): ");
            String operation = r.readLine();

            // tried to inverse the logic (do while loop w/ switch running first and only repeat if validOp is false 
            // (set validOp to true by default and only change it in the default case, then repeat the switch statement))
            // but that didn't work, so we get this instead
            while(!validOp) {
                // used if statement at first but switches exist lol
                switch(operation.toLowerCase()) {
                    case "add":
                    case "+":
                        calcs.add(add(numbers));
                        operations.add("Addition");
                        validOp = true;
                        break;
                    case "subtract":
                    case "-":
                        calcs.add(subtract(numbers));
                        operations.add("Subtraction");
                        validOp = true;
                        break;
                    case "multiply":
                    case "*":
                        calcs.add(multiply(numbers));
                        operations.add("Multiplication");
                        validOp = true;
                        break;
                    case "divide":
                    case "/":
                        calcs.add(divide(numbers));
                        operations.add("Division");
                        validOp = true;
                        break;
                    default:
                        System.out.print("Please enter a valid operation this time (Add/Subtract/Multiply/Divide): ");
                        operation = r.readLine();
                        break;
                } 
            }
            
            // resets for the next iteration of the while program
            isNumber = false;
            validOp = false;
            numbers.clear();

            System.out.println(calcs.get(counter));
            counter++;

            System.out.print("Would you like to perform more operations (y/n): ");
            String completed = r.readLine();
            
            while(!validOp) {
                if(completed.equalsIgnoreCase("no") | completed.equalsIgnoreCase("n")) {
                    userDone = true;
                    validOp = true;
                } else if(completed.equalsIgnoreCase("yes") | completed.equalsIgnoreCase("y")) {
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
        // cause i could just return 0 then, but running the if statement is probably slower than just doing the
        // calculations until the list of numbers reaches a certain point
        double result = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            result /= list.get(i);
        }

        return result;
    }

}