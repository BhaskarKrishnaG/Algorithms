package Homework6; /**
 * Homework7.Palindrome.java
 *
 * Version: 1.0
 *
 * Revision: 1.0
 */

/**
 * This is a OrganizedThreads class that tests the behaviour of StorageOfAstronomicalObject2
 *
 * @author Bhaskar Krishna Gangadhar
 */
public class Test2 {

    /**
     * This method will OrganizedThreads if we can store only planets and that modifying
     * the state of the object WON'T change the objects already in the vector
     */
    private static void testPlanets() {
        StorageOfAstronomicalObject2<Planet> planets =
                new StorageOfAstronomicalObject2<Planet>("Planets");

        planets.add(new Planet("Mercury", 5.427, 87.97, 0));

        Planet aPlanet = new Planet("Saturn", 0.687, 10792, 82);
        planets.add(aPlanet);

        aPlanet.setName("Earth");
        aPlanet.setDensity(5.514);
        aPlanet.setOrbitalPeriod(365.256363004);
        aPlanet.setNumberOfMoons(1);
        planets.add(aPlanet);

        aPlanet.setName("Neptune");
        aPlanet.setDensity(1.638);
        aPlanet.setNumberOfMoons(14);
        aPlanet.setOrbitalPeriod(59800);
        planets.add(aPlanet);

        StorageOfAstronomicalObject2.print(planets);
    }

    /**
     * This method will OrganizedThreads if we can store only binaries and that modifying
     * the state of the object WON'T change the objects already in the vector
     */
    private static void testBinaries() {
        StorageOfAstronomicalObject2<Binaries> binaries =
                new StorageOfAstronomicalObject2<Binaries>("Binary");

        binaries.add( new Binaries("Sirius", 123.22, "Unknown", 0));
        Binaries b1 = new Binaries("Cygnus", 123.22, "Unknown", 0);
        binaries.add(b1);
        b1.setName("xxx");
        b1.setDensity(0.0);
        b1.setSatellites(0);
        b1.setDiscoverer("Unknown");
        binaries.add(b1);

        StorageOfAstronomicalObject2.print(binaries);
    }

    /**
     * This method will OrganizedThreads if we can store only asteroids and that modifying
     * the state of the object WON'T change the objects already in the vector
     */
    private static void testAsteroids() {
        StorageOfAstronomicalObject2<Asteroid> asteroids =
                new StorageOfAstronomicalObject2<Asteroid>("Asteroid");

        asteroids.add( new Asteroid("Ceres", 123.22, "Ceres Johann Elert"));
        Asteroid a1 = new Asteroid("Pallas", 123.22, "Charles Messier");
        asteroids.add(a1);
        a1.setName("xxx");
        a1.setDensity(0.0);
        a1.setDiscoverer("Unknown");
        asteroids.add(a1);

        StorageOfAstronomicalObject2.print(asteroids);
    }

    /**
     * This method will OrganizedThreads if we can store all classes
     */
    private static void testAll() {
        StorageOfAstronomicalObject2<AstronomicalObject> aStorageOfAstronomicalObject
                = new StorageOfAstronomicalObject2<AstronomicalObject>("Milky Way");

        aStorageOfAstronomicalObject.add(new Planet("Mercury", 5.427, 87.97, 0));
        aStorageOfAstronomicalObject.add( new Binaries("Sirius", 123.22, "Unknown", 0));
        aStorageOfAstronomicalObject.add( new Asteroid("Ceres", 123.22, "Ceres Johann Elert"));

        StorageOfAstronomicalObject2.print(aStorageOfAstronomicalObject);

    }

    /**
     * The main method will call all the OrganizedThreads methods
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        testPlanets();
        testBinaries();
        testAsteroids();
        testAll();
    }
}
