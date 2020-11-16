package Homework6; /**
 * AstronomicalObject.java
 *
 * Version: 1.0
 *
 * Revision: 1.0
 */

/**
 * This Interface will have a set of common methods that Astronomical Objects
 * will share that can be implemented by individual classes.
 *
 * @author Bhaskar Krishna Gangadhar
 */
public interface AstronomicalObject {
    String getName();
    void setName(String name);
    double getDensity();
    void setDensity(double density);
    AstronomicalObject copy();
}
