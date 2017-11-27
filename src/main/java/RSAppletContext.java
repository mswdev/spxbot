package org;

import javax.imageio.ImageIO;
import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AudioClip;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

/**
 * Created by Sphiinx on 3/23/17.
 */
public class RSAppletContext implements AppletContext {

    /**
     * The hash map containing the streams.
     */
    private final Map<String, InputStream> STREAMS = new HashMap<>();

    /**
     * The applet.
     */
    private Applet applet;

    /**
     * Sets the applet so we can return it and the enumeration of applets in addition to setting the AppletContext.
     *
     * @param applet The applet to get.
     */
    public void setApplet(Applet applet) {
        this.applet = applet;
    }

    @Override
    public AudioClip getAudioClip(URL url) {
        return Applet.newAudioClip(url);
    }

    @Override
    public Image getImage(URL url) {
        try {
            return ImageIO.read(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Applet getApplet(String name) {
        return applet;
    }

    @Override
    public Enumeration<Applet> getApplets() {
        final Vector<Applet> APPLETS = new Vector<>();
        APPLETS.add(applet);
        return APPLETS.elements();
    }

    @Override
    public void showDocument(URL url) {
        if (!Desktop.isDesktopSupported())
            return;

        try {
            Desktop.getDesktop().browse(url.toURI());
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException("Unable to open document " + url.getPath());
        }
    }

    @Override
    public void showDocument(URL url, String target) {
        if (!Desktop.isDesktopSupported())
            return;

        try {
            Desktop.getDesktop().browse(url.toURI());
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException("Unable to open document " + url.getPath());
        }
    }

    @Override
    public void showStatus(String status) {

    }

    @Override
    public void setStream(String key, InputStream stream) throws IOException {
        if (STREAMS.containsKey(key))
            STREAMS.remove(key);

        STREAMS.put(key, stream);
    }

    @Override
    public InputStream getStream(String key) {
        return STREAMS.get(key);
    }

    @Override
    public Iterator<String> getStreamKeys() {
        return STREAMS.keySet().iterator();
    }

}

