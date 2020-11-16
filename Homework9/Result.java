package Homework9; /**
 * Homework10.Result.java
 *
 * Version ID:
 *
 * Revision ID:
 */

/**
 * This class provides a blue print to store the result for the luchrel numbers
 */
public class Result implements Comparable<Result> {
    public int firstNumberOfSequence;
    public String output;

    /**
     * Constructor
     * @param firstNumberOfSequence number
     * @param output string
     */
    public Result(int firstNumberOfSequence, String output) {
        this.firstNumberOfSequence = firstNumberOfSequence;
        this.output = output;
    }

    /**
     * This implements compareTo to compare two result objects
     *
     * @param o to compare with
     * @return 0 if equal, < 0 is lesser and > 0 if greater than the O
     */
    @Override
    public int compareTo(Result o) {
        return Integer.compare(this.firstNumberOfSequence, o.firstNumberOfSequence);
    }

    /**
     * Overriding the toString method
     *
     * @return String representation of the Object
     */
    @Override
    public String toString() {
        return output + "\n";
    }
}
