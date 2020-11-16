package Homework9; /**
 * NumberCounterMultiThreaded.java
 *
 * Version ID:
 *
 * Revision ID:
 */
import java.io.*;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

/**
 * 9.2
 *
 * This class will accept a lottery distribution data and will compute
 * the mode of each value and print the result
 * Note: We are assuming input will not be through standard input
 * @author Bhaskar Krishna Gangadhar
 */
public class NumberCounterMultiThreaded extends Thread {


    private static String fileName;
    private final int[] lottery = new int[81];
    private static final double LINES = 12279;
    private int fromLine;
    private int toLine;

    /**
     * Default constructor which sets the filename
     */
    NumberCounterMultiThreaded(int fromLine, int toLine) {
        this.fromLine = fromLine;
        this.toLine = toLine;
    }

    /**
     * Helper method which opens a ZIP reader
     *
     * @return Reader
     * @throws IOException if not able to open
     */
    private BufferedReader openZIPReader( ) throws IOException {
        return new BufferedReader( new InputStreamReader(
                new GZIPInputStream( new FileInputStream( fileName ) ) ) );
    }

    /**
     * Helper method which opens a normal reader
     *
     * @return Reader
     * @throws FileNotFoundException if not found
     */
    private BufferedReader openReader() throws FileNotFoundException {
        return new BufferedReader( new FileReader( fileName ));
    }

    /**
     * This method will read the file and process the numbers that were called
     * out through out the year and calculate the mode of each number.
     */
    public void run() {
        int number;
        try ( BufferedReader input = fileName.endsWith(".gz") ? openZIPReader():
                                     openReader() ) {
            String line;

            // Skip those many lines
            while ( fromLine > 0 ) {
                input.readLine();
                --fromLine;
            }
            while ( toLine > 0  && ( line = input.readLine() ) != null) {
                String[] numbers = line.split(",");
                if( numbers.length == 2 ) {
                    Scanner scanner = new Scanner(numbers[1]);
                    while (scanner.hasNextInt()) {
                        number = scanner.nextInt();
                        lottery[number] += 1;
                    }
                    --toLine;
                }
            }
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Prints the mode in the required form
     */
    public static void printResult( NumberCounterMultiThreaded[] threads) {
        int[] sum = new int[81];
        for ( int i = 0; i < threads.length; i++) {
            for (  int j = 1; j < 81; j++ ) {
                sum[j] += threads[i].lottery[j];
            }
        }
        for ( int i = 1; i < sum.length; i += 4 ) {
            System.out.printf( "%2d: %8d%8d: %8d%8d: %8d%8d: %8d\n"
                    ,i, sum[i], i+1,sum[i+1], i+2, sum[i+2]
                    , i+3, sum[i+3]);
        }
    }

    /**
     * This method creates threads and starts them and joins the main thread
     * to each of them to wait before printing the result
     *
     * @param noOfThreads to create
     */
    private static void createThreads( int noOfThreads ) {
        NumberCounterMultiThreaded[] threads =
                new NumberCounterMultiThreaded[noOfThreads];
        int starting = 0;
        int ending = (int) Math.ceil(LINES/noOfThreads);
        for ( int i = 0; i < noOfThreads; i++ ) {
            threads[i] = new NumberCounterMultiThreaded(starting, ending);
            threads[i].start();
            starting = starting + ending;
        }

        for ( int i = 0; i < noOfThreads; i++ ) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        printResult( threads );
    }

    /**
     * The main method
     *
     * @param args ignored
     */
    public static void main( String[] args ) {
        if ( args.length != 2) {
            System.out.println( "Please enter the filename and number of threads");
            System.exit(1);
        }
        fileName = args[0];
        try {
            if ( Integer.parseInt(args[1]) < 1) {
                System.out.println("Threads have to be > 0 ");
                System.exit(1);
            }
        }
        catch ( NumberFormatException e ) {
            System.out.println("Please enter a valid number");
            System.exit(1);
        }

        createThreads( Integer.parseInt( args[1] ) );
    }
}
