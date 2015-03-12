import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.sql.Timestamp;

/**
 * Created by John on 3/12/2015.
 */
public class TimesWriter {

    private FileOutputStream textFile;

    /**
     * Constructor
     */
    public TimesWriter() {

        try {
            String fileName = "C:\\Users\\John\\IdeaProjects\\CS455Project1\\src\\docs\\Times.txt";
            Date date = new Date();
            String header = "\n" + new Timestamp(date.getTime()) + "\n";

            // Open file and clear it
            textFile = new FileOutputStream(fileName, true);
            textFile.write(header.getBytes());
            textFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("Times Writer could not find our text file!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the time to a file
     * @param algorithm
     * @param time
     */
    public void recordTime(String algorithm, long time) {

        try {
            String fileName = "C:\\Users\\John\\IdeaProjects\\CS455Project1\\src\\Times.txt";
            algorithm += " ";
            String newLine = "\n";

            // Open file and clear it
            textFile = new FileOutputStream(fileName, true);
            textFile.write(algorithm.getBytes());
            textFile.write(Long.toString(time).getBytes());
            textFile.write(newLine.getBytes());
            textFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("Times Writer could not find our text file!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
