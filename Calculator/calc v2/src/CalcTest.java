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
    public void testvalidateInputs0() {
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
    public void testvalidateInputs1() {
        // initialize calc
        Calc calc = new Calc();
        
        double invalid = Double.MIN_VALUE;
        String input = String.valueOf(invalid);
        // validate input
        boolean validInputs = calc.validateInputs(input);

        // verify that it matches
        Assert.assertFalse(validInputs);
    }

    @Test
    public void testvalidateInputs2() {
        // initialize calc
        Calc calc = new Calc();
        
        String input = String.valueOf("10000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.0");
        // validate input
        boolean validInputs = calc.validateInputs(input);

        // verify that it matches
        Assert.assertFalse(validInputs);
    }

    @Test
    public void testvalidateInputs3() {
        // initialize calc
        Calc calc = new Calc();
        
        String input = String.valueOf("what 2");
        // validate input
        boolean validInputs = calc.validateInputs(input);

        // verify that it matches
        Assert.assertFalse(validInputs);
    }

    @Test
    public void testvalidateInputs4() {
        // initialize calc
        Calc calc = new Calc();
        
        String input = String.valueOf("2 what");
        // validate input
        boolean validInputs = calc.validateInputs(input);

        // verify that it matches
        Assert.assertFalse(validInputs);
    }

    @Test
    public void testvalidateInputs5() {
        // initialize calc
        Calc calc = new Calc();
        
        String input = String.valueOf("1.2.3.4");
        // validate input
        boolean validInputs = calc.validateInputs(input);

        // verify that it matches
        Assert.assertFalse(validInputs);
    }

    @Test
    public void testvalidateInputs6() {
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

}