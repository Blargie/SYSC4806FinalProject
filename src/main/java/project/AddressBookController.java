package project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/addressBook")
public class AddressBookController {
    @Autowired
    private AddressBookRepository addressBookRepository;

    @Autowired
    private BuddyInfoRepository buddyInfoRepository;

    //Create
    @GetMapping("/create")
    public String createAddressBookForm(Model model) {
        model.addAttribute("addressBookId", null);
        return "create";
    }

    @PostMapping("/create")
    public String createAddressBookSubmit(@ModelAttribute AddressBook addressBook,Model model) {
        AddressBook newAddressBook = new AddressBook();
        addressBookRepository.save(newAddressBook);
        model.addAttribute("addressBookId", newAddressBook.getId());
        return "create";
    }

    //Display project.AddressBook
    @GetMapping("/{id}")
    public String getAddressBookForm(@PathVariable int id, Model model) {
        Optional<AddressBook> addressBook = addressBookRepository.findById(id);
        model.addAttribute("id", id);
        if (addressBook.isPresent()) {
            return "addressBook";
        } else {
            return "error";
        }
    }

    //Get Buddies
    @GetMapping("/{id}/getBuddies")
    public String getBuddiesForm(@PathVariable int id, Model model) {
        Optional<AddressBook> addressBook = addressBookRepository.findById(id);
        if (addressBook.isPresent()) {
            model.addAttribute("buddies", addressBook.get().getBuddyList());
            model.addAttribute("addressBookId", id);
            return "getBuddies";
        } else {
            return "error";
        }
    }
    //Add Buddy
    @GetMapping("/{id}/addBuddy")
    public String addBuddyForm(@PathVariable int id, Model model) {
        Optional<AddressBook> addressBook = addressBookRepository.findById(id);
        model.addAttribute("buddyInfo", new BuddyInfo());
        if (addressBook.isPresent()) {
            return "addBuddy";
        } else {
            return "error";
        }
    }

    @PostMapping("/{id}/addBuddy")
    public String addBuddySubmit(@PathVariable int id, @ModelAttribute BuddyInfo buddyInfo, Model model) {
        Optional<AddressBook> addressBook = addressBookRepository.findById(id);
        if (addressBook.isPresent()) {
            buddyInfo.setId(null);
            buddyInfoRepository.save(buddyInfo);
            System.out.println(buddyInfo.toString());
            addressBook.get().addBuddy(buddyInfo);
            addressBookRepository.save(addressBook.get());
            model.addAttribute("buddyInfo", buddyInfo);
            model.addAttribute("addressBookId", id);
            return "addBuddyResult";
        } else {
            return "error";
        }
    }

    //Remove Buddy
    @GetMapping("/{id}/removeBuddy")
    public String removeBuddyForm(@PathVariable int id, Model model) {
        Optional<AddressBook> addressBook = addressBookRepository.findById(id);
        if (addressBook.isPresent()) {
            model.addAttribute("buddies", addressBook.get().getBuddyList());
            model.addAttribute("addressBookId", id);
            return "removeBuddy";
        } else {
            System.out.println("ERROR");
            return "error";
        }
    }
    @PostMapping("/{id}/removeBuddy")
    public String removeBuddy(@PathVariable int id, @RequestParam("selectedBuddyId") int selectedBuddyId, Model model) {
        Optional<AddressBook> addressBook = addressBookRepository.findById(id);
        if (addressBook.isPresent()) {
            Optional<BuddyInfo> buddy = buddyInfoRepository.findById(selectedBuddyId);
            if (buddy.isPresent()) {
                BuddyInfo removedBuddy = addressBook.get().removeBuddy(buddy.get());
                if (removedBuddy != null) {
                    addressBookRepository.save(addressBook.get());
                    model.addAttribute("buddyInfo", removedBuddy);
                    model.addAttribute("addressBookId", id);
                    return "removeBuddyResult";
                } else {
                    return "error";
                }
            } else {
                return "error";
            }
        } else {
            return "error";
        }
    }


    @GetMapping("/test")
    public String test(@RequestParam(name="test", required = false, defaultValue = "test") String name, Model model) {
        model.addAttribute("test", name);
        return "test";
    }

}
