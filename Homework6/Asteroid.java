package Homework6; /**
 * Asteroid.java
 *
 * Version: 1.0
 *
 * Revision: 1.0
 */

/**
 * This class will define a blueprint for a Asteroid.
 * - Attributes of the Asteroid
 * - Methods that modify these attributes
 *
 * @author Bhaskar Krishna Gangadhar
 */

public class Asteroid implements  AstronomicalObject{
    String name;
    double  density;
    String  discoverer;

    /**
     * Default constructor
     */
    public Asteroid() {
    }

    /**
     * Parameterised constructor
     *
     * @param name of the Asteroid
     * @param density of the Asteroid
     * @param discoverer of the Asteroid
     */
    public Asteroid(String name, double density, String discoverer ) {
        this.name = name;
        this.density = density;
        this.discoverer = discoverer;
    }

    /**
     * Getter method for the name of the Asteroid
     *
     * @return name of the Asteroid
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for the name of the Asteroid
     *
     * @param name of the Asteroid
     */
    public void setName( String name ) {
        this.name = name;
    }

    /**
     * Getter method for the density of the Asteroid
     *
     * @return density of the Asteroid
     */
    public double getDensity() {
        return density;
    }

    /**
     * Setter method for the density of the Asteroid
     *
     * @param density of the Asteroid
     */
    public void setDensity( double density ) {
        this.density = density;
    }

    /**
     * Getter method for the discoverer of the Asteroid
     *
     * @return discoverer of the Asteroid
     */
    public String getDiscoverer() {
        return discoverer;
    }

    /**
     * Setter method for the discoverer of the Asteroid
     *
     * @param discoverer of the Asteroid
     */
    public void setDiscoverer( String discoverer ) {
        this.discoverer = discoverer;
    }

    /**
     * Overriding the Object's toString method to return desired string.
     *
     * @return desired formatted string.
     */
    public String toString() {
        return name + "/" + density + "/" + discoverer;
    }

    /**
     * This method is an implementation of interface method
     *
     * @return a new Object with all the data copied.
     */
    @Override
    public Asteroid copy() {
        Asteroid copyAsteroid = new Asteroid();
        copyAsteroid.setName( this.getName() );
        copyAsteroid.setDensity( this.getDensity() );
        copyAsteroid.setDiscoverer( this.getDiscoverer() );
        return copyAsteroid;
    }

    /**
     * The main method
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        Asteroid a1 = new Asteroid("Pallas", 123.22, "Charles Messier");
        System.out.println(a1);
    }
}
