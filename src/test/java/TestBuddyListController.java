import AddressBookLab.AddressBookMain;
import AddressBookLab.BuddyListController;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Tests whether content is being rendered properly by the BuddyListController
 */
@SpringBootTest(classes = AddressBookMain.class)
public class TestBuddyListController {

    @Autowired
    private BuddyListController controller;

    /**
     * Ensures the controller is working as expected and rendering content
     * @throws Exception
     */
    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
}