package Homework4;
/**
 * BattleShip.java
 *
 * Version: 1.0
 *
 * Revision: 1.0
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This program will simulate a version of Battleship game.
 * Note: The game has been designed assuming the user meets below requirements.
 *  1. User provides a valid ocean text file which has the required information.
 *  2. Every boat has an unique id who's length is 1-127
 *  3. If you have unconnected boats with same name they'll be considered as one
 *  4. Width and the height that's specified in the ocean file is accurate to
 *     the rest of the data that follows.
 *  5. We are assuming there is at least something to play with, some boat.
 *
 * @author Bhaskar Krishna Gangadhar.
 */
public class BattleShip {
    String[][] boat;
    String[][] ocean;
    String[][] display;
    int width;
    int height;

    /**
     * This parameterized constructor will read the width and height from the
     * provided file and create arrays of appropriate legth.
     *
     * @param fileName ocean file which has the necessary data.
     * @throws FileNotFoundException when file provides is invalid.
     */
    private BattleShip( String fileName ) throws FileNotFoundException {
        Scanner fileScanner = new Scanner( new File( fileName ) );
        while ( fileScanner.hasNext() ) {
            String word = fileScanner.next();
            if ( word.equals( "width" ) ) {
                width = fileScanner.nextInt();
            }
            if ( word.equals( "height" ) ) {
                height = fileScanner.nextInt();
            }
            if ( height != 0 && width !=0 ) {
                break;
            }
        }
        boat = new String[ height ][ width ];
        ocean = new String[ height ][ width ];
        display = new String[ height ][ width ];
        fileScanner.close();
    }

    /**
     * This method will initialize the boat and ocean array.
     *
     * @param fileName ocean file which has the necessary data.
     * @throws FileNotFoundException when file provides is invalid.
     */
    private void initializeArrays( String fileName ) throws FileNotFoundException {
        Scanner file = new Scanner( new File( fileName ) );
        int rowNumber = 0;

        while ( file.hasNextLine() ) {
            String line = file.nextLine();

            // Skipping the first two lines.
            if ( line.contains( "width" ) || line.contains( "height" ) ) {
                continue;
            }
            int columnNumber = 0;
            for ( String word : line.split("\\s+") ) {
                if ( word.equals( "w" ) ) {
                    ocean[ rowNumber ][ columnNumber ] = word;
                    columnNumber++;
                }
                else if ( !word.equals( "row" ) ) {
                    boat[ rowNumber ][ columnNumber ] = word;
                    columnNumber++;
                }
            }
            rowNumber++;
        }
        file.close();
    }

    /**
     * This method will initialize the display array with just '.'s.
     */
    private void initDisplayArr() {
        for ( int i = 0; i < height; i++ ) {
            for (int j = 0; j < width; j++ ) {
                display[ i ][ j ] = ".";
            }
        }
    }

    /**
     * This method can be used at any point to print the current status of the
     * game.
     */
    private void printDisplayArr() {
        System.out.print("\n\n    ");
        for ( int index = 0; index < width; index++ ) {
            System.out.print(index + " "); // Print like needed
        }
        System.out.print( "---> columns\n" );

        for (int i = 0; i < height; i++ ) {
            System.out.print( i + ": ");
            for ( int j = 0; j < width; j++ ) {
                System.out.print(" " + display[ i ][ j ]);
            }
            System.out.println();
        }
    }

    /**
     * This method will sink all the entire boat when use gets one of the
     * co-ordinates of the boat correct.
     *
     * @param row the row the user chose.
     * @param col the column the user chose.
     */
    private void sinkTheBoat( int row, int col ) {
        String id = boat[ row ][ col ];
        for ( int i = 0; i < height; i++ ) {
            for (int j = 0; j < width; j++ ) {
                if ( boat[ i ][ j ] != null && boat[i][j].equals( id ) ) {
                    boat[ i ][ j ] = null;
                    display[ i ][ j ] = "x";
                }
            }
        }
    }

    /**
     * This method will check if there are any boats floating at the given
     * moment.
     *
     * @return True is there is floating boat, false otherwise.
     */
    private boolean hasAnyBoat() {
        boolean hasBoat = false;
        for ( int row = 0; row < height; row++ ) {
            for ( int col = 0; col < width; col++ ) {
                if ( boat[row][col] != null ) {
                    hasBoat = true;
                    break;
                }
            }
        }
        return hasBoat;
    }

    /**
     * This method will start the game and keep on asking for the co-ordinates
     * from the user until all the boats have been sunk.
     */
    private void startGame() {
        Scanner userInput = new Scanner( System.in );
        initDisplayArr();
        while ( hasAnyBoat() ) {
            printDisplayArr();
            System.out.println(" You can enter only Integer values!" );
            System.out.printf( "column coordinate(0 <= column < %d): ", width );
            int column = userInput.nextInt();
            System.out.printf( "row coordinate(0 <= column < %d): ", height );
            int row = userInput.nextInt();

            // If the co-ordinates are out of range, notify and continue.
            if ( column >= width || row >= height || column < 0 || row < 0 ) {
                System.out.println( "Please enter valid co-ordinated!" );
                continue;
            }

            if ( boat[row][column] != null ) {
                sinkTheBoat( row, column );
                System.out.println( "HIT" );
            }
            else if ( ocean[row][column] != null ) {
                display[row][column] = "w";
            }
        }

        printDisplayArr();
        System.out.println( "Game over, all boats have sunk!" );
        userInput.close();
    }

    /**
     * The program starts from here.
     *
     * @param args ocean file.
     * @throws FileNotFoundException if the file is invalid.
     */
    public static void main( String[] args ) throws FileNotFoundException {
        if ( args.length != 1 ) {
            System.out.println( "Please enter valid file name" );
        }
        else {
            String fileName = args[ 0 ];
            BattleShip game = new BattleShip( fileName );
            game.initializeArrays( fileName );
            game.startGame();
        }
    }
}
