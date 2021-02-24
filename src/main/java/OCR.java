import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


public class OCR extends Thread{

    private Tesseract tesseract;

    public OCR() {
        tesseract = new Tesseract();
        tesseract.setLanguage("tam");
        tesseract.setDatapath("C://Users/User/Desktop/PdfToText/tessdata");
        tesseract.setOcrEngineMode(2);
    }

    private byte saturate(double val) {
        int iVal = (int) Math.round(val);
        iVal = iVal > 255 ? 255 : (iVal < 0 ? 0 : iVal);
        return (byte) iVal;
    }

    public String doOCR(File file) throws TesseractException, IOException {

        String text = "";

        PDDocument document = PDDocument.load(file);
        PDFRenderer pdfRenderer = new PDFRenderer(document);
//
        for (int page = 0; page < document.getNumberOfPages(); ++page) {
            BufferedImage bimg = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
            ImageIO.write(bimg, "png", new File("C://Users/User/Desktop/PdfToText/1.png"));

            nu.pattern.OpenCV.loadLocally();

            //source image
            Mat image = Imgcodecs.imread("C://Users/User/Desktop/PdfToText/1.png", Imgcodecs.IMREAD_COLOR);
            Imgcodecs.imwrite("C://Users/User/Desktop/PdfToText/preprocess/True_Image.png", image);

            //Changing the contrast and brightness
            Mat dest = new Mat(image.rows(), image.cols(), image.type());
            image.convertTo(dest, -1, 10, 0);
            Imgcodecs.imwrite("C://Users/User/Desktop/PdfToText/preprocess/dest.png", dest);

            // Gray Scale
            Mat imgGray = new Mat();
            Imgproc.cvtColor(dest, imgGray, Imgproc.COLOR_BGR2GRAY);
            Imgcodecs.imwrite("C://Users/User/Desktop/PdfToText/preprocess/gray.png", imgGray);


            //Adaptive Threshold
            Mat imgAdaptiveThreshold = new Mat();
            Imgproc.adaptiveThreshold(imgGray, imgAdaptiveThreshold, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 99, 4);
            Imgcodecs.imwrite("C://Users/User/Desktop/PdfToText/preprocess/adaptive_threshold.png", imgAdaptiveThreshold);

            //smoothing the image
            Mat colorImg = new Mat();
            Imgproc.bilateralFilter(imgAdaptiveThreshold, colorImg, 9, 250, 250);
            Imgcodecs.imwrite("C://Users/User/Desktop/PdfToText/preprocess/colorImg.png", colorImg);

            // combine
            Mat finalImage = new Mat();
            Core.bitwise_and(colorImg, imgAdaptiveThreshold, finalImage);
            Imgcodecs.imwrite("C://Users/User/Desktop/PdfToText/preprocess/finalImage.png", finalImage);


            System.out.println("page"+page );

            try{
                File imageFile = new File("C://Users/User/Desktop/PdfToText/preprocess/finalImage.png");
                text += tesseract.doOCR(imageFile);
                System.out.println("OCR "+page );
            }
            catch (TesseractException e) {
                System.err.println(e.getMessage());
            }
        }
        document.close();
        System.out.println("OCRed Successfully...");
        return text;
    }

}

