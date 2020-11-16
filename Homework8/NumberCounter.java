package Homework8; /**
 * NumberCounter.java
 *
 * Version ID:
 *
 * Revision ID:
 */

import java.io.*;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

/**
 *
 * This class will accept a lottery distribution data and will compute
 * the mode of each value and print the result
 *
 * @author Bhaskar Krishna Gangadhar
 */
public class NumberCounter {

    private final String fileName;
    private final int[] lottery;

    /**
     * Parameterised constructor which sets the filename
     *
     * @param fileName of the lottery data
     */
    NumberCounter( String fileName ) {
        this.fileName = fileName;
        lottery = new int[81];
    }

    /**
     * This method will read the file and process the numbers that were called
     * out through out the year and calculate the mode of each number.
     */
    public void readFile() {
        int number;
        try (
            BufferedReader input = fileName != null ? ( fileName.contains(".gz") ?
                new BufferedReader( new InputStreamReader(
                    new GZIPInputStream( new FileInputStream( fileName ) ) ) ) :
                new BufferedReader( new FileReader( fileName ) ) ) :
                new BufferedReader(new InputStreamReader( System.in ) )
        ) {
            String line;
            while ( ( line = input.readLine() ) != null ) {
                String[] numbers = line.split(",");
                Scanner scanner = new Scanner( numbers[1] );
                while ( scanner.hasNextInt() ) {
                    number = scanner.nextInt();
                    lottery[number] += 1;
                }
            }
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Prints the mode in the required form
     */
    public void printResult() {
        for ( int i = 1; i < lottery.length; i += 4 ) {
            System.out.printf( "%2d: %8d%8d: %8d%8d: %8d%8d: %8d\n"
                    ,i, lottery[i], i+1,lottery[i+1], i+2, lottery[i+2]
                    , i+3, lottery[i+3]);
        }
    }

    /**
     * The main method
     *
     * @param args ignored
     */
    public static void main( String[] args ) {
        NumberCounter num = new NumberCounter( args.length == 1 ? args[0] :
                                                null );
        num.readFile();
        num.printResult();
    }
}
