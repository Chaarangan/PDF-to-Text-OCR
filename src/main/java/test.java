
import net.sourceforge.tess4j.TesseractException;

import java.io.File;
import java.io.IOException;


public class test {

    public static void main(String[] args) throws TesseractException, IOException {
        String inputfolder= "C://Users/User/Desktop/PdfToText/pdf";
        String outputfolder="C://Users/User/Desktop/PdfToText/pdf";

        String[] filenames ={"sample.pdf"};


        for (String filename:filenames
             ) {
            File file=new File(inputfolder+"/"+filename);
            OCR ocr=new OCR();
            String text=ocr.doOCR(file);
            Writer writer=new Writer();
            writer.write(writer.edit(text),outputfolder+"/"+filename.substring(0,filename.length()-3)+"txt");

        }
//2




    }
}



