import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.security.*;
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

        encryptDES(baos);
        encrypt3DES(baos);
        encryptAES(baos);
        encryptRSA(baos);
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

            System.out.format("DES: %d \n", endTime - startTime);

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

    public static void encrypt3DES(ByteArrayOutputStream baos) {

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

            Cipher DES3Cipher = Cipher.getInstance("DESede/CBC/NoPadding");

            final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
            DES3Cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);

            // Encrypts using 128 bit block as per specification
            output = DES3Cipher.doFinal(baos.toByteArray());

            long endTime = System.nanoTime();

            System.out.format("3DES: %d \n", endTime - startTime);

            FileOutputStream outputFile_jpg =
                    new FileOutputStream("C:\\Users\\John\\IdeaProjects\\CS455Project1\\src\\img\\3DES.jpg");

            FileOutputStream outputFile_txt =
                    new FileOutputStream("C:\\Users\\John\\IdeaProjects\\CS455Project1\\src\\3DES.txt");

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

    public static void encryptAES(ByteArrayOutputStream baos) {

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

            System.out.format("AES: %d \n", endTime - startTime);

            FileOutputStream outputFile_jpg =
                    new FileOutputStream("C:\\Users\\John\\IdeaProjects\\CS455Project1\\src\\img\\AES.jpg");

            FileOutputStream outputFile_txt =
                    new FileOutputStream("C:\\Users\\John\\IdeaProjects\\CS455Project1\\src\\AES.txt");

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

    public static void encryptRSA(ByteArrayOutputStream baos) {

        // Start timer
        long startTime = System.nanoTime();

        byte[] output;

        try {

            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(512);
            final KeyPair key = keyGen.generateKeyPair();

            Cipher RSACipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

            RSACipher.init(Cipher.ENCRYPT_MODE, key.getPublic());

            // Encrypts using 128 bit block as per specification
            output = RSACipher.doFinal(baos.toByteArray());

            long endTime = System.nanoTime();

            System.out.format("RSA: %d \n", endTime-startTime);

            FileOutputStream outputFile_jpg =
                    new FileOutputStream("C:\\Users\\John\\IdeaProjects\\CS455Project1\\src\\img\\RSA.jpg");

            FileOutputStream outputFile_txt =
                    new FileOutputStream("C:\\Users\\John\\IdeaProjects\\CS455Project1\\src\\RSA.txt");

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
}