/**
 * @author Yash Kapoor
 * Student ID: 101163338
 */

package AddressBookLab;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * AddressBook is a class that stores a list of BuddyInfo objects.
 * It allows the user to either add or remove a buddy from this list.
 */
@Entity
public class AddressBook {
    @Id
    @GeneratedValue
    private Integer id;
    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true)
    private final List<BuddyInfo> buddyInfo;

    /**
     * Constructor for AddressBook that initializes the buddyInfo ArrayList
     */
    public AddressBook() {
        this.buddyInfo = new ArrayList<>();
    }


    /**
     * Get the ID of the AddressBook Object - used as an identifier
     * @return  an Integer, the id of the AddressBook Object
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set the ID of the AddressBook Object - used as an identifier
     * @return  an Integer, the id of the AddressBook Object
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Add a buddy to the BuddyInfo ArrayList
     * @param bi    a BuddyInfo object, the new buddy to be added to the list
     */
    public void addBuddy(BuddyInfo bi) {
        buddyInfo.add(bi);
    }

    /**
     * Remove a buddy from the BuddyInfo ArrayList
     * @param bi    a BuddyInfo object, the buddy to be removed from the list
     */
    public void removeBuddy(BuddyInfo bi) {
        buddyInfo.remove(bi);
    }

    /**
     * Getter for the buddyInfo ArrayList
     * @return  a list of BuddyInfo objects stored in the AddressBook
     */
    public List<BuddyInfo> getBuddyInfo() {
        return buddyInfo;
    }
}
