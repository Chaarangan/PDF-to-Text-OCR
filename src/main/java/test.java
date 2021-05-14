
import net.sourceforge.tess4j.TesseractException;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class test {

    public static void main(String[] args) throws TesseractException, IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a file Path: ");
        System.out.flush();
        String filename = scanner.nextLine();
        File file = new File(filename);

        OCR ocr=new OCR();
        String text=ocr.doOCR(file);
        Writer writer=new Writer();
        writer.write(writer.edit(text),filename.substring(0,filename.length()-3)+"txt");
    }
}



