import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Writer {
    public void write(String text,String filepath) throws IOException {
        BufferedWriter outputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filepath)));
        outputStream.write(text);
        outputStream.close();
        System.out.println("Written to the file Successfully!");
    }
}