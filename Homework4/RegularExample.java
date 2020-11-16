package Homework4;
/**
 * RegularExamples.java
 *
 * Version: 1.0
 *
 * Revision: 1.0
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * This program will read a set of strings from a provided file after applying
 * the user specified delimiter.
 * For each of this strings it'll check against a set of predefined patters
 * and print the result appropriately
 * We are assuming that a word indicates an english word, meaning it only
 * has alphabets in them.
 * For the vowels pattern is case insensitive
 *
 * @author Bhaskar Krishna Gangadhar.
 *
 * Note: We have copied the structure from Professor HP's notes.
 */
public class RegularExample {

    static String[] allPatternsToTest = {
            "starts with 'a' followed by one digit or more digits",
            "a[0-9]+",
            "a word with the vowels 'aeiou' in order and each vowel can appear only once",
            "[a-zA-Z&&[^aeiouAEIOU]]*[aA][a-zA-Z&&[^aeiouAEIOU]]*[eE][a-zA-Z&&[^aeiouAEIOU]]*[iI]" +
             "[a-zA-Z&&[^aeiouAEIOU]]*[oO][a-zA-Z&&[^aeiouAEIOU]]*[uU][a-zA-Z&&[^aeiouAEIOU]]*",
            "starts with ’a’ followed by 3 digits in the range between 1 and 3 only,",
            "a[1-3]{3}",
            "starts with ’a’ followed by least 3 digits in the range between 1 and 3 only,",
            "a[1-3]{3,}",
            "starts with ’a’ followed by between 1 and 2 digits in the range between 8 and 9 only,",
            "a[8-9]{1,2}",
            "includes only lower case characters, but not the character 'h', 'p', and 'b'",
            "[a-z&&[^hpb]]+",
    };

    /**
     * This method will check the string against all the predefined patterns
     * and print if they match any of it.
     *
     * @param word the string which is being tested.
     */
    private static void checkIfMatches( String word ) {
        int index = 0;
        System.out.println("Input: -" + word + "=");
        while  ( index < allPatternsToTest.length ) {
            if  ( Pattern.matches(allPatternsToTest[index + 1 ], word ) ) {
                System.out.println("This regular expression " +
                        allPatternsToTest[index + 1 ] +
                        " matches the following input: -" + word + "=");
                System.out.println(" 	verbal explanation: " +
                        allPatternsToTest[index]);
            }
            index += 2;
        }
    }

    /**
     * This main program.
     *
     * @param args command line argument, a delimiter and file with strings.
     * @throws FileNotFoundException if input file is invalid
     */
    public static void main( String[] args ) throws FileNotFoundException {
        if ( args.length != 4 ) {
            System.out.println("Please enter the right arguments\n\t" +
                    "-d '<delimiter>' -input <filename>");
        }
        else {
            int i = 0;
            Scanner words = null;
            String delimiter = "";
            while ( i < args.length ) {
                if ( args[ i ].equals("-input") ) {
                    ++i;
                    words = new Scanner( new File( args[ i ] ) );
                }
                else if (args[ i ].equals("-d") ) {
                    ++i;
                    delimiter = args[i];
                }
                i++;
            }
            delimiter = delimiter.substring( 1, delimiter.length()-1 );
            words.useDelimiter( delimiter );
            while ( words.hasNext() ) {
                String wordToTest = words.next();
                if ( !wordToTest.equals("\n") ) {
                    System.out.println( "-".repeat(60) );
                    checkIfMatches( wordToTest );
                }
            }
            words.close();
        }
    }
}
