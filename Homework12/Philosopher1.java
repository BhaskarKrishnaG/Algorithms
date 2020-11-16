package Homework12; /**
 * Philosopher1.java
 *
 * Version ID:
 *
 * Revision ID:
 */
import java.util.Random;

/**
 * This program will demonstrate a deadlock-free solution to dinning philosopher
 * problem.
 * In this solution we are enforcing an order, P3 will eat first then P2, P1 & P0
 *
 * @author Professor HP
 * @author Bhaskar Krishna Gangadhar
 */
public class Philosopher1 extends Thread {

    protected static Random random = new Random();
    protected int me;
    protected Integer left, right;

    /**
     * Constructor
     * @param me id
     * @param left fork id
     * @param right fork id
     */
    public Philosopher1 ( int me, Integer left, Integer right ) {
        this.me = me;
        this.left = left;
        this.right = right;
    }

    /**
     * We are enforcing an order to prevent
     */
    public void run () {
        while ( true ) {
            System.out.println(me + " thinks");

            synchronized ( right ) {
                System.out.println("\t" + me + " takes right fork");
                synchronized ( left ) {
                    left.notify();
                    System.out.println("\t" + me + " takes left fork");
                    System.out.println("\t\t" + me + " eats");
                    try {
                        Thread.sleep((long) (random.nextFloat() * 1000));
                    } catch (Exception e) {

                    }
                }
                try {
                    right.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
        Philosopher1[] thePhilosophers = new Philosopher1[4];
        thePhilosophers[0] = new Philosopher1(0, forks[3], forks[0]);
        for (int n = 1; n <= 3; ++ n)
            thePhilosophers[n] = new Philosopher1(n, forks[n-1], forks[n]);
        for (int n = 3; n >= 0; n--) {
            thePhilosophers[n].start();
            try {
                Thread.sleep( 1000  );
            }
            catch( Exception e ) {
            }
        }
    }
}