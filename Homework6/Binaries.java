package Homework6; /**
 * Binaries.java
 *
 * Version: 1.0
 *
 * Revision: 1.0
 */

/**
 * This class will define a blueprint for a Binaries.
 * - Attributes of the binaries
 * - Methods that modify these attributes
 *
 * @author Bhaskar Krishna Gangadhar
 */
public class Binaries implements AstronomicalObject {
    private String name;
    private double  density;
    private String  discoverer;
    private int satellites;


    /**
     * Default constructor
     */
    public Binaries() {
    }

    /**
     * Parameterised Constructor
     *
     * @param name of the binary
     * @param density of the binary
     * @param discoverer of the binary
     * @param satellites of the binary
     */
    public Binaries( String name, double density, String discoverer,
                     int satellites ) {
        this.name = name;
        this.density = density;
        this.discoverer = discoverer;
        this.satellites = satellites;
    }

    /**
     * Getter method for the binary's name
     *
     * @return the name of the binary
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method to set the name of the binary
     *
     * @param name of the binary
     */
    public void setName( String name ) {
        this.name = name;
    }

    /**
     * Getter method for the binary's density
     *
     * @return the density of the binary
     */
    public double getDensity() {
        return density;
    }

    /**
     * Setter method to set the density of the binary
     *
     * @param density of the binary
     */
    public void setDensity( double density ) {
        this.density = density;
    }

    /**
     * Getter method for the binary's discoverer
     *
     * @return the discoverer of the binary
     */
    public String getDiscoverer() {
        return discoverer;
    }

    /**
     * Setter method to set the discoverer of the binary
     *
     * @param discoverer of the binary
     */
    public void setDiscoverer( String discoverer ) {
        this.discoverer = discoverer;
    }

    /**
     * Getter method for the binary's satellites
     *
     * @return the satellites of the binary
     */
    public int getSatellites() {
        return satellites;
    }

    /**
     * Setter method to set the number of satellites of the binary
     *
     * @param satellites of the binary
     */
    public void setSatellites( int satellites ) {
        this.satellites = satellites;
    }

    /**
     * Overriding the Object's toString method to return desired string.
     *
     * @return desired formatted string.
     */
    public String toString() {
        return name + "/" + density + "/" + discoverer + "/" + satellites;
    }

    /**
     * This method is an implementation of interface method
     *
     * @return a new Object with all the data copied.
     */
    @Override
    public Binaries copy() {
        Binaries copyBinary = new Binaries();
        copyBinary.setName( this.getName() );
        copyBinary.setDensity( this.getDensity() );
        copyBinary.setDiscoverer( this.getDiscoverer() );
        copyBinary.setSatellites( this.getSatellites() );
        return copyBinary;
    }

    /**
     * The main method
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        Binaries b1 = new Binaries("Cygnus", 123.22, "Unknown", 0);
        System.out.println(b1);
    }
}
