package Homework6; /**
 * StorageOfAstronomicalObject.java
 *
 * Version: 1.0
 *
 * Revision: 1.0
 */

import java.util.Vector;

/**
 * This class will define a class that can store Astronomical Object
 *
 * @author Bhaskar Krishna Gangadhar
 */
public class StorageOfAstronomicalObject<T extends AstronomicalObject> {

    private final Vector<T> storageObject;
    private final String storageName;

    /**
     * Parameterised constructor
     *
     * @param name of the storage system
     */
    StorageOfAstronomicalObject( String name ) {
        storageName = name;
        storageObject = new Vector<T>();
    }

    /**
     * This method will add any of the astronomical object to the storage.
     *
     * @param astronomicalObject Planet/Asteroid/
     */
    public void add( T astronomicalObject ) {
        storageObject.add( astronomicalObject );
    }

    /**
     * This method will fetch the names of all the astronomical objects stored
     * in the system
     *
     * @return names of all the astronomical objects
     */
    public String getAllNames() {
        String result = storageName + "\n";
        for(int index = 0; index < storageObject.size(); index++) {
            result += storageObject.get(index).getName() + ", ";
        }
        return result;
    }

    /**
     * Overriding the Object's toString method to return desired string.
     *
     * @return desired formatted string.
     */
    public String toString() {
        String result = "";
        double totalDensity = 0;
        for(int index = 0; index < storageObject.size(); index++) {
            result += index + ": " + storageObject.get( index ) + "\n";
            totalDensity += storageObject.get( index ).getDensity();
        }
        double averageDensity = totalDensity / storageObject.size();
        result += "\n\t\taverage density: " + averageDensity + "\n";
        return result;
    }

    /**
     * A generic method that takes any of the Astronomical object and
     * prints it's values
     *
     * @param aStorageOfAstronomicalObject object
     * @param <T> Generic type
     */
    public static <T extends AstronomicalObject> void print(
            StorageOfAstronomicalObject<T> aStorageOfAstronomicalObject) {
        System.out.println(aStorageOfAstronomicalObject.getAllNames());
        System.out.println(aStorageOfAstronomicalObject);
    }

    public static void testPlanets() {
        StorageOfAstronomicalObject<AstronomicalObject> aStorageOfAstronomicalObject
                = new StorageOfAstronomicalObject<AstronomicalObject>("Milky Way");

        aStorageOfAstronomicalObject.add(new Planet("Mercury", 5.427, 87.97, 0));

        Planet aPlanet = new Planet("Saturn", 0.687, 10792, 82);
        aStorageOfAstronomicalObject.add(aPlanet);

        aPlanet.setName("Earth");
        aPlanet.setDensity(5.514);
        aPlanet.setOrbitalPeriod(365.256363004);
        aPlanet.setNumberOfMoons(1);
        aStorageOfAstronomicalObject.add(aPlanet);
        print(aStorageOfAstronomicalObject);
    }

    public static void main(String[] args) {
        testPlanets();
    }
}
