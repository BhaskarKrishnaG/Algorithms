package Homework7; /**
 * Homework7.TestBST.java
 *
 * Version ID: 1.0
 *
 * Revision ID: 1.0
 */

/**
 * Homework 7.2
 * This class will OrganizedThreads the operations of Homework7.Homework10.SortedStorage and Homework7.BinaryNode class
 *
 * @author Bhaskar Krishna Gangadhar
 */
public class TestBST {

    /**
     * Helper method to print error text
     *
     * @param message that caused the error
     */
    private static void error( String message ) {
        System.err.println(message);
    }
    /**
     * Method to OrganizedThreads all operations and behaviour for Integer type storage
     * @param storage object
     */
    private static void test( StorageInterface<Integer> storage ) {
        storage.add( 2 );
        storage.add( 2 );
        storage.add( 1 );
        storage.add( 3 );
        storage.add( null );
        System.out.println( "Initial BST" );
        System.out.println( storage );
        if ( !storage.includesNull() ) {
            error( "Missing null object" );
        }
        if ( !storage.find(1) ) {
            error( "Missing elements" );
        }
        if ( storage.find(4) ) {
            error( "Contains invalid element" );
        }
        if ( !storage.delete(null) ) {
            error( "Unable to delete" );
        }
        if ( storage.includesNull()) {
            error( "Contains invalid element" );
        }
        if ( !storage.delete(2) ) {
            error( "Unable to delete" );
        }
        if ( !storage.delete(2) ) {
            error( "Unable to delete" );
        }
        if ( storage.delete(2) ) {
            error( "Contains invalid element" );
        }
        System.out.println( "\nBST after deleting null, and two 2 elements" );
        System.out.println( storage );
    }

    /**
     * The main method
     *
     * @param args ignored
     */
    public static void main( String[] args ) {
        StorageInterface<Integer> storage = new SortedStorage<>();
        test( storage );
    }
}
