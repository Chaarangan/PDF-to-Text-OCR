import com.recognition.software.jdeskew.ImageDeskew;
import net.sourceforge.tess4j.util.ImageHelper;

import java.awt.image.BufferedImage;

public class DeskewedImage {
    public static BufferedImage correctSkewness(BufferedImage image) {

        final double MINIMUM_DESKEW_THRESHOLD = 0.05d;

        ImageDeskew mImage = new ImageDeskew(image);
        double imageSkewAngle = mImage.getSkewAngle(); // determine skew angle
        if ((imageSkewAngle > MINIMUM_DESKEW_THRESHOLD || imageSkewAngle < -(MINIMUM_DESKEW_THRESHOLD))) {
            image = ImageHelper.rotateImage(image, -imageSkewAngle); // deskew image
        }
        return image;
    }
}
