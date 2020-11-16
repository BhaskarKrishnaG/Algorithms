package Homework11; /**
 * ProducerConsumer.java
 *
 * Version ID:
 *
 * Revision ID:
 */

/**
 * This class will simulate the behaviour of a thread safe consumer producer
 * program
 *
 * -consumer 4 -producer 3 -storageSize 5 -maxNumberOfManipulations 10
 *
 * @author Bhaskar Krishna Gangadhar
 * @author Utkarsh sharma
 */
public class ConsumerProducer {
    static int numOfConsumers = 4;
    static int numOfProducers = 3;
    static int manipulations = 100;
    static int storageCapacity = 5;

    /**
     * This method will create the required threads and starts them
     */
    private static void start() {
        Storage s = new Storage( storageCapacity , manipulations);
        Producer[] p = new Producer[ numOfProducers + 1 ];
        for ( int i = 1; i <= numOfProducers; i++ ) {
            p[i] = new Producer(i, s);
            p[i].start();
        }
        Consumer[] c = new Consumer[ numOfConsumers + 1];
        for ( int i = 1; i <= numOfConsumers; i++) {
            c[i] = new Consumer(i, s);
            c[i].start();
        }
    }

    private static void checkArguments() {
        if ( numOfProducers < 1 || numOfConsumers < 1 || storageCapacity < 1
                || manipulations < 1 ) {
            System.out.println( "Please enter valid arguments(greater than 1)" );
            System.exit(0);
        }
    }
    /**
     * Main method
     *
     * @param args command line arguments
     */
    public static void main( String[] args ) {
        if ( args.length == 8 ) {
            for (int i = 0; i < 8; i += 2 ) {
                if (args[i].equals( "-consumer" ) ) {
                    numOfConsumers = Integer.parseInt( args[ i + 1 ] );
                }
                else if ( args[i].equals( "-producer" ) ) {
                    numOfProducers = Integer.parseInt( args[ i + 1 ] );
                }
                if (args[i].equals( "-storageSize" ) ) {
                    storageCapacity = Integer.parseInt( args[ i + 1 ] );
                }
                if (args[i].equals("-maxNumberOfManipulations") ) {
                    manipulations = Integer.parseInt( args[ i + 1 ] );
                }
            }
        }
        checkArguments();
        start();
    }
}
