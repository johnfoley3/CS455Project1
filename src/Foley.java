import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @author John Foley
 * Main driver class
 */
class Foley {

    /**
     * Main function
     * @param args Arguments given at command line
     */
    public static void main(String[] args) {

        BufferedImage targetImage;

        String imgPath = "img/Target.jpg";
		File targetFile = new File(imgPath);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {

            // Read the image, then pipe to byte array
            targetImage = ImageIO.read(targetFile);
	        int imageHeight = targetImage.getHeight();
	        int imageWidth = targetImage.getWidth();

	        int[][] imageMatrix = new int[imageHeight][imageWidth];

	        for (int i = 0; i < imageHeight; i++) {

		        for (int j = 0; j < imageWidth; j++) {

			        imageMatrix[i][j] = targetImage.getRGB(j, i);
		        }
	        }

            ImageIO.write(targetImage, "jpg", baos);

            TimesWriter timer = new TimesWriter();

            DES des = new DES(baos, timer);
            DESede desede = new DESede(baos, timer);
            AES aes = new AES(baos, timer);
            RSA rsa = new RSA(baos, timer);
        } catch (IOException e) {

            System.out.println("Could not open Buffered Image");
            e.printStackTrace();
        }
    }
}