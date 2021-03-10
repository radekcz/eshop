package cz.rk.eshop.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.rk.eshop.entity.Watch;
import java.beans.PropertyEditorSupport;


/**
 * Watch editor
 */
public class WatchEditor extends PropertyEditorSupport {

    private ObjectMapper objectMapper;


    public WatchEditor(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text.isEmpty()) {
            setValue(null);
        } else {
            Watch watch = new Watch();
            try {
                watch = objectMapper.readValue(text, Watch.class);
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException(e);
            }
            setValue(watch);
        }
    }

}
