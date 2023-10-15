/**
 * @author Yash Kapoor
 * Student ID: 101163338
 */

import AddressBookLab.AddressBook;
import AddressBookLab.BuddyInfo;
import org.junit.Before;
import org.junit.Test;

import jakarta.persistence.*;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests each method in the AddressBook Class
 */
public class TestAddressBook {

    private AddressBook addressBook;
    private BuddyInfo buddy1;
    private BuddyInfo buddy2;

    /**
     * Initializes two BuddyInfo objects and an AddressBook object that
     * will be used to ensure each method in the AddressBook class works
     * correctly
     */
    @Before
    public void initialize() {
        addressBook = new AddressBook();
        buddy1 = new BuddyInfo("Bella", "666-777-8888");
        buddy2 = new BuddyInfo("Andy", "777-888-9999");
    }

    /**
     * Tests the addBuddy() method
     */
    @Test
    public void testAddBuddy() {
        assertEquals(0, addressBook.getBuddyInfo().size());
        addressBook.addBuddy(buddy1);
        assertEquals(1, addressBook.getBuddyInfo().size());
        assertTrue(addressBook.getBuddyInfo().contains(buddy1));

        addressBook.addBuddy(buddy2);
        assertEquals(2, addressBook.getBuddyInfo().size());
        assertTrue(addressBook.getBuddyInfo().contains(buddy2));
    }

    /**
     * Tests the removeBuddy() method
     */
    @Test
    public void testRemoveBuddy() {
        addressBook.addBuddy(buddy1);
        addressBook.addBuddy(buddy2);

        assertEquals(2, addressBook.getBuddyInfo().size());
        addressBook.removeBuddy(buddy1);
        assertFalse(addressBook.getBuddyInfo().contains(buddy1));
        assertEquals(1, addressBook.getBuddyInfo().size());

        addressBook.removeBuddy(buddy2);
        assertEquals(0, addressBook.getBuddyInfo().size());
        assertFalse(addressBook.getBuddyInfo().contains(buddy2));
    }

    /**
     * Tests the getBuddyInfo() method
     */
    @Test
    public void testGetBuddyInfo() {
        String firstBuddyName = buddy1.getName();
        String firstBuddyPhoneNumber = buddy1.getPhoneNumber();

        assertEquals("Bella", firstBuddyName);
        assertEquals("666-777-8888", firstBuddyPhoneNumber);

        String secondBuddyName = buddy2.getName();
        String secondBuddyPhoneNumber = buddy2.getPhoneNumber();

        assertEquals("Andy", secondBuddyName);
        assertEquals("777-888-9999", secondBuddyPhoneNumber);
    }

    /**
     * Testing whether the AddressBook Object can be persisted
     */
//    @Test
//    public void performJPAForAddressBook() {
//
//        AddressBook addressBook = new AddressBook();
//
//        BuddyInfo buddy1 = new BuddyInfo();
//        buddy1.setId(1);
//        buddy1.setName("Jimmy");
//        buddy1.setPhoneNumber("111-222-3333");
//
//        BuddyInfo buddy2 = new BuddyInfo();
//        buddy2.setId(2);
//        buddy2.setName("Alan");
//        buddy2.setPhoneNumber("222-3333-4444");
//
//        addressBook.setId(1);
//        addressBook.addBuddy(buddy1);
//        addressBook.addBuddy(buddy2);
//
////        // Connecting to the database through EntityManagerFactory
////        // connection details loaded from persistence.xml
////        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-test");
////        EntityManager em = emf.createEntityManager();
////        // Creating a new transaction
////        EntityTransaction tx = em.getTransaction();
//
////        tx.begin();
////
////        // Persisting the AddressBook also persists the BuddyInfo Objects
////        // since I used the cascade annotation in the AddressBook Class
////        em.persist(addressBook);
////
////        tx.commit();
//
////        // Querying the contents of the database using JPQL query
////        Query q = em.createQuery("SELECT b FROM BuddyInfo b");
//
//        @SuppressWarnings("unchecked")
//        List<BuddyInfo> results = q.getResultList();
//
//        System.out.println("List of buddies\n----------------");
//
//        List <BuddyInfo> buddyInfoList = addressBook.getBuddyInfo();
//        for (BuddyInfo buddyInfo : buddyInfoList) {
//            System.out.println("Buddy name: " + buddyInfo.getName() + " (id=" + buddyInfo.getId() + ")");
//            System.out.println("Buddy phone number: " + buddyInfo.getPhoneNumber() + " (id=" + buddyInfo.getId() + ")");
//        }
//
//        // Closing connection
//        em.close();
//
//        emf.close();
//    }

}
