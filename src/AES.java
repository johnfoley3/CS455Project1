import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @author John Foley
 * @date 3/12/15
 * @since 1.0.0.0
 */
public class AES {

    /**
     * Constructor
     * @param baos
     * @param timer
     */
    public AES(ByteArrayOutputStream baos, TimesWriter timer) {

        // Start timer
        long startTime = System.nanoTime();

        // Initialize key containers, 32 bytes = 256 bits
        byte[] key32 = new byte[32];
        byte[] output;

        // Fill with random bytes
        new Random().nextBytes(key32);

        try {

            // Generate AES key from random byte code
            SecretKeySpec keySpec = new SecretKeySpec(key32, "AES");

            Cipher AESCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            AESCipher.init(Cipher.ENCRYPT_MODE, keySpec);

            // Encrypts using 128 bit block as per specification
            output = AESCipher.doFinal(baos.toByteArray());

            long endTime = System.nanoTime();

            timer.recordTime("AES", endTime-startTime);

            FileOutputStream outputFile_jpg =
                    new FileOutputStream("C:\\Users\\John\\IdeaProjects\\CS455Project1\\src\\img\\AES.jpg");

            FileOutputStream outputFile_txt =
                    new FileOutputStream("C:\\Users\\John\\IdeaProjects\\CS455Project1\\src\\docs\\AES.txt");

            outputFile_txt.write(output);
            outputFile_txt.close();

            outputFile_jpg.write(output);
            outputFile_jpg.close();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("No such algorithm! AES");
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            System.out.println("No such padding! AES");
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            System.out.println("Invalid Key Exception thrown. AES");
            e.printStackTrace();
        } catch (BadPaddingException e) {
            System.out.println("Bad Padding Exception thrown. AES");
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            System.out.println("Illegal Block Size Exception thrown! AES");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("Could not store AES encrypted image. AES");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Could not write to file. AES");
            e.printStackTrace();
        }
    }
}
