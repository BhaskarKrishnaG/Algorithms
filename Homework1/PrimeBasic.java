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
 * This program prints all the numbers which satisfy below properties:
 *     a. The number is prime.
 *     b. Remove the right most digit and the number is still prime.
 *     c. Repeat step 'b' until the last digit is also prime.
 *
 * @author    Bhaskar Krishna Gangadhar
 * @author    Utkarsh Sharma
 */
public class PrimeBasic {

    //These variable will determine the range of our numbers.
    final static int MIN_NUMBER = 3;
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
     * @param num an integer
     * @return - True if the number satisfies all the properties.
     *         - False otherwise.
     */
    static boolean hasProperties( int num ) {
        boolean primeNumber = true;
        if ( num <= 0) {
            return false;
        }
        while ( num > 0 ) {
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