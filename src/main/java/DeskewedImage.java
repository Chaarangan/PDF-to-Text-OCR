import com.recognition.software.jdeskew.ImageDeskew;
import net.sourceforge.tess4j.util.ImageHelper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DeskewedImage {
    public static BufferedImage correctSkewness(BufferedImage image) throws IOException {

        final double MINIMUM_DESKEW_THRESHOLD = 0.05d;

        ImageDeskew mImage = new ImageDeskew(image);
        double imageSkewAngle = mImage.getSkewAngle(); // determine skew angle
        if ((imageSkewAngle > MINIMUM_DESKEW_THRESHOLD || imageSkewAngle < -(MINIMUM_DESKEW_THRESHOLD))) {
            image = ImageHelper.rotateImage(image, -imageSkewAngle); // deskew image
        }
        ImageIO.write(image, "png", new File("PdfToText/OpenCV/skewed/"+1 + ".png"));
        return image;
    }
}
