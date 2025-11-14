import java.util.ArrayList;

import org.junit.Test;
import org.junit.Assert;

public class CalcTest {
    @Test
    public void testAddition0() {
        // initialize calc
        Calc calc = new Calc();
        ArrayList<Double> numbers = new ArrayList<Double>();
        numbers.add(1.25);
        numbers.add(55.923829);
        
        // perform addition
        double result = calc.add(numbers);

        // verify that it matches
        Assert.assertEquals(57.173829, result, 0.01);
    }

    @Test
    public void testAddition1() {
        // initialize calc
        Calc calc = new Calc();
        ArrayList<Double> numbers = new ArrayList<Double>();
        numbers.add(1.0);
        numbers.add(55.923829);
        
        // perform addition
        double result = calc.add(numbers);

        // verify that it matches
        Assert.assertEquals(56.923829, result, 0.01);
    }

    @Test
    public void testSubtraction0() {
        // initialize calc
        Calc calc = new Calc();
        ArrayList<Double> numbers = new ArrayList<Double>();
        numbers.add(-15.25);
        numbers.add(-1.43);
        
        // perform subtraction
        double result = calc.subtract(numbers);

        // verify that it matches
        Assert.assertEquals(-13.82, result, 0.01);
    }

    @Test
    public void testSubtraction1() {
        // initialize calc
        Calc calc = new Calc();
        ArrayList<Double> numbers = new ArrayList<Double>();
        numbers.add(100.0);
        numbers.add(1.25);
        
        // perform subtraction
        double result = calc.subtract(numbers);

        // verify that it matches
        Assert.assertEquals(98.75, result, 0.01);
    }

    @Test
    public void testMultiplication0() {
        // initialize calc
        Calc calc = new Calc();
        ArrayList<Double> numbers = new ArrayList<Double>();
        numbers.add(1.5);
        numbers.add(1.5);
        
        // perform multiplication
        double result = calc.multiply(numbers);

        // verify that it matches
        Assert.assertEquals(2.25, result, 0.01);
    }

    @Test
    public void testMultiplication1() {
        // initialize calc
        Calc calc = new Calc();
        ArrayList<Double> numbers = new ArrayList<Double>();
        numbers.add(-1.5);
        numbers.add(1.5);
        
        // perform multiplication
        double result = calc.multiply(numbers);

        // verify that it matches
        Assert.assertEquals(-2.25, result, 0.01);
    }

    @Test
    public void testDivision0() {
        // initialize calc
        Calc calc = new Calc();
        ArrayList<Double> numbers = new ArrayList<Double>();
        numbers.add(1.5);
        numbers.add(1.5);
        
        // perform division
        double result = calc.divide(numbers);

        // verify that it matches
        Assert.assertEquals(1, result, 0.01);
    }

    @Test
    public void testDivision1() {
        // initialize calc
        Calc calc = new Calc();
        ArrayList<Double> numbers = new ArrayList<Double>();
        numbers.add(1.5);
        numbers.add((double) 0);
        
        // perform division
        double result = calc.divide(numbers);

        // verify that it matches
        Assert.assertEquals(Double.NaN, result, 0.01);
    }

    @Test
    public void testValidateInputs0() {
        // initialize calc
        Calc calc = new Calc();
        
        double invalid = Double.MAX_VALUE;
        String input = String.valueOf(invalid);
        // validate input
        boolean validInputs = calc.validateInputs(input);

        // verify that it matches
        Assert.assertFalse(validInputs);
    }

    @Test
    public void testValidateInputs1() {
        // initialize calc
        Calc calc = new Calc();
        
        double invalid = -1 * Double.MAX_VALUE;
        String input = String.valueOf(invalid);
        // validate input
        boolean validInputs = calc.validateInputs(input);

        // verify that it matches
        Assert.assertFalse(validInputs);
    }

    @Test
    public void testValidateInputs2() {
        // initialize calc
        Calc calc = new Calc();
        
        String input = String.valueOf("10000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.0");
        // validate input
        boolean validInputs = calc.validateInputs(input);

        // verify that it matches
        Assert.assertFalse(validInputs);
    }

    @Test
    public void testValidateInputs3() {
        // initialize calc
        Calc calc = new Calc();
        
        String input = String.valueOf("what 2");
        // validate input
        boolean validInputs = calc.validateInputs(input);

        // verify that it matches
        Assert.assertFalse(validInputs);
    }

    @Test
    public void testValidateInputs4() {
        // initialize calc
        Calc calc = new Calc();
        
        String input = String.valueOf("2 what");
        // validate input
        boolean validInputs = calc.validateInputs(input);

        // verify that it matches
        Assert.assertFalse(validInputs);
    }

