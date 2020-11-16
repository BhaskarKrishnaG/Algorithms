package Homework12; /**
 * Philosopher.java
 *
 * Version ID:
 *
 * Revision ID:
 */

import java.util.Random;

/**
 * This program will demonstrate a deadlock-free solution to dinning philosopher
 * problem.
 *
 * @author Professor HP
 * @author Bhaskar Krishna Gangadhar
 */
public class Philosopher extends Thread {

    protected static Random random = new Random();
    protected int me;
    protected Integer left, right;

    /**
     * Constructor
     * @param me id
     * @param left fork id
     * @param right fork id
     */
    public Philosopher ( int me, Integer left, Integer right ) {
        this.me = me;
        this.left = left;
        this.right = right;
    }

    public void run () {
        while ( true ) {
            System.out.println( me+" thinks" );

            try {
                Thread.sleep( ( long )( random.nextFloat()*1000 ) );
            }
            catch( Exception e ) {
            }

            // Every thread except for the last one picks up right fork and
            // then the left one. This will break the circular wait.
            // We can even say evey odd thread will enter if and even will go else
            if  ( me != 3 ) {
                synchronized ( right ) {
                    System.out.println( "\t" + me + " takes right fork" );
                    synchronized ( left )  {
                        System.out.println( "\t" + me + " takes left fork" );
                        System.out.println("\t\t" + me + " eats");
                        try {
                            Thread.sleep( ( long )( random.nextFloat()*1000 ) );
                        }
                        catch(Exception e) {

                        }
                    }
                }
            }
            else {
                synchronized ( left ) {
                    System.out.println("\t" + me + " takes left fork");
                    synchronized ( right )  {
                        System.out.println("\t" + me + " takes right fork");
                        System.out.println("\t\t" + me + " eats");
                        try {
                            Thread.sleep( ( long )( random.nextFloat()*1000 ) );
                        }
                        catch( Exception e ) {

                        }
                    }
                }
            }
        }
    }

    /**
     * main method
     * @param args ignored
     */
    public static void main ( String[] args ) {
        Integer[] forks = new Integer[5];
        for (int n = 0; n <= 3; ++ n)
            forks[n] = new Integer(n);
        Philosopher[] thePhilosophers = new Philosopher[4];
        thePhilosophers[0] = new Philosopher(0, forks[3], forks[0]);
        for (int n = 1; n <= 3; ++ n)
            thePhilosophers[n] = new Philosopher(n, forks[n-1], forks[n]);
        for (int n = 0; n < 4; ++ n) thePhilosophers[n].start();
    }
}