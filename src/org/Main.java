package org;

import javax.swing.*;
import java.applet.Applet;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;

/**
 * Created by Sphiinx on 3/23/17.
 */
public class Main {

    /**
     * The applet width in pixels.
     * */
    private static final int APPLET_WIDTH = 765;

    /**
     * The applet height in pixels.
     * */
    private static final int APPLET_HEIGHT = 503;

    /**
     * The applet frame width in pixels.
     * */
    private static final int APPLET_FRAME_WIDTH = 800;

    /**
     * The applet frame height in pixels.
     * */
    private static final int APPLET_FRAME_HEIGHT = 600;

    /**
     * The name of the applet.
     * */
    private static final String APPLET_NAME = "SPXBot";

    /**
     * The url in which to grab the client parameters.
     * */
    private static final String JAVA_CONFIG_URL = "http://oldschool.runescape.com/jav_config.ws";

    public static void main(String[] args) {
        final ConfigReader CONFIG_READER = new ConfigReader(JAVA_CONFIG_URL);
        final Map<String, String> CONFIG_PARAMETERS = CONFIG_READER.parseConfig();
        System.out.println(CONFIG_PARAMETERS.toString());

        final String JAR_LOCATION = CONFIG_PARAMETERS.get("codebase") + CONFIG_PARAMETERS.get("initial_jar");
        try {
            final URLClassLoader CLASS_LOADER = new URLClassLoader(new URL[]{new URL(JAR_LOCATION)});
            final Class<?> CLIENT_CLASS = CLASS_LOADER.loadClass(CONFIG_PARAMETERS.get("initial_class").replace(".class", ""));
            final Applet APPLET = (Applet) CLIENT_CLASS.newInstance();
            final RSAppletStub APPLET_STUB = new RSAppletStub(CONFIG_PARAMETERS);
            APPLET_STUB.getAppletContext().setApplet(APPLET);
            APPLET.setStub(APPLET_STUB);
            APPLET.init();
            APPLET.setSize(APPLET_WIDTH, APPLET_HEIGHT);
            APPLET_STUB.setActive(true);

            final JFrame APPLET_FRAME = new JFrame(APPLET_NAME);
            APPLET_FRAME.setSize(APPLET_FRAME_WIDTH, APPLET_FRAME_HEIGHT);
            final JPanel APPLET_PANEL = new JPanel();
            APPLET_FRAME.add(APPLET_PANEL);
            APPLET_PANEL.add(APPLET);
            APPLET_FRAME.pack();
            APPLET_FRAME.setVisible(true);
        } catch (IllegalAccessException | ClassNotFoundException | MalformedURLException | InstantiationException e) {
            e.printStackTrace();
        }
    }

}

