package org;

import org.api.DirectoryManagment;
import org.api.GamepackManagment;
import org.api.design.enums.SPXColor;
import org.api.design.enums.SPXFont;
import org.data.Vars;
import org.data.enums.DirectoryFile;
import org.data.enums.DirectoryFolder;
import org.util.Logging;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by Sphiinx on 3/23/17.
 */
public class Main {

    public static void main(String[] args) {

        Logging.setDebug(true);

        final JFrame APPLET_FRAME = new JFrame(Vars.get().APPLET_NAME);
        APPLET_FRAME.setSize(Vars.get().APPLET_FRAME_WIDTH, Vars.get().APPLET_FRAME_HEIGHT);
        final JPanel APPLET_PANEL = new JPanel();
        APPLET_FRAME.add(APPLET_PANEL);

        APPLET_PANEL.setLayout(new BorderLayout());

        APPLET_PANEL.setBackground(SPXColor.SPX_GRAY.getColor());
        final JLabel DOWNLOAD_TEXT = new JLabel(Logging.getDebugStatus());
        DOWNLOAD_TEXT.setForeground(SPXColor.SPX_WHITE.getColor());
        DOWNLOAD_TEXT.setFont(SPXFont.SPX_MAIN_TEXT.getFont());
        DOWNLOAD_TEXT.setHorizontalAlignment(JLabel.CENTER);
        APPLET_PANEL.add(DOWNLOAD_TEXT, BorderLayout.CENTER);

        APPLET_FRAME.setVisible(true);

        if (DirectoryManagment.createDirectoryFolders()) {
            Logging.setDebugStatus("Creating directory folders.");
            Logging.status("Successfully created all directory folders.");
        }
        if (DirectoryManagment.createDirectoryFiles()) {
            Logging.setDebugStatus("Creating directory files.");
            Logging.status("Successfully created all directory files.");
        }
        if (DirectoryManagment.createFileProperties()) {
            Logging.setDebugStatus("Creating file properties.");
            Logging.status("Successfully created file properties.");
        }
        if (GamepackManagment.needsGamepack()) {
            if (GamepackManagment.requestGamepack()) {
                Logging.setDebugStatus("Acquiring the gamepack.");
                Logging.status("Successfully acquired the gamepack.");
            }
        }

        try {
            final URLClassLoader CLASS_LOADER = new URLClassLoader(new URL[]{new File(DirectoryFolder.DATA.getDirectoryPath() + File.separator + DirectoryFolder.DATA.getFolderName() + File.separator + DirectoryFile.GAMEPACK.getFileName() + DirectoryFile.GAMEPACK.getFileExtension()).toURI().toURL()});
            final Class<?> CLIENT_CLASS = CLASS_LOADER.loadClass(GamepackManagment.getConfigParameters().get("initial_class").replace(".class", ""));
            final Applet APPLET = (Applet) CLIENT_CLASS.newInstance();
            final RSAppletStub APPLET_STUB = new RSAppletStub(GamepackManagment.getConfigParameters());
            APPLET_STUB.getAppletContext().setApplet(APPLET);
            APPLET.setStub(APPLET_STUB);
            APPLET.init();
            APPLET.setSize(Vars.get().APPLET_WIDTH, Vars.get().APPLET_HEIGHT);
            APPLET_STUB.setActive(true);

            DOWNLOAD_TEXT.setVisible(false);

            APPLET_PANEL.add(APPLET);
            APPLET_FRAME.pack();
            Logging.status("Successfully hooked the game client.");
        } catch (IllegalAccessException | ClassNotFoundException | MalformedURLException | InstantiationException e) {
            e.printStackTrace();
        }
    }

}

