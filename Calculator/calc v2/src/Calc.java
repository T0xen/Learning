// Needed to read console input through BufferedReader
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Calc {

    // run the calc functions here
    public static void main(String[] args) throws Exception {
        //boolean userDone = false;
        boolean isNumber = false;
        boolean validOp = false;
        float calculation = 0;
        float a = 0, b = 0;
        //while(userDone == false)
        //{
            BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

            // could extend to > 2 vars by asking number of digits and using a for loop + array
            System.out.print("Enter a number (decimals are allowed): ");

            // while loop to force user to enter a number until they do
            // if it didn't kill the program when parsing the float if it's the wrong type this could be
            // done through if statements, not these nested while-loop try catch blocks 
            while(isNumber == false) {
                try {
                    a = Float.parseFloat(r.readLine());

                    // forces that the user enters a number within the bounds while still checking for non-number inputs
                    while(!Float.isFinite(a)) {
                        System.out.print("Please enter a number that is within min/max bounds this time: ");

                        try {
                            a = Float.parseFloat(r.readLine());
                        } catch (NumberFormatException e) {
                            // if the user enters something that isn't a within the min/max bounds and isn't a number then print this part out too
                            System.out.print("Error: Invalid input. ");
                        }                        
                    }

                    isNumber = true;
                } catch (NumberFormatException e) {
                    System.out.print("Error: Invalid input; please enter a number this time: ");
                }
            }
        
            // reset so the next try-catch block repeats while necessary
            isNumber = false;

            System.out.print("Enter a number (decimals are allowed): ");
            while(isNumber == false) {
                try {
                    b = Float.parseFloat(r.readLine());

                    while(!Float.isFinite(b)) {
                        System.out.print("Please enter a number that is within min/max bounds this time: ");

                        try {
                            b = Float.parseFloat(r.readLine());
                        } catch (NumberFormatException e) {
                            System.out.print("Error: Invalid input. ");
                        }                        
                    }

                    isNumber = true;
                } catch (NumberFormatException e) {
                    System.out.print("Error: Invalid input; please enter a number this time: ");
                }
            }

            System.out.print("What operation would you like to perform (Add/Subtract/Multiply/Divide): ");
            String operation = r.readLine();

            // tried to inverse the logic (do while loop w/ switch running first and only repeat if validOp is false 
            // (set validOp to true by default and only change it in the default case, then repeat the switch statement))
            // but that didn't work, so we get this instead
            while(validOp == false) {
                // used if statement at first but switches exist lol
                switch(operation.toLowerCase()) {
                    case "add":
                        calculation = add(a, b);
                        validOp = true;
                        break;
                    case "+":
                        calculation = add(a, b);
                        validOp = true;
                        break;
                    case "subtract":
                        calculation = subtract(a, b);
                        validOp = true;
                        break;
                    case "-":
                        calculation = subtract(a, b);
                        validOp = true;
                        break;
                    case "multiply":
                        calculation = multiply(a, b);
                        validOp = true;
                        break;
                    case "*":
                        calculation = multiply(a, b);
                        validOp = true;
                        break;
                    case "divide":
                        calculation = divide(a, b);
                        validOp = true;
                        break;
                    case "/":
                        calculation = divide(a, b);
                        validOp = true;
                        break;
                    default:
                        validOp = false;
                        System.out.print("Please enter a valid operation this time (Add/Subtract/Multiply/Divide): ");
                        operation = r.readLine();
                        break;
                } 
            }
            
            System.out.println(calculation);
        //}
    }
    // add two variables
    public static float add(float x, float y) {
        return x + y;
    }

    // subtract two variables
    public static float subtract(float x, float y) {
        return x - y;
    }

    // multiply two variables
    public static float multiply(float x, float y) {
        return x * y;
    }

    // divide two variables
    public static float divide(float x, float y) {
        return x / y;
    }

}