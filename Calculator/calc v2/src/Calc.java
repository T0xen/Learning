// Needed to read console input through BufferedReader
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList; // Need to do more than 2 var operations and store outputs

public class Calc {
    static ArrayList<Double> numbers;
    static ArrayList<Double> calcs;
    static ArrayList<String> operations;

    static String operand;
    static String modeInput;

    // run the calc functions here
    public static void main(String[] args) throws Exception {
        boolean userDone = false; // repeats the program for multiple calculations
        boolean validInput = false; // input validation
        boolean singleLineMode = false; // if true then inputs can be "2 + 2"
        
        operand = "";
        modeInput = "";
        String mode = ""; // track user's mode input for later

        int counter = 0;

        numbers = new ArrayList<Double>();
        calcs = new ArrayList<Double>();
        operations = new ArrayList<String>();

        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Currently the operations that can be performed are addition, subtraction, multiplication, and division. Numbers with decimals can be used.\n");
        while (!userDone) {
            if(counter == 0) {
                System.out.print("Would you like to use single line mode (e.g. 2 + 2 + 2)? Your input will be saved (Y/N): ");
            } else {
                System.out.print("Would you like to use single line mode? Press enter if you would like to stay in the selected mode (Y/N): ");
            }

            mode = modeInput; // store modeInput for 2nd iteration+ so pressing enter keeps you in mode
            modeInput = r.readLine();

            if(modeInput.length() == 0 && mode.length() != 0) {
                modeInput = mode;
            }

            while(!validInput) {
                if (modeInput.equalsIgnoreCase("yes") || modeInput.equalsIgnoreCase("y")) {
                    singleLineMode = true;
                    validInput = true;
                } else if (modeInput.equalsIgnoreCase("no") || modeInput.equalsIgnoreCase("n")) {
                    singleLineMode = false;
                    validInput = true;
                } else {
                    System.out.print("Please enter yes or no this time: ");
                    modeInput = r.readLine();
                }
            }

            if(!singleLineMode) {
                validateInputs();

                validInput = performOperations();
            }
            else {
                singleLineMath();
            }
            
            // resets for the next iteration of the while program
            validInput = false;
            numbers.clear();

            System.out.println(calcs.get(calcs.size() - 1));
            counter++;

            System.out.print("Would you like to perform more operations (y/n): ");
            String completed = r.readLine();

            while (!validInput) {
                if (completed.equalsIgnoreCase("no") | completed.equalsIgnoreCase("n")) {
                    userDone = true;
                    validInput = true;
                } else if (completed.equalsIgnoreCase("yes") | completed.equalsIgnoreCase("y")) {
                    userDone = false;
                    validInput = true;
                } else {
                    System.out.print("Please enter yes or no this time: ");
                    completed = r.readLine();
                }
            }

            validInput = false;
        }

        int n = 1;
        System.out.println("Number of calculations performed: " + counter);
        for (Double calc : calcs) {
            System.out.printf("Operation %d (%s) output: %f \n", n, operations.get(n - 1), calc);
            n++;
        }
    }

    // Reads in user input for the numbers that they want to use in their calculations
    // Broken out into a function for the sake of testing and refactoring
    // ------------------------ Function description ^ ------------------------------------------
    // ------------------------ Other notes | ---------------------------------------------------
    // This could be removed from a function and just put back into the if-else statement in main
    // I tried creating a test version and it just wouldn't work for unit testing, there's potential
    // for this being moved back into main
    public static void singleLineMath() {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        int operandNum = 1;
        int digitNum = 1;

        //double result = 0;

        System.out.print("Enter a calculation with numbers and operands separated by a space: ");
        // Java requires try-catch blocks for all user input via system.in in functions
        try {
            input = r.readLine();
        } catch (Exception e) {
            System.err.println("I/O Error: " + e.getMessage());
        }

        
        // this + if-statement for using the result variable, it looks weird, but the implementation kinda
        // breaks if I try to do it a different way
        char firstChar = input.charAt(0);
        
        if((firstChar == '+' || firstChar == '-' || firstChar == '*' || firstChar == '/') && input.charAt(1) == ' ' && calcs.size() > 0) {
            String result = Double.toString(calcs.get(calcs.size() - 1));
            input = " " + input;
            result = result.concat(input);
            input = result;
        }
        
        String[] nums = input.split(" ");

        if(nums.length == 1) {
            validateNumber(nums[0], digitNum);
            calcs.add(numbers.get(numbers.size() - 1));
            return;
        }


        for (int i = 0; i < nums.length; i++) {
            if(numbers.size() == 2) {
                performOperation(operand);
            }

            if(i % 2 == 1) { // could speed up by just doing bitwise check, but eh
                operand = validateOperation(nums[i], operandNum);
                operandNum++;
            } else {
                validateNumber(nums[i], digitNum);
                digitNum++;
            }
        }

        // while the calculations can be performed w/ more than two variables, since different operands can be entered
        // it's better to check every 2 iterations in the for loop and then perform it one last time to perform the final op
        performOperation(operand);
    }

