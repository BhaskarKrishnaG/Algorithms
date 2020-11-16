package Homework9; /**
 * PalindromeThreaded2.java
 *
 * Version ID:
 *
 * Revision ID:
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 9.3
 *
 * This program in parallel check for lychre property of a number
 * User has to provide a command line argument for how many threads to be
 * created
 * We will print only numbers that become palindrome between min and max delay
 *
 * @author Bhaskar Krishna Gangadhar
 */
public class PalindromeThreaded2 extends Thread {
    static final int START = 78;
    static final int END = 88;
    static final int MIN_DELAY = 3;
    static final int MAX_DELAY = 10;
    private int threadStart;
    private int threadEnd;
    public static List<Result> theResults =
            Collections.synchronizedList(new ArrayList<Result>());

    /**
     * Constructor for class
     * @param start of the number
     * @param end of the number
     */
    public PalindromeThreaded2(int start, int end  ) {
        threadStart = start;
        threadEnd = end;
    }

    /**
     * This method will print the result in desired format.
     *
     * @param num            The number who's property is being tested.
     * @param previousNumber Previously tested number.
     * @param lastNumber     Homework10.Result of the last iteration.
     * @param delay          The delay.
     */
    static void print( int num, String previousNumber,
                       StringBuilder lastNumber, int delay ) {
        int lastNumb = Integer.parseInt( lastNumber.toString() );
        StringBuilder prevNumberReversed = new StringBuilder( previousNumber ).
                reverse();
        String res;
        if ( delay > MAX_DELAY ) {
            res = num + ":\tdelayed " + --delay + ": does not become " +
                    "palindrome within " + delay + "iterations (" +
                    previousNumber + " + " + prevNumberReversed + " = " + lastNumb
                    + ": " + lastNumb + " != " + lastNumber.reverse();
            theResults.add( new Result( num , res) );
        }
        else {
            res = num + ":\tdelayed " + delay + " : " + previousNumber + " + "
                    + prevNumberReversed + " = " + lastNumb;
            theResults.add( new Result( num , res) );
        }
    }

    /**
     * This method will add the number with it's reverse and send back the
     * result in a StringBuilder object.
     *
     * @param   number StringBuilder representation of an Integer.
     * @return  addition of two numbers put in a StringBuilder.
     */
    static StringBuilder addNumberAndReverse( StringBuilder number) {

        // Converting the StringBuilder to Integer to  add them.
        int num = Integer.parseInt( number.toString() );
        int reverse = Integer.parseInt( number.reverse().toString() );

        return new StringBuilder( String.valueOf( num + reverse ) );
    }

    /**
     * This method will check if the given number has the property.
     *
     * @param num the integer we are trying to OrganizedThreads.
     */
    static void checkIfLychrelNumber( int num ) {
        StringBuilder number = new StringBuilder( String.valueOf( num ) );
        String previousNumber = "";
        for( int delay = 1; delay <= MAX_DELAY; delay++ ) {
            previousNumber = number.toString();
            number = addNumberAndReverse( number );

            // Converting the StringBuilder back to String to use equals method
            if ( number.toString().equals( number.reverse().toString()) ) {
                if ( delay < MIN_DELAY ) {
                    return;
                }
                number.reverse();
                print( num, previousNumber, number, delay );
                return;
            }
            number.reverse();
        }
        print( num, previousNumber, number, MAX_DELAY + 1 );
    }

    /**
     * Implmenting the starting point of the thread
     */
    public void run() {
        while ( threadEnd > 0 && threadStart <= END) {
            checkIfLychrelNumber(threadStart);
            threadStart++;
            threadEnd--;
        }
    }

    /**
     * Prints the output in sorted format
     */
    public static void printOutput() {
        Collections.sort( theResults );
        System.out.println( theResults );
    }

    /**
     * This method will create threads and start them and join the main thread
     * till all the threads are done computing
     *
     * @param numThread to create
     */
    private static void compute( float numThread ) {
        if ( MAX_DELAY < 1 || START < 1 || START > END || MIN_DELAY > MAX_DELAY ) {
            System.out.println("Please check the range");
            return;
        }
        PalindromeThreaded2[] pal = new PalindromeThreaded2[ (int) numThread ];
        int start = START;
        int ending = (int) Math.ceil( (END - START + 1) /numThread);
        for( int i = 0; i < numThread; i++ ) {
            pal[i] = new PalindromeThreaded2( start, ending  );
            pal[i].start();
            start = start + ending;
        }

        for ( PalindromeThreaded2 palindrome : pal) {
            try {
                palindrome.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        printOutput();
    }

    /**
     * The main function.
     *
     * @param args command line arguments.
     */
    public static void main( String[] args ) {
        if ( args.length != 1 ) {
            System.out.println( "Please enter number of parallel threads");
            System.exit(1);
        }
        try {
            if (Integer.parseInt(args[0]) < 1) {
                System.out.println(" Thread count has to be greater than 1 ");
                System.exit(1);
            }
        }
        catch ( NumberFormatException e ) {
            System.out.println("Please enter a valid number");
            System.exit(1);
        }
        compute( Float.parseFloat( args[0] ) );
    }
}
