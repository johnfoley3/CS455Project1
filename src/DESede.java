import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @author John Foley
 * @date 3/12/15
 * @since 1.0.0.0
 */
public class DESede {

    /**
     * Constructor
     * @param baos Contains the clear text
     * @param timer  Writes the run time to file
     */
    public DESede(ByteArrayOutputStream baos, TimesWriter timer) {

        // Start timer
        long startTime = System.nanoTime();

        // Initialize key containers, 32 bytes = 256 bits
        byte[] key21 = new byte[24];
        byte[] output;

        // Fill with random bytes
        new Random().nextBytes(key21);

        try {

            // Generate DESede key from random byte code
            SecretKeySpec keySpec = new SecretKeySpec(key21, "DESede");

            Cipher DES3Cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");

            final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
            DES3Cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);

            // Encrypts using 128 bit block as per specification
            output = DES3Cipher.doFinal(baos.toByteArray());

            long endTime = System.nanoTime();

            timer.recordTime("3DES", endTime-startTime);

            FileOutputStream outputFile_jpg =
                    new FileOutputStream("img/3DES.jpg");

            FileOutputStream outputFile_txt =
                    new FileOutputStream("docs/3DES.txt");

            outputFile_txt.write(output);
            outputFile_txt.close();

            outputFile_jpg.write(output);
            outputFile_jpg.close();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("No such algorithm! 3DES");
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            System.out.println("No such padding! 3DES");
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            System.out.println("Invalid Key Exception thrown. 3DES");
            e.printStackTrace();
        } catch (BadPaddingException e) {
            System.out.println("Bad Padding Exception thrown. 3DES");
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            System.out.println("Illegal Block Size Exception thrown! 3DES");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("Could not store AES encrypted image. 3DES");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Could not write to file. 3DES");
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            System.out.println("Invalid Algorithm Parameter Exception. 3DES");
            e.printStackTrace();
        }
    }
}
