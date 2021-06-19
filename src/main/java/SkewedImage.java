import com.recognition.software.jdeskew.ImageDeskew;
import net.sourceforge.tess4j.util.ImageHelper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SkewedImage {
    public BufferedImage correctSkewness(BufferedImage image) throws IOException {

        final double MINIMUM_SKEW_THRESHOLD = 0.05d;

        ImageDeskew mImage = new ImageDeskew(image);
        double imageSkewAngle = mImage.getSkewAngle(); // determine skew angle
        if ((imageSkewAngle > MINIMUM_SKEW_THRESHOLD || imageSkewAngle < -(MINIMUM_SKEW_THRESHOLD))) {
            image = ImageHelper.rotateImage(image, -imageSkewAngle); // skewed image
        }
        ImageIO.write(image, "png", new File("PdfToText/OpenCV/skewed/"+image.toString() + ".png"));
        return image;
    }
}
