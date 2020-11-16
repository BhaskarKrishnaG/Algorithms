package Homework11; /**
 * Storage.java
 *
 * Version ID:
 *
 * Revision ID:
 */

/**
 * This class will define synchronized methods to add and remove from the
 * storage system
 *
 * @author Bhaskar Krishna Gangadhar
 * @author Utkarsh sharma
 */
public class Storage {
    static volatile int soManyObjectsInTheStorage = 0;
    static volatile int soManyManipulations;
    static int capacity;

    public Storage( int num , int times) {
        capacity = num;
        soManyManipulations = times;
    }

    /**
     * This method will increment the storage by the specified number if
     * the result does not exceed the capacity. If it does then the thread
     * will go for wait giving the other threads a chance
     *
     * @param id of the thread
     * @param theseMany increment value
     */
    public synchronized void addItems( int id, int theseMany ) {
        while ( soManyManipulations > 0 ) {
            while ( soManyObjectsInTheStorage + theseMany > capacity ) {
                System.out.println( "Producer " +id + " entered wait");
                try {
                    wait();
                } catch ( InterruptedException e ) {
                    e.printStackTrace();
                }
            }

            soManyObjectsInTheStorage += theseMany;
            System.out.printf("Producer %d added %d items: %d <-- so many items" +
                    " in the storage\n", id, id, soManyObjectsInTheStorage );
            soManyManipulations--;
            if ( soManyManipulations == 0 ) {
                System.out.println("We have reached the limit of manipulations");
                System.exit(0);
            }
            notifyAll();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method will decrement the storage by the specified number if
     * the result does not go below 0. If it does then the thread
     * will go for wait giving the other threads a chance
     *
     * @param id of the thread
     * @param theseMany decrement value
     */
    public synchronized void consumeItems( int id, int theseMany ) {
        while ( soManyManipulations > 0 ) {
            while (soManyObjectsInTheStorage - theseMany < 0) {
                System.out.println( "Consumer " +id + " entered wait");
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            soManyObjectsInTheStorage -= theseMany;
            System.out.printf("Consumer %d consumed %d items: %d <-- these many left" +
                    " in the storage\n", id, id, soManyObjectsInTheStorage);
            soManyManipulations--;
            if ( soManyManipulations == 0 ) {
                System.out.println("We have reached the limit of manipulations");
                System.exit(0);
            }
            notifyAll();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
