import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class CheckScannedPdf {
    public LinkedList<BufferedImage> getBufferedImages(File pdfFile ) throws IOException {
        int images = 0;
        int numberOfPages;

        LinkedList<BufferedImage> bufferedImages = new LinkedList<>();

        PDDocument doc = PDDocument.load(pdfFile);

        PDPageTree list = doc.getPages();

        numberOfPages = doc.getNumberOfPages();

        for (PDPage page : list) {

            PDResources resource = page.getResources();

            for (COSName xObjectName : resource.getXObjectNames()) {

                PDXObject xObject = resource.getXObject(xObjectName);

                if (xObject instanceof PDImageXObject) {
                    PDImageXObject image = (PDImageXObject) xObject;

                    BufferedImage bufferedImage = image.getImage();
                    // Add bufferedImages to list
                    bufferedImages.add(bufferedImage);
                    images++;
                }

            }

        }

        doc.close();

        //  pdf pages if equal to the images === scanned pdf ===
        if (numberOfPages == images || images > numberOfPages) {
            return bufferedImages;
        } else {
            return new LinkedList<>();
        }

    }
}
