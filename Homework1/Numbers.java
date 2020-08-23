package Homework1;
/**
 * Numbers.java
 *
 * Version: 1.0
 *
 * Revisions: 0.0
 *
 */

/**
 * This program print all the numbers which satisfy below property:
 * - A number n exists such that, sum of each digit ^n is equal to the number.
 *
 * @author    Bhaskar Krishna Gangadhar
 * @author    Utkarsh Sharma
 */
public class Numbers {
    static final int MAX_NUMBER = 10000;

    /**
     * This method will print the solution in desired format.
     *
     * @param num         The integer that satisfied the property.
     * @param power       The power that satisfied the property.
     * @param digitArray  The character array of the integer.
     */
    static void print( int num, int power, char[] digitArray ) {
        System.out.println( num + "  ==        " + num + " has the desired properties." );
        System.out.print( " " );
        for ( char i : digitArray ) {
            System.out.print( " " + i + " ^ " + power );
        }
        System.out.print( "\n" );
    }

    /**
     * This method converts the given number into an array of digits.
     *
     * @param    num integer value.
     * @return   a character array.
     */
    static char[] arrayOfDigits( int num ) {
        String number = String.valueOf( num );
        return number.toCharArray();
    }

    /**
     * This method evaluates the elements of the given character array.
     *
     * @param   digitArray character array.
     * @return  - True of the array contains only 1's and 0's.
     *          - False if it contains any other element.
     */
    static boolean onlyBinaryDigits( char[] digitArray ) {
        for ( char i : digitArray ) {

            // If the element is not 1 or 0 return false.
            if ( !( i == '1' || i  == '0' ) ) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks for the required property.
     *
     * @param i number.
     */
    static void checkForProperties( int i ) {
        int power = 1;
        int sum;
        int numberOfDigits = Integer.toString( i ).length();

        // We will convert the number into array of characters
        // to be able to access all the individual digits easily.
        char[] digitArray = arrayOfDigits( i );

        // Check if the number has only 1's and 0's
        // We can skip this number because 1^n is always 1
        // and 0^n is always 0, sum of their powers will never satisfy.
        if ( numberOfDigits > 1 && onlyBinaryDigits( digitArray ) ) {
            return;
        }
        do {
            sum =0;

            // Compute the sum of powers of each digit.
            for (int n = 0; n < numberOfDigits; ++n) {
                sum += Math.pow( Character.getNumericValue( digitArray[ n ] ), power );
            }

            if (sum == i) {
                print(i, power, digitArray);
                break;
            }
            power++;
        } while ( sum < i ); // If the sum exceeds number then increasing
        // the power will only increase the sum.
    }

    /**
     * This main method, execution begins from here.
     *
     * @param args command line arguments.
     */
    public static void main( String[] args ) {

        for ( int i = 1 ; i < MAX_NUMBER ; ++i ) {
            checkForProperties( i );
        }
    }
}
