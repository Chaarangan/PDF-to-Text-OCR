import net.sourceforge.tess4j.TesseractException;
import org.apache.log4j.BasicConfigurator;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) throws IOException {
        BasicConfigurator.configure();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your file Path: ");
        System.out.flush();
        String filename = scanner.nextLine();
        File file = new File(filename);

        ExtractText extractText = new ExtractText();
        String text = extractText.extractTextFromFile(file);
        Writer writer=new Writer();
        writer.write(text,filename.substring(0,filename.length()-3)+"txt");
    }
}



