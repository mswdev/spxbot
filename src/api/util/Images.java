package api.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Images {

    /**
     * Gets the image from the url.
     *
     * @param url The url of the image.
     * @return The image.
     */
    public static Image getImageFromURL(String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * Gets the image from the path.
     *
     * @param path The path of the image.
     * @return The image.
     */
    public static Image getImageFromPath(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}