    @Test
    public void testValidateInputs5() {
        // initialize calc
        Calc calc = new Calc();
        
        String input = String.valueOf("1.2.3.4");
        // validate input
        boolean validInputs = calc.validateInputs(input);

        // verify that it matches
        Assert.assertFalse(validInputs);
    }

    @Test
    public void testValidateInputs6() {
        // initialize calc
        Calc calc = new Calc();
        
        String input = String.valueOf("2 2 2");
        // validate input
        boolean validInputs = calc.validateInputs(input);

        // verify that it matches
        Assert.assertTrue(validInputs);
    }

    @Test
    public void testPerformOperations0() {
        // initialize calc
        Calc calc = new Calc();
        ArrayList<Double> numbers = new ArrayList<Double>();
        numbers.add(1.25);
        numbers.add(55.923829);
        
        // validate input
        String operation = "+";
        boolean validOp = calc.performOperations(operation, numbers);

        // verify that it matches
        Assert.assertTrue(validOp);
    }

    @Test
    public void testPerformOperations1() {
        // initialize calc
        Calc calc = new Calc();
        ArrayList<Double> numbers = new ArrayList<Double>();
        numbers.add(1.25);
        numbers.add(55.923829);
        
        // validate input (added code to test function that should cause it to rerun w/ a valid input)
        String operation = "bwaaaaah";
        boolean validOp = calc.performOperations(operation, numbers);

        // verify that it matches
        Assert.assertFalse(validOp);
    }

    @Test
    public void testPerformOperations2() {
        // initialize calc
        Calc calc = new Calc();
        ArrayList<Double> numbers = new ArrayList<Double>();
        numbers.add(1.25);
        numbers.add(55.923829);
        
        // validate input
        String operation = "should fail";
        boolean validOp = calc.performOperations(operation, numbers);

        // verify that it matches
        Assert.assertFalse(validOp);
    }

    @Test
    public void testMode0() {
        // initialize calc
        Calc calc = new Calc();
        String input = "bwaah";
        
        // validate input (should fail bc invalid input)
        boolean result = calc.mode(input);

        // verify that it matches
        Assert.assertFalse(result);
    }

    @Test
    public void testMode1() {
        // initialize calc
        Calc calc = new Calc();
        String input = "no";
        
        // validate input (should be false bc not singleLineMode)
        boolean result = calc.mode(input);

        // verify that it matches
        Assert.assertFalse(result);
    }

    @Test
    public void testMode2() {
        // initialize calc
        Calc calc = new Calc();
        String input = "y";
        
        // validate input (should be false bc not singleLineMode)
        boolean result = calc.mode(input);

        // verify that it matches
        Assert.assertTrue(result);
    }

    @Test
    public void testValidateNumber0() {
        // initialize calc
        Calc calc = new Calc();
        calc.numbers = new ArrayList<Double>();
        String input = "2";
        
        // validate input (should be true bc number)
        boolean result = calc.validateNumber(input);

        // verify that it matches
        Assert.assertTrue(result);
    }

    @Test
    public void testValidateNumber1() {
        // initialize calc
        Calc calc = new Calc();
        calc.numbers = new ArrayList<Double>();
        String input = "-2";
        
        // validate input (should be true bc number)
        boolean result = calc.validateNumber(input);

        // verify that it matches
        Assert.assertTrue(result);
    }

    @Test
    public void testValidateNumber2() {
        // initialize calc
        Calc calc = new Calc();
        calc.numbers = new ArrayList<Double>();
        String input = "what";
        
        // validate input (should be false bc not number)
        boolean result = calc.validateNumber(input);

        // verify that it matches
        Assert.assertFalse(result);
    }

    @Test
    public void testValidateOperation0() {
        // initialize calc
        Calc calc = new Calc();
        String input = "what";
        
        // validate input (should be false bc not operand)
        boolean result = calc.validateOperation(input);

        // verify that it matches
        Assert.assertFalse(result);
    }

    @Test
    public void testValidateOperation1() {
        // initialize calc
        Calc calc = new Calc();
        String input = "+";
        
        // validate input (should be true bc operand)
        boolean result = calc.validateOperation(input);

        // verify that it matches
        Assert.assertTrue(result);
    }

    @Test
    public void testValidateOperation2() {
        // initialize calc
        Calc calc = new Calc();
        String input = "-";
        
        // validate input (should be true bc operand)
        boolean result = calc.validateOperation(input);

        // verify that it matches
        Assert.assertTrue(result);
    }

    @Test
    public void testValidateOperation3() {
        // initialize calc
        Calc calc = new Calc();
        String input = "*";
        
        // validate input (should be true bc operand)
        boolean result = calc.validateOperation(input);

        // verify that it matches
        Assert.assertTrue(result);
    }

    @Test
    public void testValidateOperation4() {
        // initialize calc
        Calc calc = new Calc();
        String input = "/";
        
        // validate input (should be true bc operand)
        boolean result = calc.validateOperation(input);

        // verify that it matches
        Assert.assertTrue(result);
    }

