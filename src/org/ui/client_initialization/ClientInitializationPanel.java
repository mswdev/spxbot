package org.ui.client_initialization;

import org.api.client.design.enums.SPXColor;
import org.api.client.design.enums.SPXFont;
import org.data.enums.DirectoryFile;
import org.data.enums.DirectoryFolder;
import org.util.Images;
import org.util.Logging;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;

/**
 * Created by Sphiinx on 6/16/2017.
 */
public class ClientInitializationPanel extends JPanel {

    /**
     * The JLabel containing the status text.
     */
    private static final JLabel STATUS_TEXT = new JLabel();

    /**
     * The ImageIcon containing the logo.
     */
    private final ImageIcon IMAGE_ICON = new ImageIcon();

    /**
     * The JLabel containing the logo.
     */
    private final JLabel LOGO_LABEL = new JLabel();


    /**
     * Initializes the client initialization panel.
     */
    public void initializeClientInitializationPanel() {
        setLayout(new BorderLayout());
        setBackground(SPXColor.SPX_GRAY.getColor());

        final Image CLIENT_INITIALIZATION_LOGO = Images.getImageFromPath(DirectoryFolder.ASSETS.getDirectoryPath() + File.separator + DirectoryFolder.ASSETS.getFolderName() + File.separator + DirectoryFile.CLIENT_INITIALIZATION_LOGO.getFileName() + DirectoryFile.CLIENT_INITIALIZATION_LOGO.getFileExtension());
        if (CLIENT_INITIALIZATION_LOGO != null)
            IMAGE_ICON.setImage(CLIENT_INITIALIZATION_LOGO);

        LOGO_LABEL.setIcon(IMAGE_ICON);
        LOGO_LABEL.setHorizontalAlignment(JLabel.CENTER);
        LOGO_LABEL.setBorder(new EmptyBorder(15, 0, 0, 0));

        STATUS_TEXT.setText(Logging.getDebugStatus());
        STATUS_TEXT.setFont(SPXFont.SPX_MAIN_TEXT.getFont());
        STATUS_TEXT.setForeground(SPXColor.SPX_WHITE.getColor());
        STATUS_TEXT.setHorizontalAlignment(JLabel.CENTER);

        add(LOGO_LABEL, BorderLayout.NORTH);
        add(STATUS_TEXT, BorderLayout.CENTER);
    }

    /**
     * Updates the status text.
     */
    public static JLabel getStatusTextLabel() {
        return STATUS_TEXT;
    }

}

