package Homework3;

/**
 * Picture.java
 *
 * Version: 1.0
 *
 * Revision: 1.0
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

/**
 * This program will simulate a game where the user needs to guess the words.
 * The picture unwraps itself a bit for every correct guess and eventually if
 * the user guesses all the words correctly then the picture will be revealed.
 * - User has 9 tries per game to guess all the words.
 *
 * @author Bhaskar Krishna Gangadhar
 *         Utkarsh Sharma
 */
public class Picture {

    /**
     * This will display the hidden picture.
     *
     * @param image  Vector representation of the image.
     * @param reveal Integer which tells which character to show.
     */
    static void showThePicture(Vector<String> image, int reveal ) {
        int show = 1;
        for (int index = 0; index < image.size(); index++ ){
            String line = image.get( index );
            for ( int i = 0; i < line.length(); i++) {
                if ( show % reveal == 0 ) {
                    System.out.print( line.charAt(i) );
                }
                else {
                    System.out.print( "." );
                }
                show++;
            }
            System.out.println();
        }
    }

    /**
     * This method will ask the user to guess the letters one at a time
     * giving them 9 changes to guess the hidden word correctly.
     *  @param word  The word that's being guessed.
     * @param picture The picture.
     */
    static void startTheRound( String word, Vector<String> picture ) {
        Vector<Character> characters = new Vector<>();
        Scanner input = new Scanner( System.in );
        Vector<Character> guessedCharacters = new Vector<>();
        for (int count = 0; count < word.length(); count++ ) {
            guessedCharacters.add('_');
        }
        int attempt = 0;
        int guessCount = 0;
        for ( char c : word.toCharArray() ) {
            characters.add( c );
        }
        while ( characters.size() > 0 ) {

            // Ran out of attempts. Move on to next word.
            if ( attempt == 10 ) {
                break;
            }

            int printEverXthWord = ( word.length() - guessCount ) + 1;
            showThePicture( picture, printEverXthWord );
            System.out.println( attempt + ": " + guessedCharacters );

            // Reading user input
            Character guessedCharacter = input.next().charAt(0);

            if ( characters.contains( guessedCharacter ) ) {
                guessedCharacters.set( guessCount, guessedCharacter );
                characters.remove( guessedCharacter );
                guessCount++;
            }

            else {
                ++attempt;
            }

            // Guessed all the characters, reveal the image and the word.
            if ( characters.size() == 0 ) {
                showThePicture( picture, printEverXthWord - 1 );
                System.out.println( "The word was: " + word  + "\n\n");
            }
        }
    }

    /**
     * The method simulates the game for all the words present in the file.
     *
     * @param words the file containing the words to be guessed.
     * @param picture the picture file.
     */
    static void startGame(Scanner words, Vector<String> picture ) {
        Vector<String> listOfWords = new Vector<>();
        Random random = new Random();

        while ( words.hasNextLine() ) {
            listOfWords.add(words.nextLine());
        }

        while ( listOfWords.size() > 0 ) {

            // Pick a random word and start the guessing game
            int word = random.nextInt( listOfWords.size() );
            startTheRound( listOfWords.get(word), picture );
            listOfWords.remove( word );
        }
        System.out.println( "No more words left to guess\n Hope you had fun!" );
    }

    /**
     * This method will read the picture file into a vector of strings so that
     * we don't have to open the file through scanner each time.
     *
     * @param image text file of image.
     * @return the vector of strings.
     */
    static Vector<String> readTheImage(Scanner image ) {
        Vector<String> picture = new Vector<>();
        while ( image.hasNext() ) {
            picture.add( image.nextLine() );
        }
        return picture;
    }

    /**
     * The main function.
     * @param args - words, the words text file contains the words to guess.
     *             - picture, contains the picture in a text file.
     */
    public static void main( String[] args ) throws FileNotFoundException {
        if ( args.length < 4) {
            System.out.print( "Please enter the arguments correctly\n\t" +
                    "Picture -words <words file> -picture <picture file>" );
        }
        else {
            int i = 0;
            Scanner words = null;
            Scanner picture = null;
            while ( i < args.length ) {
                if ( args[ i ].equals("-words") ) {
                    ++i;
                    words = new Scanner( new File( args[ i ] ) );
                }
                else if (args[ i ].equals("-picture") ) {
                    ++i;
                    picture = new Scanner( new File( args[ i ] ));
                }
                i++;
            }

            if ( picture != null && words != null ) {
                Vector<String> image = readTheImage(picture);
                startGame(words, image);
                words.close();
                picture.close();
            }

            else {
                System.out.print( "Please enter the arguments correctly\n\t" +
                        "Picture -words <words file> -picture <picture file>" );
            }
        }
    }
}
