package org.ui;

import org.api.client.design.enums.SPXColor;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sphiinx on 6/9/2017.
 */
public class AppletPanel extends JPanel {

    /**
     * Initializes the main applet panel.
     */
    public void initializeAppletPanel() {
        setLayout(new BorderLayout());
        setBackground(SPXColor.SPX_GRAY.getColor());
    }

}

