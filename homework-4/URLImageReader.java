import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class URLImageReader {
    public static void main(String[] args) {
        BufferedImage image =null;
        try {
            URL url = new URL("https://picsum.photos/200/300");

            image = ImageIO.read(url);

            ImageIO.write(image, "png", new File("image.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}