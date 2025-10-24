// Needed to read console input through DataInputStreamClass
import java.io.*;

public class Main {
    // run the calc functions here
    boolean exit = false;
    int result = 0;
    while(exit == false)
    {
        DataInputStream r = new DataInputStream(System.in);

        // could extend to > 2 vars by asking number of digits and using a for loop
        System.out.print("Enter a number (decimals are allowed): ");
        float a = float.parseFloat(r.readLine());
        
        System.out.print("Enter a number (decimals are allowed): ");
        float b = float.parseFloat(r.readLine());

        System.out.print("What operations would you like to perform (Add): ");
        String operation = r.readLine();

        if(operation.equalsIgnoreCase("add") | operation.equals("+")) {
            result = add(a, b);
        }
        
        System.out.println(result);
    }
}

// add two variables
public class add(float x, float y) {
    return x + y;
}