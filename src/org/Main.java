package org;

import org.api.DirectoryManagment;
import org.api.data_managment.DataManagment;
import org.data.Vars;
import org.data.enums.DirectoryFile;
import org.data.enums.DirectoryFolder;
import org.util.Logging;

import javax.swing.*;
import java.applet.Applet;
import java.io.*;
import java.net.*;
import java.util.Map;

/**
 * Created by Sphiinx on 3/23/17.
 */
public class Main {

    /**
     * 1. If we do not have the gamepack, download it and store the version.
     * 2. If we do have the gamepack, and we do not have the version stored || the version is stored, re-download it and store the version.
     * 3. if we do have the gamepack, and we do not have the version stored || the version is stored but is old, re-download it and store the version.
     * */


    public static void main(String[] args) {
        Logging.setDebug(true);
        if (DirectoryManagment.createDirectoryFolders())
            Logging.debug("Successfully created all directory folders.");
        if (DirectoryManagment.createDirectoryFiles())
            Logging.debug("Successfully created all directory files.");
        if (DataManagment.createFileProperties())
            Logging.debug("Successfully created file properties.");
        /*if (DirectoryManagment.requestGamepack())
            Logging.debug("Successfully acquired the gamepack.");

        final ConfigReader CONFIG_READER = new ConfigReader(Vars.get().JAVA_CONFIG_URL);
        final Map<String, String> CONFIG_PARAMETERS = CONFIG_READER.parseConfig();

        try {
            final URLClassLoader CLASS_LOADER = new URLClassLoader(new URL[]{new File(DirectoryFolder.DATA.getDirectoryPath() + File.separator + DirectoryFolder.DATA.getFolderName() + File.separator + DirectoryFile.GAMEPACK.getFileName() + DirectoryFile.GAMEPACK.getFileExtension()).toURI().toURL()});
            final Class<?> CLIENT_CLASS = CLASS_LOADER.loadClass(CONFIG_PARAMETERS.get("initial_class").replace(".class", ""));
            final Applet APPLET = (Applet) CLIENT_CLASS.newInstance();
            final RSAppletStub APPLET_STUB = new RSAppletStub(CONFIG_PARAMETERS);
            APPLET_STUB.getAppletContext().setApplet(APPLET);
            APPLET.setStub(APPLET_STUB);
            APPLET.init();
            APPLET.setSize(Vars.get().APPLET_WIDTH, Vars.get().APPLET_HEIGHT);
            APPLET_STUB.setActive(true);

            final JFrame APPLET_FRAME = new JFrame(Vars.get().APPLET_NAME);
            APPLET_FRAME.setSize(Vars.get().APPLET_FRAME_WIDTH, Vars.get().APPLET_FRAME_HEIGHT);
            final JPanel APPLET_PANEL = new JPanel();
            APPLET_FRAME.add(APPLET_PANEL);
            APPLET_PANEL.add(APPLET);
            APPLET_FRAME.pack();
            APPLET_FRAME.setVisible(true);
            Logging.debug("Game client successfully hooked.");
        } catch (IllegalAccessException | ClassNotFoundException | MalformedURLException | InstantiationException e) {
            e.printStackTrace();
        }*/
    }

}

