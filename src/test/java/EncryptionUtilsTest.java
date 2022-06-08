import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EncryptionUtilsTest {

    private static final String PRG_IDX = "PRG202205250003";

    @Test
    void testDecode() {
        //given
        String encodingString = EncryptionUtils.encode(PRG_IDX);

        //when
        String decodingString = EncryptionUtils.decode(encodingString);

        //then
        assertEquals(PRG_IDX, decodingString);
    }
}