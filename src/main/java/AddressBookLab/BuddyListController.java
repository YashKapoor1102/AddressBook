package AddressBookLab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller Class for managing list of buddies in an AddressBook
 */
@Controller
public class BuddyListController {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private BuddyRepository buddyRepository;

    /**
     * Displays the buddy list associated with an AddressBook
     * @param id    the ID Object, the ID of the AddressBook for which the buddy list should be displayed
     * @param model     the model object, the model used to pass data to the view
     * @return  the name of the view template called "buddies" that renders the content of the web page
     */
    @GetMapping("/addressbook/{id}/buddylist")
    public String viewBuddyList(@PathVariable Integer id, Model model) {
        AddressBook addressBook = addressRepository.findById(id).orElse(null);
        if (addressBook != null) {
            model.addAttribute("buddiesList", addressBook.getBuddyInfo());
        }
        return "buddies";
    }

    @GetMapping("/addressbook/addBuddyForm")
    public String displayAddBuddyForm(Model model) {
        System.out.println("Hello we are here");
        model.addAttribute("addressBook", new AddressBook());
        return "addBuddy";
    }
//    @PostMapping("/addressbook/addBuddyForm")
//    public String submitAddBuddyForm(@ModelAttribute AddressBook addressBook,
//                                     @RequestParam String name,
//                                     @RequestParam String phoneNumber,
//                                     @RequestParam String address,
//                                     Model model) {
//
//        // Create new BuddyInfo object
//        BuddyInfo newBuddy = new BuddyInfo(name, phoneNumber, address);
//        buddyRepository.save(newBuddy);
//
//        AddressBook currentAddressBook = null;
//        if (addressBook.getId() != null) {
//            currentAddressBook = addressRepository.findById(addressBook.getId()).orElse(null);
//        }
//
//        if (currentAddressBook == null) {
//            // Handle the case where you're creating a new AddressBook
//            currentAddressBook = new AddressBook();
//        }
//
//        currentAddressBook.addBuddy(newBuddy);
//        addressRepository.save(currentAddressBook);
//
//        model.addAttribute("addressBook", currentAddressBook);
//        model.addAttribute("buddiesList", currentAddressBook.getBuddyInfo());
//
//        return "submitFormResult";
//    }


}