    // Reads in input to perform calculations for testing
    public static void singleLineMath(String input) {
        int operandNum = 1;
        int digitNum = 1;


        System.out.print("Enter a calculation with numbers and operands separated by a space: ");

        // this + if-statement for using the result variable, it looks weird, but the implementation kinda
        // breaks if I try to do it a different way
        char firstChar = input.charAt(0);
        
        if((firstChar == '+' || firstChar == '-' || firstChar == '*' || firstChar == '/') && input.charAt(1) == ' ' && calcs.size() > 0) {
            String result = Double.toString(calcs.get(calcs.size() - 1));
            input = " " + input;
            result = result.concat(input);
            input = result;
        }

        String[] nums = input.split(" ");

        if(nums.length == 1) {
            validateNumber(nums[0], digitNum);
            calcs.add(numbers.get(numbers.size() - 1));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if(numbers.size() == 2) {
                performOperation(operand);
            }

            if(i % 2 == 1) { // could speed up by just doing bitwise check, but eh
                operand = validateOperation(nums[i], operandNum);
                operandNum++;
            } else {
                validateNumber(nums[i], digitNum);
                digitNum++;
            }
        }

        // while the calculations can be performed w/ more than two variables, since different operands can be entered
        // it's better to check every 2 iterations in the for loop and then perform it one last time to perform the final op
        performOperation(operand);
    }

