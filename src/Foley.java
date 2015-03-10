import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Foley {
	public static void main(String[] args) {

        BufferedImage targetImage;
        int width = 0;

        String imgPath = "C:\\Users\\John\\IdeaProjects\\CS455Project1\\src\\img\\Target.jpg";
		File targetFile = new File(imgPath);
        System.out.println(imgPath);
        try {
            targetImage = ImageIO.read(targetFile);
            width = targetImage.getWidth();
        } catch (IOException e) {
            System.out.println("Could not open Buffered Image");
            e.printStackTrace();
        }

        System.out.println(width);
    }
}