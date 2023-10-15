import AddressBookLab.AddressBookMain;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = AddressBookMain.class)
public class TestHttpRequest {

    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testPostRequest() {
        // Define the request URL
        String url = "http://localhost:" + port + "/addressbook/";

        // Send the POST request and get the response entity
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, null, String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK); // Adjust the status code as needed

        // asserting response as JSON string
        String jsonResponse = responseEntity.getBody();
        assertThat(jsonResponse).isNotNull();

        // parsing the JSON
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);
            assertThat(jsonNode.has("id")).isTrue();
            assertThat(jsonNode.get("buddyInfo").isArray()).isTrue();
        } catch (Exception e) {
            // Handle JSON parsing exception
            e.printStackTrace();
        }
    }

    @Test
    public void testGetRequest() {
        int idToTest = 1;

        // Define the request URL with the dynamic ID
        String url = "http://localhost:" + port + "/addressbook/" + idToTest;

        // Send the GET request and get the response entity
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK); // Adjust the status code as needed

        String responseBody = responseEntity.getBody();
        assertThat(responseBody).isNotNull();

        // Ensure that the response body contains Jimmy's information
        assertThat(responseBody).contains("\"name\":\"Jimmy\",\"phoneNumber\":\"123-456-7777\"");

        // Ensure that the response body contains Dwayne's information
        assertThat(responseBody).contains("\"name\":\"Dwayne\",\"phoneNumber\":\"222-333-4444\"");

        // Ensure that the response body contains Chris's information
        assertThat(responseBody).contains("\"name\":\"Chris\",\"phoneNumber\":\"333-444-5555\"");

    }

    @Test
    public void testPutRequest() {
        int idToTest = 2;
        String addNewBuddyUrl = "http://localhost:" + port + "/addressbook/";
        // Send the POST request and get the response entity
        restTemplate.postForEntity(addNewBuddyUrl, null, String.class);

        // Define the request URL with the dynamic ID
        String url = "http://localhost:" + port + "/addressbook/" + idToTest + "/addBuddy";

        // Create the request body as a String
        String requestBody = "{\"name\":\"Mark\",\"phoneNumber\":\"111-222-3333\"}";

        // Send the PUT request and get the response entity
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // Create the HTTP entity with the request body and headers
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // Send the PUT request and get the response entity using the exchange method
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);

        // Assert the response
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK); // Adjust the status code as needed

        String responseBody = responseEntity.getBody();
        assertThat(responseBody).isNotNull();

        assertThat(responseBody).contains("\"name\":\"Mark\",\"phoneNumber\":\"111-222-3333\"");
    }

    @Test
    public void testDeleteRequest() {
        int idToTest = 1;
        int addressBookIdToTest = 1;
        // Removing Mark from the list
        int buddyToRemoveId = 4;

        // adding a new buddy that will be removed
        String addNewBuddyUrl = "http://localhost:" + port + "/addressbook/";
        // Define the request URL with the dynamic ID
        String url = "http://localhost:" + port + "/addressbook/" + idToTest + "/addBuddy";
        // Create the request body as a String
        String requestBody = "{\"name\":\"James\",\"phoneNumber\":\"333-777-8888\"}";
        // Send the PUT request and get the response entity
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        // Create the HTTP entity with the request body and headers
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        // Send the PUT request and get the response entity using the exchange method
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);

        String responseBody = responseEntity.getBody();
        assertThat(responseBody).isNotNull();
        // ensuring that James was added to the list before I delete it
        assertThat(responseBody).contains("\"name\":\"James\",\"phoneNumber\":\"333-777-8888\"");

        // Making a Delete request
        String url2 = "http://localhost:" + port + "/addressbook/" + addressBookIdToTest + "/removeBuddy/" + buddyToRemoveId;
        restTemplate.delete(url2);

        // Send a new GET request to retrieve the updated response
        ResponseEntity<String> updatedResponseEntity = restTemplate.getForEntity(url, String.class);

        // Get the updated response body
        String updatedResponseBody = updatedResponseEntity.getBody();
        assertThat(updatedResponseBody).isNotNull();

        // Check if the response contains the expected JSON structure
        assertThat(updatedResponseBody).doesNotContain("\"name\":\"James\",\"phoneNumber\":\"333-777-8888\"");
    }
}
