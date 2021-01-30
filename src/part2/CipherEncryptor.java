package part2;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Class implementing a Cipher Encryptor
 * @author David Llop Roig
 * @author Anna Julia Naval
 */
public class CipherEncryptor implements IStrategy {

    String key = "IWantToPassTAP12"; // 128 bit key
    java.security.Key aesKey =
            new javax.crypto.spec.SecretKeySpec(key.getBytes(), "AES");
    Cipher cipher;

    {
        try {
            cipher = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Given a string, returns it encoded
     * @param message message to encode
     * @return message encoded
     */

    @Override
    public String encode(String message) {
        byte[] encrypted = new byte[0];
        try {
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            encrypted = cipher.doFinal(message.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(encrypted);
    }

    /**
     * Given an encoded string, decodes it
     * @param message encoded message
     * @return decoded message
     */
    @Override
    public String decode(String message) {
        byte[] encrypted = Base64.getDecoder().decode(message.getBytes());
        String decrypted = null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            decrypted = new String(cipher.doFinal(encrypted));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decrypted;
    }
}
