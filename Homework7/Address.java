package Homework7; /**
 * Homework7.Address.java
 *
 * Version ID: 1.0
 *
 * Revision ID: 1.0
 */

/**
 * Homework 7.1
 * This class will define a blue print for an address
 *
 * @author Bhaskar Krishna Gangadhar
 */
public class Address implements Comparable<Address> {
    private final int street;
    private final String address;
    private final String city;
    private final String state;
    private final int pincode;

    /**
     * Parameterised constructor because you are not allowed to create a blank
     * address.
     *
     * @param street number where you live
     * @param address where you live
     * @param city where you live
     * @param state where you live
     * @param pincode of your place
     */
    public Address( int street, String address, String city, String state,
                    int pincode ) {
        this.street = street;
        this.address = address;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
    }

    /**
     * Getter method to access the street
     *
     * @return street
     */
    public int getStreet() {
        return street;
    }

    /**
     * Getter method to access the address
     *
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Getter method to access the city
     *
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * Getter method to access the state
     *
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     * Getter method to access the pincode
     *
     * @return pincode
     */
    public int getPincode() {
        return pincode;
    }

    /**
     * Overriding toString
     *
     * @return string representation of the object
     */
    @Override
    public String toString() {
        return street + "/" + address + "/" + city + "/" + state + "/"
                + pincode + "\n";
    }

    /**
     * Implementing the compare to from comparable
     *
     * @param a address object
     * @return 0 if equal, -1 if smaller or >1 otherwise
     */
    @Override
    public int compareTo( Address a ) {
        return this.toString().compareTo( a.toString() );
    }

    /**
     * Main method
     *
     * @param args ignored
     */
    public static void main( String[] args ) {
        Address a1 = new Address(1600, "Pennsylvania Avenue NW",
                "Washington", "DC", 20500);
        System.out.println( "Street: " + a1.getStreet() );
        System.out.println( "Homework7.Address: " + a1.getAddress() );
        System.out.println( "City: " + a1.getCity() );
        System.out.println( "State: " + a1.getState() );
        System.out.println( "Pincode: " + a1.getPincode() );
    }
}
