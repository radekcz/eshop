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
        long price = 123987;
        String fountain = "R0lGODlhAQABAIAAAAUEBAAAACwAAAAAAQABAAACAkQBADs=";
        String jsonContent = "{\"title\":\"" + title + "\", \"description\":\"" + description + "\", \"price\":" + price + ", \"fountain\":\"" + fountain + "\"}";
        Watch watch = WatchProcessor.processInputJSON(jsonContent);

        Assertions.assertNotNull(watch);
        Assertions.assertEquals(title, watch.getTitle());
        Assertions.assertEquals(description, watch.getDescription());
        Assertions.assertEquals(price, watch.getPrice());
        Assertions.assertEquals(fountain, watch.getFountain());
    }

//    @Test
//    public void testProcessInputJSON2() throws JsonProcessingException {
//        String jsonContent = "{\"title\":\"Longines\", \"description\":\"Great watch!\", \"price\":123987}";
//        Watch watch = WatchProcessor.processInputJSON(jsonContent);
//
//        Assertions.assertNotNull(watch);
//        Assertions.assertEquals("Longines", watch.getTitle());
//        Assertions.assertEquals("Great watch!", watch.getDescription());
//        Assertions.assertEquals(123987, watch.getPrice());
//    }

}
