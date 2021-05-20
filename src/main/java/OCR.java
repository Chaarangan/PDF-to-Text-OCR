import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import nu.pattern.OpenCV;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.*;
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

    public String doOCR(File file) throws IOException {

        String text = "";

        PDDocument document = PDDocument.load(file);
        PDFRenderer pdfRenderer = new PDFRenderer(document);
//
        for (int page = 0; page < document.getNumberOfPages(); ++page) {
            BufferedImage bimg = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
            ImageIO.write(bimg, "png", new File("C://Users/User/Desktop/PdfToText/OpenCV/original/"+page + ".png"));

            OpenCV.loadLocally();

            //source image
            Mat in = Imgcodecs.imread("C://Users/User/Desktop/PdfToText/OpenCV/original/"+page + ".png", Imgcodecs.IMREAD_COLOR);

            // convert to grayscale
            Mat gray = new Mat(in.size(),CvType.CV_8UC1);
            if(in.channels()==3){
                Imgproc.cvtColor(in, gray, Imgproc.COLOR_RGB2GRAY);
            }else if(in.channels()==1){
                in.copyTo(gray);
            }else{
                throw new IOException("Invalid image type:"+in.type());
            }

            // Blur the gray scale image
            Mat blurred = new Mat(gray.size(),gray.type());
            Mat binary4c    = new Mat(gray.size(),gray.type());

            Imgproc.GaussianBlur(gray, blurred, new Size (5,5), 2.2, 2);

            // next we threshold the blurred image
            float th=128;
            Imgproc.threshold(blurred,binary4c,th,255,Imgproc.THRESH_BINARY);

            //Changing the contrast and brightness
            Mat dest = new Mat(binary4c.rows(), binary4c.cols(), binary4c.type());
            gray.convertTo(dest, -1, 10, 0);
            Imgcodecs.imwrite("C://Users/User/Desktop/PdfToText/OpenCV/brightness-contrast/"+page + ".png", dest);

            Mat mHierarchy = new Mat();
            List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
            Imgproc.findContours(dest, contours, mHierarchy, Imgproc.RETR_LIST,Imgproc.CHAIN_APPROX_SIMPLE);

            //Drawing the Contours
            Scalar color = new Scalar(0, 0, 255);
            Imgproc.drawContours(in, contours, -1, color, 2, Imgproc.LINE_8,
                    mHierarchy, 2, new Point() ) ;
            Imgcodecs.imwrite("C://Users/User/Desktop/PdfToText/OpenCV/contours/"+page+".png", in);

            System.out.println("page"+page );

            try{
                File imageFile = new File("C://Users/User/Desktop/PdfToText/OpenCV/contours/"+page+".png");
                text += tesseract.doOCR(imageFile);
                System.out.println("OCR "+page );
            }
            catch (TesseractException e) {
                System.err.println(e.getMessage());
            }
        }
        document.close();
        System.out.println("Recognized Successfully!");
        return text;
    }
}

