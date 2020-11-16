package Homework11; /**
 * Consumer.java
 *
 * Version ID:
 *
 * Revision ID:
 */

/**
 * This class will create blue print for a consumer which consumes from a
 * storage class
 *
 * @author Bhaskar Krishna Gangadhar
 * @author Utkarsh sharma
 */
public class Consumer extends Thread {
    private final int id;
    private final Storage storage;

    /**
     * Constructor
     *
     * @param id of the thread
     * @param storage class
     */
    public Consumer(int id, Storage storage) {
        this.id = id;
        this.storage = storage;
    }

    public void run() {
        storage.consumeItems( id, id );
    }
}
