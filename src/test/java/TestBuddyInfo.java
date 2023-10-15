/**
 * @author Yash Kapoor
 * Student ID: 101163338
 */

import AddressBookLab.BuddyInfo;
import org.junit.Before;
import org.junit.Test;

import jakarta.persistence.*;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests each method in the BuddyInfo Class
 */
public class TestBuddyInfo {
    private BuddyInfo buddy;

    /**
     * Initializes a new BuddyInfo object that is going to be used to
     * ensure each method in the BuddyInfo class works correctly
     */
    @Before
    public void initialize() {
        buddy = new BuddyInfo("Bella", "666-777-8888");
    }

    /**
     * Tests the getName() method
     */
    @Test
    public void testGetName() {
        assertEquals("Bella", buddy.getName());
    }

    /**
     * Tests the getPhoneNumber() method
     */
    @Test
    public void testGetPhoneNumber() {
        assertEquals("666-777-8888", buddy.getPhoneNumber());
    }

    /**
     * Tests the overridden toString() method
     */
    @Test
    public void testToString() {
        String expected = "Name of Buddy: Bella, Phone Number of Buddy: 666-777-8888";
        assertEquals(expected, buddy.toString());
    }

    /**
     * Testing whether the BuddyInfo Object can be persisted
     */
//    @Test
//    public void performJPAForBuddyInfo() {
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
//        // Connecting to the database through EntityManagerFactory
//        // connection details loaded from persistence.xml
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-test");
//
//        EntityManager em = emf.createEntityManager();
//
//        // Creating a new transaction
//        EntityTransaction tx = em.getTransaction();
//
//        tx.begin();
//
//        em.persist(buddy1);
//        em.persist(buddy2);
//
//        tx.commit();
//
//        // Querying the contents of the database using JPQL query
//        Query q = em.createQuery("SELECT b FROM BuddyInfo b");
//
//        @SuppressWarnings("unchecked")
//        List<BuddyInfo> results = q.getResultList();
//
//        System.out.println("List of buddies\n----------------");
//
//        for (BuddyInfo b : results) {
//            System.out.println("Buddy name: " + b.getName() + " (id=" + b.getId() + ")");
//            System.out.println("Buddy phone number: " + b.getPhoneNumber() + " (id=" + b.getId() + ")");
//        }
//
//        // Closing connection
//        em.close();
//
//        emf.close();
//    }
}
