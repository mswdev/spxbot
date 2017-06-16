package org.ui.login_screen;

import org.api.client.design.enums.SPXColor;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sphiinx on 6/14/2017.
 */
public class LoginScreenPanel extends JPanel {

    /**
     * Initializes the login screen panel.
     */
    public void initializeLoginScreenPanel() {
        setLayout(new GridBagLayout());
        setBackground(SPXColor.SPX_GRAY.getColor());
    }

}

