package org.ui;

import org.data.Vars;

import javax.swing.*;

/**
 * Created by Sphiinx on 6/9/2017.
 */
public class AppletFrame {

    /**
     * The master JFrame for the applet frame.
     */
    private static final JFrame APPLET_FRAME = new JFrame();

    /**
     * Initializes the main applet frame.
     */
    public void initializeAppletFrame() {
        APPLET_FRAME.setTitle(Vars.get().APPLET_NAME);
        APPLET_FRAME.setSize(Vars.get().APPLET_FRAME_WIDTH, Vars.get().APPLET_FRAME_HEIGHT);
        APPLET_FRAME.setLocationRelativeTo(null);
    }

    /**
     * Gets the main applet frame.
     *
     * @return The main applet frame.
     */
    public JFrame getAppletFrame() {
        return APPLET_FRAME;
    }

}

