package main;

import java.applet.Applet;
import java.applet.AppletStub;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * Created by Sphiinx on 3/23/17.
 */
public class RSAppletStub implements AppletStub {

    /**
     * The hash map containing the config parameters.
     */
    private final Map<String, String> CONFIG_PARAMETERS;

    /**
     * The applet context.
     */
    private final RSAppletContext APPLET_CONTEXT;

    /**
     * Contains whether the applet should be active or not.
     */
    private boolean is_active = false;

    /**
     * The constructor initializing the config parameters and creating the new applet context.
     *
     * @param config_parameters The config parameters to pass to the constructor.
     */
    public RSAppletStub(Map<String, String> config_parameters) {
        this.CONFIG_PARAMETERS = config_parameters;
        APPLET_CONTEXT = new RSAppletContext();
    }

    /**
     * Sets the applet to active in order to start it.
     *
     * @param is_active True if the applet should be active; false otherwise.
     */
    public void setActive(boolean is_active) {
        this.is_active = is_active;
    }

    @Override
    public boolean isActive() {
        return is_active;
    }

    @Override
    public URL getDocumentBase() {
        try {
            return new URL(CONFIG_PARAMETERS.get("codebase"));
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid document base url.");
        }
    }

    @Override
    public URL getCodeBase() {
        try {
            return new URL(CONFIG_PARAMETERS.get("codebase"));
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid code base url.");
        }
    }

    @Override
    public String getParameter(String name) {
        if (!CONFIG_PARAMETERS.containsKey(name))
            return null;

        return CONFIG_PARAMETERS.get(name);
    }

    @Override
    public RSAppletContext getAppletContext() {
        return APPLET_CONTEXT;
    }

    @Override
    public void appletResize(int width, int height) {
        final Applet APPLET = getAppletContext().getApplet("main");
        if (APPLET == null)
            return;

        APPLET.resize(width, height);
    }

}

