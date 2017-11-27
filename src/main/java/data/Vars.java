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
    public final int APPLET_FRAME_WIDTH = 791;

    /**
     * The applet frame height in pixels.
     */
    public final int APPLET_FRAME_HEIGHT = 552;

    /**
     * The last known revision, this is just a base for starting the revision tracking.
     */
    public final int LAST_KNOWN_REVISION = 143;

    /**
     * The name of the applet.
     */
    public final String APPLET_NAME = "SPXBot";

    /**
     * The url in which to grab the client parameters.
     */
    public final String JAVA_CONFIG_URL = "http://oldschool.runescape.com/jav_config.ws";

    /**
     * The current client version.
     */
    public String CLIENT_VERSION = "";

    /**
     * The boolean determining whether the client is online or not.
     */
    public boolean IS_CLIENT_ONLINE;

    /**
     * The name of the client developer.
     */
    public final String CLIENT_DEVELOPER = "Sphiinx";
}

