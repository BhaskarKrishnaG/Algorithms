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
 *            2.0
 *            - Now we are also considering the possibility that the cashier
 *              also has coins with him and he can give back change to us.
 *            - With this possibility our goal now is have the least number of
 *              coins with us at the end of the exchange of coins.
 */

import java.util.Arrays;

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
    static int[] coins = {1, 1, 2, 5, 25, 25, 25};
    static int[] cashiersCoins = { 2 };
    static int[] toPay = {0, 1, 4, 5, 7, 8};
    static int[] allCoins;

    /**
     * Sort the coins (Insertion sort)
     *
     * @param  allCoins array of unsorted coins.
     */
    static void sortCoins( int[] allCoins ) {
        int length = allCoins.length;
        for ( int index = 1 ; index < length; index++ ) {
            int j = index - 1;
            int key = allCoins[ index ];
            while ( j >= 0 && allCoins[ j ] < key ) {
                allCoins[ j + 1] = allCoins[ j ];
                --j;
            }
            allCoins[ j + 1 ] = key;
        }
    }

    /**
     * This method will add the coins the cashier has to our pocket but as
     * negative coins.
     */
    static void addCashierCoinsToPocket() {
        allCoins = new int[ coins.length + cashiersCoins.length ];
        for ( int index = 0; index < coins.length; index++ ) {
            allCoins[ index ] = coins[ index ];
        }
        for ( int index = 0; index < cashiersCoins.length; index++ ) {
            allCoins[ coins.length + index ] = -cashiersCoins[ index ];
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
        System.out.printf( "%d cents: \tyes; I gave the cashier the following " +
                "coins ", amount );
        String message = "\t\t\t\t and the cashier gave me ";
        while ( ( numOfCoins - 1 ) > 0 ) {
            if ( allCoins[ i ] < 0 ) {
                message = message.concat( -allCoins[ i ] + " cents " );
            }
            else {
                System.out.printf("%d cents ", allCoins[i]);
            }
            i++;
            numOfCoins--;
        }
        if ( allCoins[ j ] < 0 ) {
            message = message.concat( -allCoins[ j ] + " cents" );
        }
        System.out.printf( "%d cents\n",allCoins[j] );
        if ( message.length() > 29) {
            System.out.println(message);
        }
    }

    /**
     * This method will compute all possible combinations of coins
     * for the given number of coins.
     *
     * @param noOfCombinations size of our set.
     * @param amount           amount to be paid.
     */
    static boolean tryAllCombinations( int noOfCombinations, int amount ) {
        for ( int index = 0; index <= allCoins.length - noOfCombinations + 1; index++ ) {
            int set = noOfCombinations - 2;
            int sum = 0;
            while ( set >= 0 ) {
                sum += allCoins[ index + set ];
                --set;
            }
            int j = ( index + noOfCombinations -1 ) % allCoins.length;
            int upperBound = j == 0 ? index : allCoins.length - 1;
            for ( int k = j ; k < upperBound; k++ ) {
                if ( sum + allCoins[ k ] == amount ) {
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
            if ( index != allCoins.length - 1 ) {
                System.out.print( allCoins[index] + ", " );
            }
            else {
                System.out.print( allCoins[index] + "]\n" );
            }
        }
    }

    /**
     * The main method, control flows from here.
     *
     * @param  args command line argument.
     */
    public static void main( String[] args ) {
        addCashierCoinsToPocket();
        sortCoins( allCoins );
        for ( int index = 0; index < toPay.length; index++ ) {
            if ( toPay[ index ] == 0 ) {
                System.out.println("0 Cents: \t\tcan not be paid;");
            } else {
                checkIfWeCanPay( toPay[ index ] );
            }
        }
    }
}
