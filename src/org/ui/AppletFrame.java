package org.ui;

import org.data.Vars;

import javax.swing.*;

/**
 * Created by Sphiinx on 6/9/2017.
 */
public class AppletFrame extends JFrame {

    /**
     * Initializes the main applet frame.
     */
    public void initializeAppletFrame() {
        setTitle(Vars.get().APPLET_NAME);
        setSize(Vars.get().APPLET_FRAME_WIDTH, Vars.get().APPLET_FRAME_HEIGHT);
        setLocationRelativeTo(null);
    }

}

