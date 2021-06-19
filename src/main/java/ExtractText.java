import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class ExtractText {
    public static String extractTextFromFile(File file) throws IOException {
        StringBuilder extractedText = new StringBuilder();
        CheckScannedPdf checkScannedPdf = new CheckScannedPdf();
        SkewedImage skewedImage = new SkewedImage();
        ExtractTextFromImage extractTextFromImage = new ExtractTextFromImage();
        LinkedList<BufferedImage> bufferedImageList = checkScannedPdf.getBufferedImages(file);

        if (!bufferedImageList.isEmpty()) {
            for (BufferedImage image : bufferedImageList) {
                BufferedImage skewedImg = skewedImage.correctSkewness(image);
                String text = extractTextFromImage.extractTextFromImage(skewedImg);

                if (text != null) {
                    extractedText.append(text);
                }
            }
        }

        return extractedText.toString();
    }
}