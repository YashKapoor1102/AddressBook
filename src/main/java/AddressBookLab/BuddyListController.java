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
}
