package Homework7; /**
 * Homework7.BinaryNode.java
 *
 * Version ID: 1.0
 *
 * Revision ID: 1.0
 */

/**
 * Homework 7.2
 * This class will define a blue print a node of BST
 *
 * @author Bhaskar Krishna Gangadhar
 */
public class BinaryNode<E extends Comparable<E>> {
    private E element;
    private int count;
    private BinaryNode<E> leftNode;
    private BinaryNode<E> rightNode;

    /**
     * Constructor
     * @param element value the node stores
     */
    public BinaryNode(E element) {
        this.element = element;
        leftNode = null;
        rightNode = null;
    }

    /**
     * Getter for value of node
     *
     * @return value stored
     */
    public E getElement() {
        return element;
    }

    /**
     * Setter for the value of the node
     *
     * @param element value of the node
     */
    public void setElement( E element ) {
        this.element = element;
    }

    /**
     * Method to increase the count of the element
     */
    public void increaseCount() {
        count++;
    }

    /**
     * Method to decrease the count of the element
     */
    public void decreaseCount() {
        count--;
    }

    /**
     * Getter for count of same element
     *
     * @return count
     */
    public int getCount() {
        return count;
    }

    /**
     * Setter for the count of same element
     *
     * @param count of element
     */
    public void setCount( int count ) {
        this.count = count;
    }

    /**
     * Getter for left node
     *
     * @return left node
     */
    public BinaryNode<E> getLeftNode() {
        return leftNode;
    }

    /**
     * Setter method for left node
     *
     * @param leftNode being set
     */
    public void setLeftNode(BinaryNode<E> leftNode) {
        this.leftNode = leftNode;
    }

    /**
     * Getter for right node
     *
     * @return right node
     */
    public BinaryNode<E> getRightNode() {
            return rightNode;
    }

    /**
     * Setter method for right node
     *
     * @param rightNode being set
     */
    public void setRightNode(BinaryNode<E> rightNode) {
        this.rightNode = rightNode;
    }

    /**
     * Inorder traversal of the BST
     *
     * @param node current node who's being traversed
     * @return String of the traversal
     */
    private String inOrderTraversal(BinaryNode<E> node) {
        if ( node != null ) {
            String output = "";
            output += inOrderTraversal( node.getLeftNode());
            output += node.getElement();
            output += inOrderTraversal( node.getRightNode());
            return output;
        }
        return "";
    }

    /**
     * Overriding toString
     *
     * @return string representation of the object
     */
    @Override
    public String toString() {
        return inOrderTraversal( this );
    }
}
