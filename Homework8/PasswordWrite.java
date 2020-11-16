package Homework8; /**
 * PasswordWrite.java
 *
 * Version id: $ID$
 *
 * Revision id: $ID$
 */

import java.io.*;

/**
 * This class writes a serialised object to a file.
 * This object is a password, we will also write the hashcode of the password
 * which will later be used to check if the password was modified
 */
public class PasswordWrite implements Serializable {
    static final long serialVersionUID = 72043371927804L;
    private final String PASSWORD = "abc1ef";
    private int hashCode;
    private final String fileName;

    /**
     * Parameterised constructor which sets the filename
     *
     * @param fileName output
     */
    PasswordWrite(String fileName ) {
        this.fileName = fileName;
    }

    /**
     * A very basic has function.
     */
    private void computeHash() {
        hashCode = 0;
        for ( int i = 0; i < PASSWORD.length(); i++ ) {
            hashCode += (int)PASSWORD.charAt(i) + i;
        }
        hashCode %= PASSWORD.length();
    }

    /**
     * This method will open an output stream and write the object in it.
     */
    private void writeObject() {
        try (
            FileOutputStream stream = new FileOutputStream(fileName);
            ObjectOutputStream output = new ObjectOutputStream(stream);
            ) {
            output.writeObject( PASSWORD );
            output.writeInt( hashCode );
            output.flush();
        }
        catch ( IOException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * The main function
     *
     * @param args file to which we are writing the object
     */
    public static void main( String[] args ) {

        PasswordWrite writer = new PasswordWrite( args.length != 1 ? "1234.ser"
                                                : args[0] );
        writer.computeHash();
        writer.writeObject();

    }
}
