import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class OCR extends Thread{

    private Tesseract tesseract;

    public OCR() {
        tesseract = new Tesseract();
        tesseract.setLanguage("tam");
        tesseract.setDatapath("C://Users/User/Desktop/PdfToText/tessdata");
        //tesseract.setOcrEngineMode(3);
    }

    public String doOCR(File file) throws TesseractException, IOException {


        String text = "";

        PDDocument document = PDDocument.load(file);
        PDFRenderer pdfRenderer = new PDFRenderer(document);
//
        for (int page = 0; page < document.getNumberOfPages(); ++page) {
            BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 900, ImageType.BINARY);
//            ImageIO.write(bim, "png", new File("/media/aj/Studies/OCR/Tests/TXT/123.png"));

                System.out.println("page"+page );
            text += tesseract.doOCR(bim);

                System.out.println("OCR "+page );
//
//
        }

            document.close();

            System.out.println("OCRed Successfully...");

            return text;
        }

    }

