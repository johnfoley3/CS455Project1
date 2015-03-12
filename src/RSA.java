import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @author John Foley
 * @date 3/12/15
 * @since 1.0.0.0
 */
public class RSA {

    /**
     * Constructor
     * @param baos
     * @param timer
     */
    public RSA(ByteArrayOutputStream baos, TimesWriter timer) {

        // Start timer
        long startTime = System.nanoTime();

        byte[] output = new byte[0];

        try {

            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(512);
            final KeyPair key = keyGen.generateKeyPair();

            Cipher RSACipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

            RSACipher.init(Cipher.ENCRYPT_MODE, key.getPublic());

            // Put input stream into byte array to work with
            byte[] clearTextByteArray = baos.toByteArray();
            int prev = 0;

            // Encrypt in blocks of 16 bytes, or 128 bits
            while (prev < clearTextByteArray.length - 16) {
                // Encrypts using 128 bit block as per specification
                output = concatenateByteArrays(output,
                        RSACipher.doFinal(Arrays.copyOfRange(clearTextByteArray, prev, prev+16)));
                prev += 16;
            }

            output = concatenateByteArrays(output,
                    RSACipher.doFinal(Arrays.copyOfRange(clearTextByteArray, prev,
                            prev + ((clearTextByteArray.length % 16) - 1))));


            long endTime = System.nanoTime();

            timer.recordTime("RSA", endTime-startTime);

            FileOutputStream outputFile_jpg =
                    new FileOutputStream("C:\\Users\\John\\IdeaProjects\\CS455Project1\\src\\img\\RSA.jpg");

            FileOutputStream outputFile_txt =
                    new FileOutputStream("C:\\Users\\John\\IdeaProjects\\CS455Project1\\src\\docs\\RSA.txt");

            outputFile_txt.write(output);
            outputFile_txt.close();

            outputFile_jpg.write(output);
            outputFile_jpg.close();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("No such algorithm! RSA");
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            System.out.println("No such padding! RSA");
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            System.out.println("Invalid Key Exception thrown. RSA");
            e.printStackTrace();
        } catch (BadPaddingException e) {
            System.out.println("Bad Padding Exception thrown. RSA");
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            System.out.println("Illegal Block Size Exception thrown! RSA");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("Could not store RSA encrypted image. RSA");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Could not write to file. RSA");
            e.printStackTrace();
        }
    }

    /**
     * Concatenates byte arrays
     * @param a
     * @param b
     * @return
     */
    static byte[] concatenateByteArrays(byte[] a, byte[] b) {
        byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }
}
