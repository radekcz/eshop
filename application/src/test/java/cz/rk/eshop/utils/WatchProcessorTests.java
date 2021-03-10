package cz.rk.eshop.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import cz.rk.eshop.entity.Watch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Watch processor tests
 */
public class WatchProcessorTests {

    @Test
    public void testProcessInputJSON() throws JsonProcessingException {
        String title = "Longines";
        String description = "Great watch!";
        String price = "123987";
        String jsonContent = "{\"title\":\"" + title + "\", \"description\":\"" + description + "\", \"price\":" + price + "}";
        Watch watch = WatchProcessor.processInputJSON(jsonContent);

        Assertions.assertNotNull(watch);
        Assertions.assertEquals(title, watch.getTitle());
        Assertions.assertEquals(description, watch.getDescription());
        Assertions.assertEquals(price, watch.getPrice()+"");
    }

}
