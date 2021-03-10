package cz.rk.eshop.test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

/**
 * Acceptance tests
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ApplicationTest {

    private static final String ENDPOINT = "http://localhost:8080/eshop/watches";

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

        ResponseEntity<String> response = executePost(jsonPayload);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);

        JsonNode root = mapper.readTree(response.getBody());
        JsonNode name = root.path("title");
        Assertions.assertNotNull(name.asText());
    }

    @Test
    public void shouldReadWatch() throws Exception {
        // first create new watch to test
        // create JSON input
        String jsonPayload = createJSONInput();
        // post
        ResponseEntity<String> responseCreate = executePost(jsonPayload);
        Assertions.assertEquals(responseCreate.getStatusCode(), HttpStatus.CREATED);

        JsonNode rootCreate = mapper.readTree(responseCreate.getBody());
        JsonNode id = rootCreate.path("id");
        Assertions.assertNotNull(id.asText());

        // then try to get recently created watch
        ResponseEntity<String> responseGet = restTemplate.getForEntity(ENDPOINT + "/"+id, String.class);
        Assertions.assertEquals(responseGet.getStatusCode(), HttpStatus.OK);

        JsonNode rootGet = mapper.readTree(responseGet.getBody());
        JsonNode fountain = rootGet.path("title");
        Assertions.assertNotNull(fountain.asText());
    }

    private ResponseEntity<String> executePost(String jsonPayload) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(jsonPayload,headers);
        return restTemplate.postForEntity(ENDPOINT, entity, String.class);
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
