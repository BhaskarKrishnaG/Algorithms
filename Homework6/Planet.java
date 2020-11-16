package Homework6; /**
 * Planet.java
 *
 * Version: 1.0
 *
 * Revision: 1.0
 */

/**
 * This class will define a blueprint for a Planet.
 * - Attributes of the planet
 * - Methods that modify these attributes
 *
 * @author Bhaskar Krishna Gangadhar
 */
public class Planet implements AstronomicalObject {
    private String name;
    private double density;
    private double orbitalPeriod;
    private int numberOfMoons;

    /**
     * Default constructor
     */
    public Planet() {
    }

    /**
     * We are defining a parameterised constructor to make sure that a planet
     * without any attributes can't be created.
     *
     * @param name of the planet.
     * @param density of the planet.
     * @param orbitalPeriod of the planet.
     * @param numberOfMoons the planet has.
     */
    public Planet( String name, double density, double orbitalPeriod, int numberOfMoons ) {
        this.name = name;
        this.density = density;
        this.orbitalPeriod = orbitalPeriod;
        this.numberOfMoons = numberOfMoons;
    }

    /**
     * Getter method for name attribute.
     *
     * @return name of the planet.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method to set/modify the name of the planet
     *
     * @param name of the planet
     */
    public void setName( String name ) {
        this.name = name;
    }

    /**
     * Getter method for density attribute.
     *
     * @return density of the planet.
     */
    public double getDensity() {
        return density;
    }

    /**
     * Setter method to set/modify the density of the planet
     *
     * @param density of the planet
     */
    public void setDensity( double density ) {
        this.density = density;
    }

    /**
     * This method is an implementation of interface method
     *
     * @return a new Object with all the data copied.
     */
    @Override
    public Planet copy() {
        return new Planet(this.getName(), this.getDensity(),
                this.getOrbitalPeriod(), this.numberOfMoons);
    }

    /**
     * Getter method for orbital period attribute.
     *
     * @return orbital period of the planet.
     */
    public double getOrbitalPeriod() {
        return orbitalPeriod;
    }

    /**
     * Setter method to set/modify the orbital period of the planet
     *
     * @param orbitalPeriod of the planet
     */
    public void setOrbitalPeriod( double orbitalPeriod ) {
        this.orbitalPeriod = orbitalPeriod;
    }

    /**
     * Getter method for number of moons attribute.
     *
     * @return number of moons of the planet.
     */
    public int getNumberOfMoons() {
        return numberOfMoons;
    }

    /**
     * Setter method to set/modify the number of moons of the planet.
     *
     * @param numberOfMoons the planet consists.
     */
    public void setNumberOfMoons( int numberOfMoons ) {
        this.numberOfMoons = numberOfMoons;
    }

    /**
     * Overriding the Object's toString method to return desired string.
     *
     * @return desired formatted string.
     */
    public String toString() {
        return name + "/" + density + "/" + orbitalPeriod + "/" + numberOfMoons;
    }

    /**
     * The main method will OrganizedThreads certain properties.
     *
     * @param args command line arguments, ignored.
     */
    public static void main( String[] args) {
        Homework5.Planet aPlanet = new Homework5.Planet("Mercury", 5.427, 87.97, 1 );
        System.out.println(aPlanet);
        aPlanet.setName( "Saturn" );
        aPlanet.setDensity(0.687);
        aPlanet.setOrbitalPeriod(10759.22);
        aPlanet.setNumberOfMoons(82);
        System.out.println(aPlanet);

        System.out.println( "1: " + aPlanet.getName() );
        System.out.println( "2: " + aPlanet.getDensity() );
        System.out.println( "3: " + aPlanet.getOrbitalPeriod() );
        System.out.println( "4: " + aPlanet.getNumberOfMoons() );

    }
}
