package Homework11; /**
 * Producer.java
 *
 * Version ID:
 *
 * Revision ID:
 */

/**
 * This class will create blue print for a producer which produces to a storage
 * class
 *
 * @author Bhaskar Krishna Gangadhar
 * @author Utkarsh sharma
 */
public class Producer extends Thread {
    private final int id;
    private final Storage storage;

    /**
     * Constructor
     *
     * @param id of the thread
     * @param storage class
     */
    public Producer( int id, Storage storage ) {
        this.id = id;
        this.storage = storage;
    }

    public void run() {
        storage.addItems( id, id );
    }
}
