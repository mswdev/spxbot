package org.ui;

import org.api.design.enums.SPXColor;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sphiinx on 6/9/2017.
 */
public class AppletPanel extends AppletFrame {

    /**
     * The master JPanel for the applet panel.
     */
    private static final JPanel APPLET_PANEL = new JPanel();

    /**
     * Initializes the main applet panel.
     */
    public void initializeAppletPanel() {
        APPLET_PANEL.setLayout(new BorderLayout());
        APPLET_PANEL.setBackground(SPXColor.SPX_GRAY.getColor());
        getAppletFrame().add(APPLET_PANEL);
    }

    /**
     * Gets the main applet panel.
     *
     * @return The main applet panel.
     */
    public JPanel getAppletPanel() {
        return APPLET_PANEL;
    }


}

