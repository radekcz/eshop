package cz.rk.eshop.utils;

import javax.persistence.AttributeConverter;
import java.util.Base64;

/**
 * Watch converter
 */
public class WatchConverter implements AttributeConverter<String, byte[]> {

    @Override
    public byte[] convertToDatabaseColumn(String string) {
        if (!string.isBlank())
            return Base64.getDecoder().decode(string);
        else
            return null;
    }

    @Override
    public String convertToEntityAttribute(byte[] bytes) {
        if (bytes.length > 0)
            return Base64.getEncoder().encodeToString(bytes);
        else
            return null;
    }
}
