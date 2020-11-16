package Homework7; /**
 * Homework7.StorageInterface.java
 *
 * Version ID: 1.0
 *
 * Revision ID: 1.0
 */

/**
 * Homework 7.2
 *
 * This an interface definition for a storage structure that can store
 * any type of element
 * @param <E> Type of the element to be stored
 *
 * @author Bhaskar Krishna Gangadhar
 */
public interface StorageInterface<E extends Comparable<E>> {
    boolean add( E x );
    boolean find( E x );
    boolean includesNull();
    boolean delete(E x);

}
