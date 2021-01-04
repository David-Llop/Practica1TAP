package part2;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class CipherEncripter implements Strategy{

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
