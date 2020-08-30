package Homework1;
/**
 * Coins.java
 *
 * Version: 1.0
 *
 * Revisions: 1.0
 *            - Previous version was not considering the last elements to be in
 *              a group and add the elements from the beginning to that set.
 *            - The main changes are in tryAllCombination where I am advancing
 *              the index to one more position to the right and the inner loop
 *              which is adding the one coin to the set is smart enough to start
 *              from the next coin or the beginning coin.
 *
 */

/**
 * This program will check if we can pay the cashier the desired amount
 * of money with the provided coins.
 * - If we can't, then we'll print that we can't.
 * - Otherwise, we'll print the maximum number of coins that can be
 *   used from the pocket and their values.
 *
 * @author Bhaskar Krishna Gangadhar
 */
public class Coins {
    static int[] coins = {1, 1, 1, 1, 5};
    static int[] toPay = {8};

    /**
     * Sort the coins (Insertion sort)
     *
     * @param  coins array of unsorted coins.
     */
    static void sortCoins( int[] coins ) {
        int length = coins.length;
        for ( int index = 1 ; index < length; index++ ) {
            int j = index - 1;
            int key = coins[ index ];
            while ( j >= 0 && coins[ j ] < key ) {
                coins[ j + 1] = coins[ j ];
                --j;
            }
            coins[ j + 1 ] = key;
        }
    }

    /**
     * Print the coins in desired format.
     *
     * @param i          starting of the set of coins.
     * @param j          the single coin.
     * @param numOfCoins number of coins used to pay.
     */
    static void printCoins( int i, int j, int numOfCoins, int amount ) {
        System.out.print( amount + " cents: \t\tyes; used coins = " );
        while ( ( numOfCoins - 1 ) > 0 ) {
            System.out.print( coins[ i ] +" cents " );
            i++;
            numOfCoins--;
        }
        System.out.print( coins[ j ] + " cents\n" );
    }

    /**
     * This method will compute all possible combinations of coins
     * for the given number of coins.
     *
     * @param noOfCombinations size of our set.
     * @param amount           amount to be paid.
     */
    static boolean tryAllCombinations( int noOfCombinations, int amount ) {
        for ( int index = 0; index <= coins.length - noOfCombinations + 1; index++ ) {
            int set = noOfCombinations - 2;
            int sum = 0;
            while ( set >= 0 ) {
                sum += coins[ index + set ];
                --set;
            }
            int j = ( index + noOfCombinations -1 ) % coins.length;
            int upperBound = j == 0 ? index : coins.length - 1;
            for ( int k = j ; k < upperBound; k++ ) {
                if ( sum + coins[ k ] == amount ) {
                    printCoins( index, k, noOfCombinations, amount );
                    return true;
                }
            }

        }
        return false;
    }

    /**
     * This method will check if we can pay the amount
     *
     * @param amount The amount to be paid.
     */
    static void checkIfWeCanPay( int amount ) {

        // We will try to pay using the most number of coins
        // and work our way to the least.
        for ( int combination = coins.length; combination > 0; combination-- ) {
            if  ( tryAllCombinations( combination, amount ) ) {
                return;
            }
        }

        // If none of the combinations work then print that we can't pay.
        System.out.print( amount + " cents: \t\tno; can not be paid " +
                "with the following sequence of coins: [" );
        for ( int index = 0; index < coins.length; index++ ) {
            if ( index != coins.length - 1 ) {
                System.out.print( coins[index] + ", " );
            }
            else {
                System.out.print( coins[index] + "]\n" );
            }
        }
    }

    /**
     * The main method, control flows from here.
     *
     * @param  args command line argument.
     */
    public static void main( String[] args ) {
        sortCoins( coins );
        for ( int index = 0; index < toPay.length; index++ ) {
            if ( toPay[ index ] == 0 ) {
                System.out.println("0 Cents: \t\tcan not be paid;");
            } else {
                checkIfWeCanPay( toPay[ index ] );
            }
        }
    }
}
