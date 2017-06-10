package org.ui;

import org.api.client.design.enums.SPXColor;
import org.api.client.design.enums.SPXFont;
import org.data.Vars;
import org.util.Images;
import org.util.Logging;
import org.util.Text;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by Sphiinx on 6/9/2017.
 */
public class SplashScreen extends JPanel {

    /**
     * The master JLabel for the status text.
     */
    private static final JLabel STATUS_TEXT = new JLabel();

    /**
     * The master JLabel for the developers text.
     * */
    private final JLabel DEVELOPERS_TEXT = new JLabel();

    /**
     * The master ImageIcon for the logo.
     * */
    private final ImageIcon IMAGE_ICON = new ImageIcon();

    /**
     * The master JLabel for the logo.
     * */
    private final JLabel LOGO_LABEL = new JLabel();

    /**
     * Initializes the main splash screen panel.
     */
    public void initializeSplashScreen() {
        setLayout(new BorderLayout());
        setBackground(SPXColor.SPX_GRAY.getColor());

        IMAGE_ICON.setImage(Images.getImage(Vars.get().LOGO_URL));
        LOGO_LABEL.setIcon(IMAGE_ICON);
        LOGO_LABEL.setHorizontalAlignment(JLabel.CENTER);
        LOGO_LABEL.setBorder(new EmptyBorder(125, 0, 0, 0));

        STATUS_TEXT.setText(Logging.getDebugStatus());
        STATUS_TEXT.setFont(SPXFont.SPX_MAIN_TEXT.getFont());
        STATUS_TEXT.setForeground(SPXColor.SPX_WHITE.getColor());
        STATUS_TEXT.setHorizontalAlignment(JLabel.CENTER);

        DEVELOPERS_TEXT.setText("Developer: Sphiinx");
        DEVELOPERS_TEXT.setFont(SPXFont.SPX_MAIN_TEXT.getFont());
        DEVELOPERS_TEXT.setForeground(SPXColor.SPX_WHITE.getColor());
        DEVELOPERS_TEXT.setHorizontalAlignment(JLabel.LEFT);
        DEVELOPERS_TEXT.setBorder(new EmptyBorder(0, 10, 10 ,0));

        add(LOGO_LABEL, BorderLayout.NORTH);
        add(STATUS_TEXT, BorderLayout.CENTER);
        add(DEVELOPERS_TEXT, BorderLayout.SOUTH);
    }

    /**
     * Updates the status text.
     */
    public static void updateStatusText() {
        final String DEBUG_TEXT = Logging.getDebugStatus();
        if (DEBUG_TEXT.contains("%"))
            STATUS_TEXT.setText(DEBUG_TEXT);
        else
            STATUS_TEXT.setText(DEBUG_TEXT + Text.getLoadingPeriods());
    }

}

