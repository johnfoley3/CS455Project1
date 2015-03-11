import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

class Foley {

    public static void main(String[] args) {

        BufferedImage targetImage;
        int width = 0;

        // Initialize key containers
        byte[] key56 = new byte[56];
        byte[] key168 = new byte[168];
        byte[] key256 = new byte[256];

        // Fill with random bytes
        new Random().nextBytes(key56);
        new Random().nextBytes(key168);
        new Random().nextBytes(key256);

        String imgPath = "C:\\Users\\John\\IdeaProjects\\CS455Project1\\src\\img\\Target.jpg";
		File targetFile = new File(imgPath);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        encryptAES(baos);
        try {

            // Read the image, then pipe to byte array
            targetImage = ImageIO.read(targetFile);
            ImageIO.write(targetImage, "jpg", baos);


        } catch (IOException e) {

            System.out.println("Could not open Buffered Image");
            e.printStackTrace();
        }
    }

    public static void encryptDES(ByteArrayOutputStream baos) {

        // Initialize key containers
        byte[] key56 = new byte[56];

        // Fill with random bytes
        new Random().nextBytes(key56);

        final String DES_ENCRYPTION_SCHEME = "DES";
    }

    public static void encrypt3DES(ByteArrayOutputStream baos) {

        // Initialize key containers
        byte[] key168 = new byte[168];

        // Fill with random bytes
        new Random().nextBytes(key168);

        final String DES_3_ENCRYPTION_SCHEME = "3DES";
    }

    public static void encryptAES(ByteArrayOutputStream baos) {

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

            FileOutputStream outputFile =
                    new FileOutputStream("C:\\Users\\John\\IdeaProjects\\CS455Project1\\src\\img\\AES.jpg");

            outputFile.write(output);
            outputFile.close();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("No such algorithm!");
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            System.out.println("No such padding!");
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            System.out.println("Invalid Key Exception thrown.");
            e.printStackTrace();
        } catch (BadPaddingException e) {
            System.out.println("Bad Padding Exception thrown.");
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            System.out.println("Illegal Block Size Exception thrown!.");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("Could not store AES encrypted image.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Could not write to file.");
            e.printStackTrace();
        }
    }

    public static void encryptRSA(ByteArrayOutputStream baos) {

        // Initialize key containers
        byte[] key256 = new byte[256];

        // Fill with random bytes
        new Random().nextBytes(key256);

        final String RSA_ENCRYPTION_SCHEME = "RSA";
    }
}