package Homework1;
/**
 * Numbers.java
 *
 * Version:
 *
 * Revisions:
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
 * @author Utkarsh Sharma
 */
public class Coins {
    static int[] coins = {1, 1, 2, 25, 25, 5};
    static int[] toPay = {0, 1, 4, 5, 7, 8, 10};

    /**
     * Sort the coins (Insertion sort)
     *
     * @param  coins array of unsorted coins.
     */
    static void sortCoins(int[] coins ) {
        int length = coins.length;
        for ( int i = 1 ; i < length; i++) {
            int j = i - 1;
            int key = coins[ i ];
            while ( j >= 0 & coins[ j ] > key) {
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
        System.out.print( amount + " cents: \t\tyes; used coins = ");
        while ( ( numOfCoins - 1 ) >0 ){
            System.out.print( coins[ i ] +" cents ");
            i++;
            numOfCoins--;
        }
        System.out.print( coins[ j ] + " cents\n");
    }
    /**
     * This method will compute all possible combinations of coins
     * for the given number of coins.
     *
     * @param numOfCoins size of our set.
     * @param amount     amount to be paid.
     */
    static boolean tryAllCombinations( int numOfCoins, int amount) {
        for ( int i = 0; i <= coins.length - numOfCoins; i++ ) {
            int n = numOfCoins - 2;
            int sum = 0;
            while ( n >= 0 ) {
                sum += coins[ i + n ];
                --n;
            }
            for ( int j = i + numOfCoins -1; j < coins.length; j++) {
                if ( sum + coins[ j ] == amount ) {
                    printCoins( i, j, numOfCoins, amount);
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
        for ( int i = coins.length; i > 0; i--) {
            if(tryAllCombinations( i, amount )) {
                return;
            }
        }
        System.out.print( amount + " cents: \t\tno; can not be paid " +
                "with the following sequence of coins: [");
        for (int c: coins) {
            System.out.print( c + ", ");
        }
        System.out.print("]\n");
    }

    /**
     * The main method, control flows from here.
     *
     * @param  args command line argument.
     */
    public static void main( String[] args ) {
        sortCoins(coins);
        for ( int i = 0; i < toPay.length; i++ ) {
            if (toPay[i] == 0) {
                System.out.println("0 Cents: \t\tcan not be paid;");
            } else {
                checkIfWeCanPay(toPay[i]);
            }
        }
    }
}
