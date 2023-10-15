/**
 * @author Yash Kapoor
 * Student ID: 101163338
 */

package AddressBookLab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AddressBookMain {
    private static final Logger log = LoggerFactory.getLogger(AddressBookMain.class);
    public static void main(String[] args) {

        SpringApplication.run(AddressBookMain.class, args);
    }

    @Bean
    public CommandLineRunner demo(AddressRepository addressRepository, BuddyRepository buddyInfoRepository) {
        return (args) -> {

            BuddyInfo buddy1 = new BuddyInfo("Jimmy", "123-456-7777", "22 Fake Street");
            BuddyInfo buddy2 = new BuddyInfo("Dwayne", "222-333-4444", "32 Fake Street");
            BuddyInfo buddy3 = new BuddyInfo("Chris", "333-444-5555", "42 Fake Street");

            // Save the BuddyInfo objects using BuddyInfoRepository
            buddyInfoRepository.save(buddy1);
            buddyInfoRepository.save(buddy2);
            buddyInfoRepository.save(buddy3);

            AddressBook addressBook = new AddressBook();

            // Add BuddyInfo objects to AddressBook
            addressBook.addBuddy(buddy1);
            addressBook.addBuddy(buddy2);
            addressBook.addBuddy(buddy3);

            // Save the AddressBook using AddressRepository
            addressRepository.save(addressBook);

            // Fetch all AddressBooks
            log.info("AddressBook found with findAll():");
            log.info("-------------------------------");
            for (AddressBook ab : addressRepository.findAll()) {
                log.info(ab.toString());
                for (BuddyInfo buddy : ab.getBuddyInfo()) {
                    // Printing the name and phone number of each buddy stored in the AddressBook
                    log.info(buddy.toString());
                }
            }
            log.info("");
        };
    }
}
