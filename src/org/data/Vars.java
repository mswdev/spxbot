package org.data;

/**
 * Created by Sphiinx on 3/24/17.
 */
public class Vars {

    /**
     * The initialized variable.
     */
    public static Vars vars;

    /**
     * Gets the specified variable.
     */
    public static Vars get() {
        return vars == null ? vars = new Vars() : vars;
    }

    /**
     * Resets the current variables.
     */
    public static void reset() {
        vars = null;
    }

    /**
     * The applet width in pixels.
     */
    public final int APPLET_WIDTH = 765;

    /**
     * The applet height in pixels.
     */
    public final int APPLET_HEIGHT = 503;

    /**
     * The applet frame width in pixels.
     */
    public final int APPLET_FRAME_WIDTH = 800;

    /**
     * The applet frame height in pixels.
     */
    public final int APPLET_FRAME_HEIGHT = 600;

    /**
     * The name of the applet.
     */
    public final String APPLET_NAME = "SPXBot";

    /**
     * The url in which to grab the client parameters.
     */
    public final String JAVA_CONFIG_URL = "http://oldschool.runescape.com/jav_config.ws";

}