    // Used to validate the numbers when in single-line mode
    // Broken up for testing and to remove bloat from the singleLineMath method
    public static void validateNumber(String num, int i) {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        boolean isNumber = false;
        double number = 0;
        String input = "";

        int attempt = 0; // tracks how many times a number has been attempted to parse for the sake of determining if user input is needed
                while (!isNumber) {
                    try {
                        if (attempt == 0) {
                            number = Double.parseDouble(num);
                        } else {
                            try {
                                input = r.readLine();
                            } catch (Exception e) {
                                System.err.println("I/O Error: " + e.getMessage());
                            }
                            number = Double.parseDouble(input);
                        }


                        // forces that the user enters a number within the bounds while still checking
                        // for non-number inputs
                        while (!Double.isFinite(number) || number >= Double.MAX_VALUE || number <= (-1 * Double.MAX_VALUE)) {
                            System.out.printf("Please enter a number that is within min/max bounds this time for digit %d: ", i);
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

                        numbers.add(number);
                        isNumber = true;
                    } catch (NumberFormatException e) {
                        System.out.printf("Error: Invalid input; please enter a number for digit %d this time: ", i);
                        attempt++;
                    }
                }
                // reset so the next try-catch block repeats while necessary
                isNumber = false;
    }

    // Used to validate the numbers when in single-line mode
    // Broken up for testing and to remove bloat from the singleLineMath method
    public static boolean validateNumber(String num) {
        boolean isNumber = false;
        double number = 0;

        try {
            number = Double.parseDouble(num);

            if (!Double.isFinite(number) || number >= Double.MAX_VALUE || number <= (-1 * Double.MAX_VALUE)) {
                return isNumber; // f
            }
            
            numbers.add(number);
            isNumber = true;
        } catch (NumberFormatException e) {
            return isNumber; // f
        }
        
        return isNumber;
    }

    

    // Tests the core functionality of mode selection via unit testing
    // Was based on a method of the same name but that one broke for some reason
    public static boolean mode(String mode) {
        boolean validInput = true;
        boolean singleLineMode = false;

        if (mode.equalsIgnoreCase("yes") || mode.equalsIgnoreCase("y")) {
            singleLineMode = true;
        } else if (mode.equalsIgnoreCase("no") || mode.equalsIgnoreCase("n")) {
            singleLineMode = false; // isn't necessary but put it in for clarity
        } else {
            System.out.print("Please enter yes or no this time: ");
            validInput = false;
        }

        // if the input is invalid or the mode is not single line
        if(!validInput || !singleLineMode) {
            return false;
        }

        // return true if input is valid && singleLineMode is true
        return true;
    }

    // Reads in the user input for operation type and stores it to an ArrayList of operands
    // to perform operations on single-line inputs
    public static String validateOperation(String operation, int operandNum) {
        boolean validOperation = false;
        BufferedReader op = new BufferedReader(new InputStreamReader(System.in));

        while (!validOperation) {
            switch (operation.toLowerCase()) {
                case "+":
                    operations.add("Addition");
                    validOperation = true;
                    break;
                case "-":
                    operations.add("Subtraction");
                    validOperation = true;
                    break;
                case "*":
                    operations.add("Multiplication");
                    validOperation = true;
                    break;
                case "/":
                    operations.add("Division");
                    validOperation = true;
                    break;
                default:
                    System.out.printf("Please enter a valid operand for operand %d this time: ", operandNum);
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
        return operation;
    }

    // Reads in the user input for operation type and stores it to an ArrayList of operands
    // to perform operations on single-line inputs
    public static boolean validateOperation(String operation) {
        boolean validOperation = false;

        switch (operation.toLowerCase()) {
            case "+":
                validOperation = true;
                break;
            case "-":
                validOperation = true;
                break;
            case "*":
                validOperation = true;
                break;
            case "/":
                validOperation = true;
                break;
            default:
                validOperation = false;
                break;
        }
        return validOperation;
    }

    // Reads in the user input for operation type and stores it to an ArrayList of operands
    // to perform operations on single-line inputs
    public static void performOperation(String operation) {
        switch (operation.toLowerCase()) {
            case "+":
                calcs.add(add(numbers));
                break;
            case "-":
                calcs.add(subtract(numbers));
                break;
            case "*":
                calcs.add(multiply(numbers));
                break;
            case "/":
                calcs.add(divide(numbers));
                break;
        }

        numbers.clear();
        numbers.add(calcs.get(calcs.size() - 1));
        
    }

    // Testing version of performOperation, since all the inputs into the normal version
    // are validated beforehand, this is less so about validating the inputs and moreso
    // the functionality.
    public static double performOperation(String operation, ArrayList<Double> nums) {
        double result = 0;
        switch (operation.toLowerCase()) {
            case "+":
                result = add(nums);
                break;
            case "-":
                result = subtract(nums);
                break;
            case "*":
                result = multiply(nums);
                break;
            case "/":
                result = divide(nums);
                break;
            default:
                result = Double.MIN_VALUE;
                break; // included to be safe
        }

        nums.clear();
        nums.add(result);
        
        return result;
    }

    // Reads in user input for the numbers that they want to use in their calculations
    // Broken out into a function for the sake of testing and refactoring
    public static void validateInputs() {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        boolean isNumber = false;
        String input = "";
        double number = 0;

        System.out.print("Enter numbers separated by a space: ");
        // Java requires try-catch blocks for all user input via system.in in functions
        try {
            input = r.readLine();
        } catch (Exception e) {
            System.err.println("I/O Error: " + e.getMessage());
        }



        String[] nums = input.split(" ");

        //char firstChar = input.charAt(0);
        
        if((nums[0].equalsIgnoreCase("result")) && calcs.size() > 0) {
            nums[0] = Double.toString(calcs.get(calcs.size() - 1));
        }

        for (int i = 0; i < nums.length; i++) {
            int attempt = 0; //
            while (!isNumber) {
                try {
                    if (attempt == 0) {
                        number = Double.parseDouble(nums[i]);
                    } else {
                        try {
                            input = r.readLine();
                        } catch (Exception e) {
                            System.err.println("I/O Error: " + e.getMessage());
                        }

                        number = Double.parseDouble(input);
                    }

                    // forces that the user enters a number within the bounds while still checking
                    // for non-number inputs
                    while (!Double.isFinite(number) || number >= Double.MAX_VALUE || number <= (-1 * Double.MAX_VALUE)) {
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
                if(!Double.isFinite(number) || number >= Double.MAX_VALUE || number <= (-1 * Double.MAX_VALUE)) {
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