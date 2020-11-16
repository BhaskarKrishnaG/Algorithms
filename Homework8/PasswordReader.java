package Homework8; /**
 * PasswordReader.java
 *
 * Version id: $ID$
 *
 * Revision id: $ID$
 */
import java.io.*;

/**
 * This class reads from a file, a serialised object which should contain
 * a potential password object.
 * It will print out the password if it believes no one modified it
 * It'll print nothing otherwise
 */
public class PasswordReader implements Serializable {
    static final long serialVersionUID = 720433719289789L;
    private final String fileName;

    /**
     * Parameterised constructor which sets the filename
     *
     * @param fileName output
     */
    PasswordReader( String fileName ) {
        this.fileName = fileName;
    }

    private static int computeHash( String password) {
        int hashCode = 0;
        for ( int i = 0; i < password.length(); i++ ) {
            hashCode += (int) password.charAt(i) + i;
        }
        hashCode %= password.length();
        return hashCode;
    }

    private void readPassword() {
        try (
                FileInputStream stream = new FileInputStream( fileName );
                ObjectInputStream input = new ObjectInputStream(stream);
        ) {
            int originalHash = input.readInt();

            String password = (String) input.readObject();
            int hashCode = computeHash( password );
            if ( originalHash == hashCode ) {
                System.out.println( "The password is: " + password );
            }
            else {
            System.out.println( "The password is: " );
            }
        }
        catch ( IOException | ClassNotFoundException e ) {
            System.out.println( e.getMessage() );
        }
    }
    public static void main(String[] args) {

    PasswordReader reader = new PasswordReader( args.length != 1 ? "1234.ser"
                                                : args[0]);
    reader.readPassword();

    }
}
