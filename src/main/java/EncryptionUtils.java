import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class EncryptionUtils {

    private static final String SECRET_KEY = "mksopqAZjlkaxHAWroserzxqlyEoGaqw";
    private static final String ALGORITHM = "AES";
    private static final String ALGORITHM_MODE_PADDING = "AES/GCM/NoPadding";

    public static String encode(String str) {
        try {
            byte[] keyData = SECRET_KEY.getBytes();
            SecretKey secureKey = new SecretKeySpec(keyData, ALGORITHM);
            Cipher c = Cipher.getInstance(ALGORITHM_MODE_PADDING);
            c.init(Cipher.ENCRYPT_MODE, secureKey, new GCMParameterSpec(128, SECRET_KEY.getBytes(StandardCharsets.UTF_8)));
            byte[] encrypted = c.doFinal(str.getBytes(StandardCharsets.UTF_8));
            return Base64.encodeBase64URLSafeString(encrypted);
        } catch (Exception e) {
            throw new IllegalArgumentException("암호화 실패");
        }
    }

    public static String decode(String str) {
        try {
            byte[] keyData = SECRET_KEY.getBytes();
            SecretKey secureKey = new SecretKeySpec(keyData, ALGORITHM);
            Cipher c = Cipher.getInstance(ALGORITHM_MODE_PADDING);
            c.init(Cipher.DECRYPT_MODE, secureKey, new GCMParameterSpec(128, SECRET_KEY.getBytes(StandardCharsets.UTF_8)));
            byte[] byteStr = Base64.decodeBase64(str.getBytes());
            return new String(c.doFinal(byteStr), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new IllegalArgumentException("복호화 실패");
        }
    }
}
