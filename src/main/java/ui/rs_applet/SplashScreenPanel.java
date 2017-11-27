package org.ui.rs_applet;

import org.api.client.design.enums.SPXColor;
import org.api.client.design.enums.SPXFont;
import org.data.Vars;
import org.data.enums.DirectoryFile;
import org.data.enums.DirectoryFolder;
import org.util.Images;
import org.util.Logging;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;

/**
 * Created by Sphiinx on 6/9/2017.
 */
public class SplashScreenPanel extends JPanel {

    /**
     * The JLabel containing the status text.
     */
    private static final JLabel STATUS_TEXT = new JLabel();

    /**
     * The JLabel containing the developers text.
     */
    private final JLabel DEVELOPERS_TEXT = new JLabel();

    /**
     * The ImageIcon containing the logo.
     */
    private final ImageIcon IMAGE_ICON = new ImageIcon();

    /**
     * The JLabel containing the logo.
     */
    private final JLabel LOGO_LABEL = new JLabel();

    /**
     * Initializes the splash screen panel.
     */
    public void initializeSplashScreenPanel() {
        setLayout(new BorderLayout());
        setBackground(SPXColor.SPX_GRAY.getColor());

        final Image SPLASH_SCREEN_LOGO = Images.getImageFromPath(DirectoryFolder.ASSETS.getDirectoryPath() + File.separator + DirectoryFolder.ASSETS.getFolderName() + File.separator + DirectoryFile.SPLASH_SCREEN_LOGO.getFileName() + DirectoryFile.SPLASH_SCREEN_LOGO.getFileExtension());
        if (SPLASH_SCREEN_LOGO != null)
            IMAGE_ICON.setImage(SPLASH_SCREEN_LOGO);

        LOGO_LABEL.setIcon(IMAGE_ICON);
        LOGO_LABEL.setHorizontalAlignment(JLabel.CENTER);
        LOGO_LABEL.setBorder(new EmptyBorder(125, 0, 0, 0));

        STATUS_TEXT.setText(Logging.getDebugStatus());
        STATUS_TEXT.setFont(SPXFont.SPX_MAIN_TEXT.getFont());
        STATUS_TEXT.setForeground(SPXColor.SPX_WHITE.getColor());
        STATUS_TEXT.setHorizontalAlignment(JLabel.CENTER);

        DEVELOPERS_TEXT.setText("Developer: " + Vars.get().CLIENT_DEVELOPER);
        DEVELOPERS_TEXT.setFont(SPXFont.SPX_MAIN_TEXT.getFont());
        DEVELOPERS_TEXT.setForeground(SPXColor.SPX_WHITE.getColor());
        DEVELOPERS_TEXT.setHorizontalAlignment(JLabel.LEFT);
        DEVELOPERS_TEXT.setBorder(new EmptyBorder(0, 10, 10, 0));

        add(LOGO_LABEL, BorderLayout.NORTH);
        add(STATUS_TEXT, BorderLayout.CENTER);
        add(DEVELOPERS_TEXT, BorderLayout.SOUTH);
    }

    /**
     * Updates the status text.
     */
    public static JLabel getStatusTextLabel() {
        return STATUS_TEXT;
    }

}

