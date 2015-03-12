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
public class DES {

    /**
     * Constructor
     * @param baos
     * @param timer
     */
    public DES(ByteArrayOutputStream baos, TimesWriter timer) {

        // Start timer
        long startTime = System.nanoTime();

        // Initialize key containers, 32 bytes = 256 bits
        byte[] key8 = new byte[8];
        byte[] output;

        // Fill with random bytes
        new Random().nextBytes(key8);

        try {

            // Generate DES key from random byte code
            SecretKeySpec keySpec = new SecretKeySpec(key8, "DES");

            Cipher DESCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

            DESCipher.init(Cipher.ENCRYPT_MODE, keySpec);

            // Encrypts using 128 bit block as per specification
            output = DESCipher.doFinal(baos.toByteArray());

            long endTime = System.nanoTime();

            timer.recordTime("DES", endTime-startTime);

            FileOutputStream outputFile_jpg =
                    new FileOutputStream("C:\\Users\\John\\IdeaProjects\\CS455Project1\\src\\img\\DES.jpg");

            FileOutputStream outputFile_txt =
                    new FileOutputStream("C:\\Users\\John\\IdeaProjects\\CS455Project1\\src\\DES.txt");

            outputFile_txt.write(output);
            outputFile_txt.close();

            outputFile_jpg.write(output);
            outputFile_jpg.close();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("No such algorithm! DES");
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            System.out.println("No such padding! DES");
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            System.out.println("Invalid Key Exception thrown. DES");
            e.printStackTrace();
        } catch (BadPaddingException e) {
            System.out.println("Bad Padding Exception thrown. DES");
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            System.out.println("Illegal Block Size Exception thrown! DES");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("Could not store AES encrypted image. DES");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Could not write to file. DES");
            e.printStackTrace();
        }
    }
}
