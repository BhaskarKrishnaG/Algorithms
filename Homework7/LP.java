package Homework7; /**
 * Homework7.LP.java
 *
 * Version ID: 1.0
 *
 * Revision ID: 1.0
 */

/**
 * Homework 7.1
 * This class will define a blue print for labels
 *
 * @author Bhaskar Krishna Gangadhar
 */
public class LP implements Comparable<LP> {
    private final int year;
    private final String name;
    private final String band;
    private final float totalPlaytime;
    private final int numberOfSongs;

    /**
     * Parameterised constructor to not allow creating of empty object
     *
     * @param year of the album
     * @param name of the album
     * @param band of the album
     * @param totalPlaytime of the album
     * @param numberOfSongs of the album
     */
    public LP( int year, String name, String band, float totalPlaytime,
               int numberOfSongs ) {
        this.year = year;
        this.name = name;
        this.band = band;
        this.totalPlaytime = totalPlaytime;
        this.numberOfSongs = numberOfSongs;
    }

    /**
     * Getter method for the year of the album
     *
     * @return year
     */
    public int getYear() {
        return year;
    }

    /**
     * Getter method for the name of the album
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for the band of the album
     *
     * @return band
     */
    public String getBand() {
        return band;
    }

    /**
     * Getter method for the play time of the album
     *
     * @return play time
     */
    public float getTotalPlaytime() {
        return totalPlaytime;
    }

    /**
     * Getter method for the number of songs of the album
     *
     * @return number of songs
     */
    public int getNumberOfSongs() {
        return numberOfSongs;
    }

    /**
     * Overriding toString
     *
     * @return string representation of the object
     */
    @Override
    public String toString() {
        return year +"/" + name + "/" + band + "/" + totalPlaytime + "/"
                + numberOfSongs + "\n";
    }

    /**
     * Implementing the compare to from comparable
     *
     * @param o address object
     * @return 0 if equal, -1 if smaller or >1 otherwise
     */
    @Override
    public int compareTo( LP o ) {
        return this.toString().compareTo( o.toString() );
    }

    /**
     * Main method
     *
     * @param args ignored
     */
    public static void main( String[] args ) {
        LP l = new LP( 1960, "Deep Purple in Rock",
                "Deep Purple", (float)43.30, 7);
        System.out.println( "Year: " + l.getYear() );
        System.out.println( "Name: " + l.getName() );
        System.out.println( "Band: " + l.getBand() );
        System.out.println( "Playtime: " + l.getTotalPlaytime() );
        System.out.println( "Songs: " + l.getNumberOfSongs() );
    }
}
