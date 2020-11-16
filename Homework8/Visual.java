package Homework8;// original from: http://rosettacode.org/wiki/Pi_set#Java
// modified for color

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.zip.GZIPInputStream;

/**
 * This program will read a file and draw an imagine based on the properties of
 * the numbers present in the file.
 *
 * @author Professor HP
 * @author Bhaskar Krishna Gangadhar
 * @author Utkarsh Sharma
 */
public class Visual extends JFrame {

    private final int LENGTH_OF_SQUARE = 3;
    private final int LENGTH 	       = 330;
    private final int LENGTH_OF_WINDOW = LENGTH * LENGTH_OF_SQUARE;
    private BufferedImage theImage;
    private String fileName = null;
    Reader input;

    public Visual() {
        super("Visual");

        setBounds(100, 100, LENGTH_OF_WINDOW, LENGTH_OF_WINDOW);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Constructor which initialises the file
     *
     * @param fileName file
     */
    public Visual(String fileName) {
        this();
        this.fileName = fileName;
    }

    /**
     * This method will read the next character in the input buffer if it's a
     * digit then it'll return or it'll recursively call the same method
     * until it find a digit or eof
     *
     * @param input BufferedReader
     * @return character digit
     * @throws IOException if something went wrong while reading
     */
    private char nextDigit(BufferedReader input) throws IOException {
        char buf[] = new char[1];
        char[] value = Character.toChars(input.read());
        if ( value[0] >= '0' && value[0] <= '9') {
            buf[0] = value[0];
        }
        else {
            buf[0] = nextDigit( input );
        }
        return buf[0];
    }

    /**
     * Saves the image to a png file
     *
     * @param theImage the picture
     */
    private void saveImage(BufferedImage theImage)	{
        String suffix = "png";
        String outputFileName = fileName == null ? "output" : fileName + "_output" + "." + suffix ;
        try {
            File outputfile = new File(outputFileName);
            ImageIO.write(theImage, suffix, outputfile);
        } catch (Exception e )	{
            e.printStackTrace();
        }

    }
    public void fillSquare(int xOrig, int yOrig, int color)	{
        for (int x = 0; x < LENGTH_OF_SQUARE; x ++ )
            for (int y = 0; y < LENGTH_OF_SQUARE; y ++ )
                theImage.setRGB(xOrig + x, yOrig + y , color);
    }

    /**
     * This method will paint the picture based on the property of the number
     */
    public void createImage()	{
        theImage = new BufferedImage(getWidth(), getHeight(),
                BufferedImage.TYPE_INT_RGB);
        int red = Color.RED.getRGB();		// you might like to change the colors if you like
        int blue = Color.BLUE.getRGB();		// you might like to change the colors if you like
        int colorUsed;

        try (
            BufferedReader input = fileName != null ? ( fileName.contains(".gz") ?
                    new BufferedReader( new InputStreamReader(
                            new GZIPInputStream( new FileInputStream(fileName)))) :
                    new BufferedReader( new FileReader(fileName))):
                    new BufferedReader(new InputStreamReader(System.in))
        ) {
            for (int y = 0; y < getHeight(); y += LENGTH_OF_SQUARE) {
                for (int x = 0; x < getWidth(); x += LENGTH_OF_SQUARE) {
                    char digit = nextDigit(input);
                    fillSquare(x, y,  digit % 2 == 0 ? red : blue );
                }

            }
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }
        repaint();
        saveImage(theImage);
        System.exit(0);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(theImage, 0, 0, this);
    }

    public static void main(String[] args) {
        Visual aVisual = new Visual(args.length == 1 ? args[0] : null );
        aVisual.setVisible(true);
        aVisual.createImage();
    }
}