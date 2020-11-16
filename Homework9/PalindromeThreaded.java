package Homework9; /**
 * PalindromeThreaded.java
 *
 * Version ID:
 *
 * Revision ID:
 */

/**
 * 9.1
 *
 * This program in parallel check for lychre property of a number
 * It creates one thread per number
 *
 * @author Bhaskar Krishna Gangadhar
 */
public class PalindromeThreaded extends Thread {
    static final int START = 78;
    static final int END = 88;
    static final int MAX_DELAY = 10;
    private final int num;
    private final int arrIndex;
    private static String[] answer;

    public PalindromeThreaded( int num , int arrIndex ) {
        this.num = num;
        this.arrIndex = arrIndex;
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
                       StringBuilder lastNumber, int delay , int index) {
        int lastNumb = Integer.parseInt( lastNumber.toString() );
        StringBuilder prevNumberReversed = new StringBuilder( previousNumber ).
                reverse();
        String res;
        if ( delay > MAX_DELAY ) {
            res = num + ":\tdelayed " + --delay + ": does not become " +
                    "palindrome within " + delay + "iterations (" +
                    previousNumber + " + " + prevNumberReversed + " = " + lastNumb
                    + ": " + lastNumb + " != " + lastNumber.reverse();
            answer[index] = res;
        }
        else {
            res = num + ":\tdelayed " + delay + " : " + previousNumber + " + "
                    + prevNumberReversed + " = " + lastNumb;
            answer[index] = res;
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
    static void checkIfLychrelNumber( int num , int counter) {
        StringBuilder number = new StringBuilder( String.valueOf( num ) );
        String previousNumber = "";
        for( int delay = 1; delay <= MAX_DELAY; delay++ ) {
            previousNumber = number.toString();
            number = addNumberAndReverse( number );

            // Converting the StringBuilder back to String to use equals method
            if ( number.toString().equals( number.reverse().toString()) ) {
                number.reverse();
                print( num, previousNumber, number, delay, counter );
                return;
            }
            number.reverse();
        }
        print( num, previousNumber, number, MAX_DELAY + 1, counter );
    }

    public void run() {
        checkIfLychrelNumber( num , arrIndex);
    }

    /**
     * Prints the output
     */
    public static void printOutput() {
        for ( int i = 0; i < answer.length; i++) {
            System.out.println( answer[i] );
        }
    }

    /**
     * The main function.
     *
     * @param args command line arguments.
     */
    public static void main( String[] args ) {
        if ( MAX_DELAY < 1 || START < 1 || START > END ) {
            System.out.println("Invalid range");
            return;
        }
        answer = new String[END - START + 1];
        PalindromeThreaded[] pal = new PalindromeThreaded[ END - START + 1 ];
        int counter = 0;
        for( int number = START; number <= END; number++ ) {
            pal[counter] = new PalindromeThreaded( number , counter );
            pal[counter].start();
            counter++;
        }

        for (PalindromeThreaded palindromeThreaded : pal) {
            try {
                palindromeThreaded.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        printOutput();
    }
}
