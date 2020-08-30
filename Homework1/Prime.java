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

    // This variable will hold the range of numbers.
    final static int MIN_NUMBER = -10;
    final static int MAX_NUMBER = 100;

    /**
     * This method will check if the number is prime.
     *
     * @param num an integer.
     * @return - True if the number is prime.
     *         - False if the number is not prime.
     */
    static boolean isPrime( int num ) {

        if ( num == 1 ) {
            return false;
        }

        for ( int divider = 2; divider <= Math.sqrt( num ); divider++ ) {
            if ( num % divider == 0 ) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method will evaluate if the first digit of the number is prime.
     *
     * @param digit an integer.
     * @return - True if the first digit of the number is prime.
     *         - False if it's not prime.
     */
    static boolean isFirstDigitPrime( int digit ) {

        // Divide by 10 until we get the 1st digit.
        while ( digit > 10 ) {
            digit = digit / 10;
        }
        return isPrime( digit );
    }

    /**
     * This method will test for all the required properties.
     *
     * @param num an integer.
     * @return - True if the number satisfies all the properties.
     *         - False otherwise.
     */
    static boolean hasProperties( int num ) {

        // If the MIN_NUMBER variable is changed to negative
        // this will take care of it.
        if ( num <= 0 ) {
            return false;
        }

        // If the first digit is not prime then
        // there is no need to test for the properties.
        if ( isFirstDigitPrime( num ) ) {
            boolean primeNumber = true;
            while ( num > 10 ) {
                if ( isPrime( num ) ) {
                    num = num / 10;
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

        for ( int num = MIN_NUMBER; num <= MAX_NUMBER; num++ ) {
            if ( hasProperties( num ) ) {
                System.out.println( num + " has the properties.");
            }
        }
    }
}