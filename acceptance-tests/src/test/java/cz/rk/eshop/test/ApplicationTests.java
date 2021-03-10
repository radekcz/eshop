package cz.rk.eshop.test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ApplicationTests {

    private static final String ENDPOINT = "http://localhost:8080/watches";

    private RestTemplate restTemplate;
    private ObjectMapper mapper;

    @BeforeAll
    public void setup() {
        restTemplate = new RestTemplate();
        mapper = new ObjectMapper();
    }

    @Test
    public void shouldCreateNewWatch() throws Exception {
        // create JSON input
        String jsonPayload = createJSONInput();

        ResponseEntity<String> response = restTemplate.postForEntity(ENDPOINT, jsonPayload, String.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

        JsonNode root = mapper.readTree(response.getBody());
        JsonNode name = root.path("title");
        Assertions.assertNotNull(name.asText());
    }

    @Test
    public void shouldReadWatch() throws Exception {
        // TODO: first create new watch to test

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(ENDPOINT + "/1", String.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode name = root.path("title");
        Assertions.assertNotNull(name.asText());
    }

    private String createJSONInput() {
        // create a JSON object
        ObjectNode node = mapper.createObjectNode();
        node.put("title", "Casio");
        node.put("price", 120000);
        node.put("description", "Great watch!");
        node.put("fountain", "R0lGODlhAQABAIAAAAUEBAAAACwAAAAAAQABAAACAkQBADs=");
        return node.toString();
    }

}
