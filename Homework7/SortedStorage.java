package Homework7; /**
 * Homework7.Homework10.SortedStorage.java
 *
 * Version ID: 1.0
 *
 * Revision ID: 1.0
 */

/**
 * Homework 7.2
 *
 * This class implements the Homework7.StorageInterface and also a Binary Search Tree
 * which can store elements any type that implement comparable interface
 * @param <E> Type of the element to be stored
 *
 * @author Bhaskar Krishna Gangadhar
 */
public class SortedStorage<E extends Comparable<E>> implements StorageInterface<E>{
    BinaryNode<E> root;
    private int nullCount;

    /**
     * Constructor that create initializes the root node of the tree to null
     */
    public SortedStorage() {
        root = null;
        nullCount = 0;
    }

    /**
     * Implementing the add method that adds elements to the BST
     *
     * @param x element that needs to be added
     * @return true if added successfully false otherwise
     */
    @Override
    public synchronized boolean add( E x ) {
        if ( x == null ) {
            nullCount++;
        }
        else {
            root = insertNode( root, x );
        }
        return true;
    }

    /**
     * This is a helper method that adds to the node
     *
     * @param node to which the element will be attached
     * @param element that will be attached
     * @return Final BST
     */
    private BinaryNode<E> insertNode( BinaryNode<E> node, E element ) {
        if ( node == null ) {
            BinaryNode<E>  newNode = new BinaryNode<>( element );
            newNode.increaseCount();
            return newNode;
        }
        else if ( element.compareTo( node.getElement() ) == 0) {
            node.increaseCount();
            return node;
        }
        else if ( element.compareTo( node.getElement() ) < 0) {
            node.setLeftNode( insertNode( node.getLeftNode(), element ) );
        }
        else if ( element.compareTo( node.getElement() ) > 0) {
            node.setRightNode( insertNode( node.getRightNode(), element ) );
        }
        return node;
    }

    /**
     * Implementing the interface method that tries to find an element
     *
     * @param x that's being searched
     * @return true if found in the tree, false otherwise
     */
    @Override
    public boolean find( E x ) {
        if ( x == null ) {
            return includesNull();
        }
        return search( root, x);
    }

    /**
     * Helper method for the find implementation
     *
     * @param node which might contain the element
     * @param element needed to be found
     * @return true if found, false otherwise
     */
    private boolean search( BinaryNode<E> node, E element ) {
        if ( node == null ) {
            return false;
        }
        if ( element.compareTo( node.getElement() ) == 0 ) {
            return true;
        }
        if ( element.compareTo( node.getElement() ) < 0 ) {
            return search( node.getLeftNode(), element );
        }
        if ( element.compareTo( node.getElement() ) > 0 ) {
            return search( node.getRightNode(), element );
        }
        return false;
    }

    /**
     * This method tells if we null has been added
     *
     * @return true if added, false otherwise
     */
    @Override
    public synchronized boolean includesNull() {
        return nullCount > 0;
    }

    /**
     * Implementing the delete element method
     *
     * @param x element that needs to be deleted
     * @return true if deleted, false otherwise
     */
    @Override
    public synchronized boolean delete( E x ) {
        if ( x == null ) {
            if ( includesNull() ) {
                nullCount--;
                return true;
            }
            return false;
        }

        if ( find( x ) ) {
            deleteNode( root, x );
            return true;
        }
        return false;
    }

    /**
     * Helper method for the delete
     *
     * @param node that might contain the element
     * @param element that needs to be deleted
     * @return true we deleted, false otherwise
     *
     * @author Professor HP - modified his method
     */
    private BinaryNode<E> deleteNode( BinaryNode<E> node, E element ) {

        if ( node == null ) {
            return null;
        }

        if ( element.compareTo( node.getElement() ) == 0) {
            if ( node.getCount() > 1 ) {
                node.decreaseCount();
            }
            else if ( ( node.getLeftNode() != null)
                    && ( node.getRightNode() != null) ) {
                BinaryNode<E> minimumNodeOnRight = minimumElement( node.getRightNode() );
                node.setElement( minimumNodeOnRight.getElement() );
                node.setCount( minimumNodeOnRight.getCount() );
                node.setRightNode( deleteNode( node.getRightNode(),
                        minimumNodeOnRight.getElement() ) );
            }
            else if ( node.getLeftNode() != null) {
                node = node.getLeftNode();
            }
            else if (node.getRightNode() != null) {
                node = node.getRightNode();
            }
            else {
                node = null;
            }
        }
        else if ( element.compareTo( node.getElement() ) < 0 ) {
            node.setLeftNode( deleteNode( node.getLeftNode(), element ) );
        }
        else if ( element.compareTo( node.getElement() ) > 0 ) {
            node.setRightNode( deleteNode( node.getRightNode(), element ) );
        }
        return node;
    }

    /**
     * Helper method for delete that finds the minimum node that needs to
     * replace the node being deleted
     *
     * @param node who's replacement is being found
     * @return node that will replace
     */
    private BinaryNode<E> minimumElement( BinaryNode<E> node ) {
        if ( node.getLeftNode() == null ) {
            return node;
        }
        else {
            return minimumElement( node.getRightNode() );
        }
    }

    /**
     * Overriding toString
     *
     * @return string representation of the object
     */
    @Override
    public synchronized String toString() {
        return "\nincludes so many nulls values = " + nullCount + "\n"+
                "Values stored: " + root;
    }

    /**
     * Main method
     *
     * @param args ignored
     */
    public static void main( String[] args ) {
        StorageInterface<String> storage = new SortedStorage<String>();
        TestInterfaceImplementation.testStringSet(storage);
    }
}
