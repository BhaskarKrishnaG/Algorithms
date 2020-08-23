package Homework1;
/**
 * Prime.java
 *
 * Version: 1.0
 *
 * Revisions: 0.0
 *
 */

/**
 * This program print all the numbers which satisfy below properties:
 *     a. The number is prime.
 *     b. Remove the right most digit and the number is still prime.
 *     c. Repeat step b until the last digit is also prime.
 *
 * @author    Bhaskar Krishna Gangadhar
 * @author    Utkarsh Sharma
 */
public class Prime {

    //These variable will determine the range of our numbers.
    final static int MIN_NUMBER = 0;
    final static int MAX_NUMBER = 1000;

    /**
     * This method will check if the number is prime.
     *
     * @param p an integer.
     *
     * @return True if the number is prime.
     *         False if the number is not prime.
     */
    static boolean isPrime( int p ) {
        if ( p == 1 ) {
            return false;
        }
        for ( int n = 2; n <= Math.sqrt( p ); n++ ) {
            if ( p % n == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method will test for all the required properties.
     *
     * @param n an integer
     * @return - True if the number satisfies all the properties.
     *         - False otherwise.
     */
    static boolean hasProperties( int n ) {
        boolean primeNumber = true;
        if ( n <= 0) {
            return false;
        }
        while ( n > 0 ) {
            if ( isPrime( n ) ) {
                n = n / 10;
            }
            else {
                primeNumber = false;
                break;
            }
        }
        return primeNumber;
    }

    /**
     * The main method, starting point of the program.
     *
     * @param args command line arguments.
     */
    public static void main( String[] args ) {
        for ( int i = MIN_NUMBER; i <= MAX_NUMBER; i++ ) {
            if ( hasProperties( i ) ) {
                System.out.println( i + " has the properties.");
            }
        }
    }
}