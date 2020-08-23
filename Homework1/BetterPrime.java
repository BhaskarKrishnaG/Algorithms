package Homework1;
/**
 * Prime.java
 *
 * Version:
 *
 * Revisions:
 *
 */

/**
 * This program print all the numbers which satisfy below properties:
 *     a. The number is prime.
 *     b. Remove the right most digit and the number is still prime.
 *     c. Repeat step b until the last digit is also prime.
 *
 * @author    Bhaskar Krishna Gangadhar
 */
public class BetterPrime {

    //This variable will hold the upper limit
    final static int MIN_NUMBER = -10;
    final static int MAX_NUMBER = 100;

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
     * This method will evaluate if the first digit of the number is prime.
     *
     * @param d an integer.
     *
     * @return True if the first digit of the number is prime.
     *         False if it's not prime.
     */
    static boolean isFirstDigitPrime( int d ) {

        // Divide by 10 until we get the 1st digit.
        while ( d > 10 ) {
            d = d/10;
        }
        return isPrime( d );
    }

    static boolean hasProperties( int i ) {

        if ( i <= 0) {
            return false;
        }

        // If the first digit is not prime then
        // there is no need to test for the properties.
        if( isFirstDigitPrime( i ) ) {
            int temp = i;
            boolean primeNumber = true;
            while ( temp > 10 ) {
                if ( isPrime( temp ) ) {
                    temp = temp / 10;
                }
                else {
                    primeNumber = false;
                    break;
                }
            }
            return primeNumber;
        }
        return false;
    }

    /**
     * The main method, starting point of the program.
     *
     * @param args command line arguments.
     */
    public static void main( String[] args ) {

        // We don't need to check the property for even numbers
        // so increment the loop by 2.
        for ( int i = MIN_NUMBER; i <= MAX_NUMBER; i++ ) {
            if( hasProperties( i ) ) {
                System.out.println( i + " has the properties.");
            }
        }
    }
}