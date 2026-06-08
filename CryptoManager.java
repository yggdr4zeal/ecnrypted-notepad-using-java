import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public class CryptoManager {
    // Properti rahasia, hanya bisa diakses di dalam class ini (Encapsulation)
    private static final String ALGORITHM = "AES";

    // Method untuk menghasilkan kunci rahasia berdasarkan input password user
    private static SecretKeySpec generateKey(String password) throws Exception {
        byte[] key = password.getBytes("UTF-8");
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16); // Menggunakan 128-bit AES
        return new SecretKeySpec(key, ALGORITHM);
    }

    // Method Enkripsi
    public static String encryptText(String plainText, String password) throws Exception {
        SecretKeySpec secretKey = generateKey(password);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Method Dekripsi
    public static String decryptText(String encryptedText, String password) throws Exception {
        SecretKeySpec secretKey = generateKey(password);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes, "UTF-8");
    }
}