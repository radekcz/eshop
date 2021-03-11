package cz.rk.eshop.utils;

import cz.rk.eshop.exception.WatchBadParameterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;


/**
 * Watch fountain converter
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WatchFountainConverterTest {

    private WatchFountainConverter watchFountainConverter;


    @BeforeAll
    public void setup() {
        watchFountainConverter = new WatchFountainConverter();
    }

    @Test
    public void testConvertBase46ByteArray_successful() {
        String fountain = "R0lGODlhAQABAIAAAAUEBAAAACwAAAAAAQABAAACAkQBADs=";

        byte[] bytes = watchFountainConverter.convertToDatabaseColumn(fountain);
        Assertions.assertEquals(fountain, watchFountainConverter.convertToEntityAttribute(bytes));
    }

    @Test
    public void testConvertBase46ByteArray_badInput() {
        String fountain = "R0lGODlhAQABAIAAAAUEBAAAACwAAAAAAQABAAACAkQBADs=a";

        Assertions.assertThrows(WatchBadParameterException.class, () -> {
            watchFountainConverter.convertToDatabaseColumn(fountain);
        });
    }
}
