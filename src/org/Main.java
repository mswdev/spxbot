package org;

import org.api.DataManagment;
import org.data.Vars;
import org.data.enums.DirectoryFile;
import org.data.enums.DirectoryFolder;

import javax.swing.*;
import java.applet.Applet;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;

/**
 * Created by Sphiinx on 3/23/17.
 */
public class Main {

    public static void main(String[] args) {
        DataManagment.createDirectoryFolders();
        if (!DataManagment.requestGamepack())
            return;

        final ConfigReader CONFIG_READER = new ConfigReader(Vars.get().JAVA_CONFIG_URL);
        final Map<String, String> CONFIG_PARAMETERS = CONFIG_READER.parseConfig();

        try {
            final URLClassLoader CLASS_LOADER = new URLClassLoader(new URL[]{new File(System.getProperty("user.home") + File.separator + DirectoryFolder.DATA.getParentDirectory().getFolderName() + File.separator + DirectoryFolder.DATA.getFolderName() + File.separator + DirectoryFile.GAMEPACK.getFileName() + DirectoryFile.GAMEPACK.getFileExtension()).toURI().toURL()});
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
        } catch (IllegalAccessException | ClassNotFoundException | MalformedURLException | InstantiationException e) {
            e.printStackTrace();
        }
    }

}