    @Test
    public void testPerformOperation0() {
        // initialize calc
        Calc calc = new Calc();
        ArrayList<Double> numbers = new ArrayList<Double>();
        // simulated input = 2 + 2 + 2 + 2
        numbers.add(2.0);
        String[] operands = {"+", "+", "+"};
        double result = 0;

        // perform operations
        for (String string : operands) {
            numbers.add(2.0);
            result = calc.performOperation(string, numbers);
        }

        // verify that it matches
        Assert.assertEquals(8, result, 0.01);
    }

    @Test
    public void testPerformOperation1() {
        // initialize calc
        Calc calc = new Calc();
        ArrayList<Double> numbers = new ArrayList<Double>();
        // simulated input = -2 + 3 - 3 - 3
        numbers.add(-2.0);
        String[] operands = {"+", "-", "-"};
        double result = 0;

        // perform operations
        for (String string : operands) {
            numbers.add(3.0);
            result = calc.performOperation(string, numbers);
        }

        // verify that it matches
        Assert.assertEquals(-5, result, 0.01);
    }

    @Test
    public void testPerformOperation2() {
        // initialize calc
        Calc calc = new Calc();
        ArrayList<Double> numbers = new ArrayList<Double>();
        // simulated input = 2 + 1.5 - 1.5 * 1.5 / 1.5
        numbers.add(2.0);
        String[] operands = {"+", "-", "*", "/"};
        double result = 0;

        // perform operations
        for (String string : operands) {
            numbers.add(1.5);
            result = calc.performOperation(string, numbers);
        }

        // verify that it matches
        Assert.assertEquals(2, result, 0.01);
    }

    @Test
    public void testPerformOperation3() {
        // initialize calc
        Calc calc = new Calc();
        ArrayList<Double> numbers = new ArrayList<Double>();
        // simulated input = -2 + 3 - 3 - 3
        numbers.add(2.0);
        String[] operands = {"what"};
        double result = 0;

        // perform operations
        for (String string : operands) {
            numbers.add(1.5);
            result = calc.performOperation(string, numbers);
        }

        // verify that it matches
        Assert.assertEquals(Double.MIN_VALUE, result, 0.01);
    }

    @Test
    public void testResult0() {
        // initialize calc
        Calc calc = new Calc();
        calc.operations = new ArrayList<String>();
        calc.numbers = new ArrayList<Double>();
        calc.calcs = new ArrayList<Double>();
        calc.calcs.add(2.9);
        
        String input = "+ 2.9";

        calc.singleLineMath(input);
        double result = calc.calcs.get(calc.calcs.size() - 1);

        
        // verify that it matches
        Assert.assertEquals(5.8, result, 0.01);
    }

    @Test
    public void testResult1() {
        // initialize calc
        Calc calc = new Calc();
        calc.operations = new ArrayList<String>();
        calc.numbers = new ArrayList<Double>();
        calc.calcs = new ArrayList<Double>();
        calc.calcs.add(2.9);
        
        String input = "- 2";

        calc.singleLineMath(input);
        double result = calc.calcs.get(calc.calcs.size() - 1);
        
        // verify that it matches
        Assert.assertEquals(0.9, result, 0.01);
    }

    @Test
    public void testResult2() {
        // initialize calc
        Calc calc = new Calc();
        calc.operations = new ArrayList<String>();
        calc.numbers = new ArrayList<Double>();
        calc.calcs = new ArrayList<Double>();
        calc.calcs.add(2.9);
        
        String input = "* 2.9";

        calc.singleLineMath(input);
        double result = calc.calcs.get(calc.calcs.size() - 1);
        
        // verify that it matches
        Assert.assertEquals(8.41, result, 0.01);
    }

    @Test
    public void testResult3() {
        // initialize calc
        Calc calc = new Calc();
        calc.operations = new ArrayList<String>();
        calc.numbers = new ArrayList<Double>();
        calc.calcs = new ArrayList<Double>();
        calc.calcs.add(2.9);
        
        String input = "/ 1.5";

        calc.singleLineMath(input);
        double result = calc.calcs.get(calc.calcs.size() - 1);
        
        // verify that it matches
        Assert.assertEquals(1.93, result, 0.01);
    }

    @Test
    public void testSingleLineMath0() {
        // initialize calc
        Calc calc = new Calc();
        calc.operations = new ArrayList<String>();
        calc.numbers = new ArrayList<Double>();
        calc.calcs = new ArrayList<Double>();
        
        String input = "2 + 1.5 + 2";

        calc.singleLineMath(input);
        double result = calc.calcs.get(calc.calcs.size() - 1);
        
        // verify that it matches
        Assert.assertEquals(5.5, result, 0.01);
    }

}