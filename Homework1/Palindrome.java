package Homework1;

/**
 * Palindrome.java
 *
 * Version: 1.0
 *
 * Revision:0.0
 */

/**
 * This program will check if numbers within the given range to check if they
 * are Lychrel number(is a natural number that cannot form a palindrome through
 * the iterative process of repeatedly reversing it's digits and adding it's
 * digits) of not.
 *
 * @author Bhaskar Krishna Gangadhar
 */
public class Palindrome {
    static final int START = 69;
    static final int END = 91;
    static final int MAX_DELAY = 3;

    /**
     * This method will print the result in desired format.
     *
     * @param number The current number.
     * @param delay  It's delay qutient.
     */
    static void print( int num, StringBuilder previousNumber,
                       StringBuilder number, int delay ) {
        if ( delay > MAX_DELAY ) {
            System.out.printf( "%d\t delayed %d :  does not become palindromic"
                    + " within %d iterations  (%s + %s = %s: %s != %s)%n", num,
                    --delay, delay, previousNumber, previousNumber.reverse(),
                    number, number, number.reverse());
        }
        else {
            System.out.printf( "%d\t delayed %d :  %-4s+   %-4s=   %-4s%n",
                    num, delay, previousNumber, previousNumber.reverse(),
                    number);
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
        int num = Integer.parseInt( number.toString() );
        int reverse = Integer.parseInt( number.reverse().toString() );

        return new StringBuilder( String.valueOf( num + reverse ));
    }

    /**
     * This method will check if the given number has the property.
     *
     * @param num the integer we are trying to test.
     */
    static void checkIfLychrelNumber( int num ) {
        StringBuilder number = new StringBuilder( String.valueOf( num ) );
        StringBuilder previousNumber = new StringBuilder("");
        for( int delay = 1; delay <= MAX_DELAY; delay++ ) {
            previousNumber = number;
            number = addNumberAndReverse( number );
            if ( number.toString().equals( number.reverse().toString()) ) {
                print( num, previousNumber, number.reverse(), delay );
                return;
            }
            number.reverse();
        }
        print( num, previousNumber, number, MAX_DELAY + 1 );
    }

    /**
     * The main function.
     *
     * @param args command line arguments.
     */
    public static void main( String[] args ) {
        if ( MAX_DELAY < 1 || START < 1 ) {
            return;
        }

        for( int number = START; number <= END; number++ ) {
            checkIfLychrelNumber( number );
        }
    }
}
