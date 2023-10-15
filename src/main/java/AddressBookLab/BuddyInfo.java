/**
 * @author Yash Kapoor
 * Student ID: 101163338
 */

package AddressBookLab;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * BuddyInfo is a class that stores the information (name and phone number) of a buddy
 */
@Entity
public class BuddyInfo {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private String phoneNumber;
    private String address;

    /**
     * Default constructor for BuddyInfo
     * that uses default values for the name
     * and the number of the buddy
     */
    public BuddyInfo() {
        this("", "", "");
    }

    /**
     * Get the ID of the BuddyInfo Object - used as an identifier
     * @return  an Integer, the id of the BuddyInfo Object
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set the ID of the BuddyInfo Object - used as an identifier
     * @param id    an Integer, the id of the BuddyInfo Object
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Constructor for BuddyInfo
     *
     * @param name      a String, name of the buddy
     * @param phoneNumber    a String, number of the buddy
     */
    public BuddyInfo(String name, String phoneNumber, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    /**
     * Get the name of the buddy
     *
     * @return  a String, name of the buddy
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name of the buddy
     * @param name  a String, name of the buddy
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the number of the buddy
     *
     * @return  a String, number of the buddy
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Set the number of the buddy
     * @param phoneNumber   a String, number of the buddy
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Prints the name and phone number of a buddy in the format specified
     * @return  a String, name and phone number of a buddy
     */
    @Override
    public String toString()    {
        return "Name of Buddy: " + getName() + ", Phone Number of Buddy: " + getPhoneNumber()
                + ", Address of Buddy: " + getAddress();
    }
}
