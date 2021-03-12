package cz.rk.eshop.utils;

import cz.rk.eshop.exception.WatchBadValueParameterException;
import javax.persistence.AttributeConverter;
import java.util.Base64;

/**
 * Watch fountain converter
 *
 * - converts Base64 String to byte array
 */
public class WatchFountainConverter implements AttributeConverter<String, byte[]> {

    @Override
    public byte[] convertToDatabaseColumn(String string) throws WatchBadValueParameterException {
        if (string == null)
            return null;
        try { return Base64.getDecoder().decode(string); }
        catch (Exception exception) {
            throw new WatchBadValueParameterException();
        }
    }

    @Override
    public String convertToEntityAttribute(byte[] bytes) throws WatchBadValueParameterException {
        if (bytes == null)
            return null;
        try { return Base64.getEncoder().encodeToString(bytes); }
        catch (Exception exception) {
            throw new WatchBadValueParameterException();
        }
    }
}
