package cz.rk.eshop.test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


/**
 * Acceptance tests
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ApplicationTest {

    private static final String ENDPOINT = "http://localhost:8080/eshop/v1/watches";

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
        String jsonPayload = createJSONInputCorrect();

        ResponseEntity<String> response = executePost(jsonPayload);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        JsonNode root = mapper.readTree(response.getBody());

        JsonNode name = root.path("title");
        Assertions.assertNotNull(name.asText());

        JsonNode fountain = root.path("fountain");
        Assertions.assertNotNull(fountain.asText());
    }

    @Test
    public void shouldCreateNewWatch_withoutFountain() throws Exception {
        // create JSON input
        String jsonPayload = createJSONInputCorrectWithoutFountain();

        ResponseEntity<String> response = executePost(jsonPayload);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        JsonNode root = mapper.readTree(response.getBody());

        JsonNode fountain = root.path("fountain");
        Assertions.assertTrue(fountain.isNull());
    }

    @Test
    public void shouldNotCreateNewWatch_emptyTitle() throws Exception {
        // create JSON input with empty title
        String jsonPayload = createJSONInputWithEmptyTitle();

        HttpClientErrorException httpClientErrorException = Assertions.assertThrows(HttpClientErrorException.class, () -> {
            executePost(jsonPayload);
        });
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, httpClientErrorException.getStatusCode());
    }

    @Test
    public void shouldNotCreateNewWatch_badFountain() throws Exception {
        // create JSON input with bad parameter "fountain"
        String jsonPayload = createJSONInputWithBadFountain();

        HttpClientErrorException httpClientErrorException = Assertions.assertThrows(HttpClientErrorException.class, () -> {
            executePost(jsonPayload);
        });
        Assertions.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, httpClientErrorException.getStatusCode());
    }

    @Test
    public void shouldReadWatch() throws Exception {
        // first create new watch to test
        // create JSON input
        String jsonPayload = createJSONInputCorrect();
        // post
        ResponseEntity<String> responseCreate = executePost(jsonPayload);
        Assertions.assertEquals(HttpStatus.CREATED, responseCreate.getStatusCode());

        JsonNode rootCreate = mapper.readTree(responseCreate.getBody());
        JsonNode id = rootCreate.path("id");
        Assertions.assertNotNull(id.asText());

        // then try to get recently created watch
        ResponseEntity<String> responseGet = restTemplate.getForEntity(ENDPOINT + "/"+id, String.class);
        Assertions.assertEquals(HttpStatus.OK, responseGet.getStatusCode());

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

    private String createJSONInput(String title, String fountain, boolean appendFountain) {
        // create a JSON object
        ObjectNode node = mapper.createObjectNode();
        node.put("title", title);
        node.put("price", 120000);
        node.put("description", "Great watch!");
        if (appendFountain)
            node.put("fountain", fountain);
        return node.toString();
    }

    private String createJSONInputCorrect(){
        return createJSONInput("Casio", "R0lGODlhAQABAIAAAAUEBAAAACwAAAAAAQABAAACAkQBADs=", true);
    }

    private String createJSONInputCorrectWithoutFountain(){
        return createJSONInput("Longines", "", false);
    }

    private String createJSONInputWithBadFountain(){
        return createJSONInput("Prim", "R=a", true);
    }

    private String createJSONInputWithEmptyTitle(){
        return createJSONInput("", "YWhvag==", true);
    }

}
