package AddressBookLab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controller class for managing AddressBooks
 */
@RestController
@RequestMapping("/addressbook")
public class AddressBookController {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private BuddyRepository buddyRepository;

    /**
     * Creates a new AddressBook
     * @return  the new AddressBook object
     */
    @PostMapping("/")
    public AddressBook createAddressBook() {
        AddressBook addressBook = new AddressBook();
        return addressRepository.save(addressBook);
    }

    /**
     * Retrieves an AddressBook based off its ID
     * @param id    the ID object, ID of the AddressBook to retrieve
     * @return  The AddressBook object if found, or null if not found
     */
    @GetMapping("/{id}")
    public AddressBook getAddressBook(@PathVariable Integer id) {
        return addressRepository.findById(id).orElse(null);
    }

    /**
     * Adds a BuddyInfo object to an AddressBook
     * @param id    the ID object, the ID of the AddressBook to which the BuddyInfo shall be added
     * @param buddyInfo     the BuddyInfo object that will be added
     * @return      The updated AddressBook object if the operation is successful, or null if AddressBook cannot be found
     */
    @PutMapping("/{id}/addBuddy")
    public AddressBook addBuddyToAddressBook(@PathVariable Integer id, @RequestBody BuddyInfo buddyInfo) {
        AddressBook addressBook = addressRepository.findById(id).orElse(null);
        if (addressBook != null) {
            addressBook.addBuddy(buddyInfo);
            return addressRepository.save(addressBook);
        }
        return null;
    }

    /**
     * Removes a BuddyInfo from an Address Book based off the ID of the BuddyInfo Object
     *
     * @param addressBookId  the ID of the Address Book from which the BuddyInfo should be removed.
     * @param buddyId        the ID of the BuddyInfo to be removed.
     * @return      The updated AddressBook object if the operation is successful, or null if the Address Book or BuddyInfo is not found.
     */
    @DeleteMapping("/{addressBookId}/removeBuddy/{buddyId}")
    public AddressBook removeBuddyFromAddressBook(@PathVariable Integer addressBookId, @PathVariable Integer buddyId) {
        AddressBook addressBook = addressRepository.findById(addressBookId).orElse(null);
        Optional<BuddyInfo> buddyInfoOptional = buddyRepository.findById(buddyId);
        if (addressBook != null) {
            if (buddyInfoOptional.isPresent()) {
                BuddyInfo buddyToRemove = buddyInfoOptional.get();

                // Remove the buddy from the AddressBook's list
                addressBook.removeBuddy(buddyToRemove);

                // Save the updated AddressBook
                return addressRepository.save(addressBook);
            }
        }
        return null;
    }
}
