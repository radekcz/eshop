package cz.rk.eshop.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.rk.eshop.entity.Watch;

/**
 * Watch processor
 */
public class WatchProcessor {

    public static Watch processInputJSON(String watchPayload) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(watchPayload, Watch.class);
    }
}
