import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.sql.Timestamp;

/**
 * Class to write the run times to file
 */
public class TimesWriter {

    private FileOutputStream textFile;

    /**
     * Constructor
     */
    public TimesWriter() {

        try {
            String fileName = "docs/Times.txt";
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
     * @param algorithm Name of the algorithm to write for
     * @param time Time to write
     */
    public void recordTime(String algorithm, long time) {

        try {
            String fileName = "docs/Times.txt";
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
