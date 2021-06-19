import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.ImageHelper;
import nu.pattern.OpenCV;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExtractTextFromImage {
    static Tesseract tesseract = new Tesseract();

    public ExtractTextFromImage(){
        tesseract = new Tesseract();
        tesseract.setLanguage("tam");
        tesseract.setDatapath("PdfToText/tessdata");
        tesseract.setOcrEngineMode(2);
    }
    public static String extractTextFromImage(BufferedImage image) throws IOException {

        BufferedImage grayImage = ImageHelper.convertImageToGrayscale(image);
        ImageIO.write(grayImage, "png", new File("PdfToText/OpenCV/original/"+1 + ".png"));
        OpenCV.loadLocally();

        //source image
        Mat in = Imgcodecs.imread("PdfToText/OpenCV/original/"+1 + ".png", Imgcodecs.IMREAD_COLOR);

        // convert to grayscale
        Mat gray = new Mat(in.size(), CvType.CV_8UC1);
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

        Imgproc.GaussianBlur(gray, blurred, new Size(5,5), 2.2, 2);

        // next we threshold the blurred image
        float th=128;
        Imgproc.threshold(blurred,binary4c,th,255,Imgproc.THRESH_BINARY);

        //Changing the contrast and brightness
        Mat dest = new Mat(binary4c.rows(), binary4c.cols(), binary4c.type());
        gray.convertTo(dest, -1, 10, 0);
        Imgcodecs.imwrite("PdfToText/OpenCV/brightness-contrast/"+1 + ".png", dest);

        Mat mHierarchy = new Mat();
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Imgproc.findContours(dest, contours, mHierarchy, Imgproc.RETR_LIST,Imgproc.CHAIN_APPROX_SIMPLE);

        //Drawing the Contours
        Scalar color = new Scalar(0, 0, 255);
        Imgproc.drawContours(in, contours, -1, color, 2, Imgproc.LINE_8,
                mHierarchy, 2, new Point() ) ;
        Imgcodecs.imwrite("PdfToText/OpenCV/contours/"+1+".png", in);

        String ocrResults = null;
        try {
            File imageFile = new File("PdfToText/OpenCV/contours/"+1+".png");
            ocrResults = tesseract.doOCR(grayImage).replaceAll("\\n{2,}", "\n");

        } catch (TesseractException e) {
            e.printStackTrace();
        }

        if (ocrResults == null || ocrResults.trim().length() == 0) {
            return null;
        }

        ocrResults = ocrResults.trim();
        return ocrResults;
    }
}
