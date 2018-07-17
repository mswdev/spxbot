package ui.rs_applet;

import api.General;
import client.file_management.GamepackManagement;
import data.Vars;
import data.enums.DirectoryFile;
import data.enums.DirectoryFolder;

import java.applet.Applet;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by Sphiinx on 6/10/2017.
 */
public class RSFrame {

    private final AppletFrame APPLET_FRAME = new AppletFrame();

    private final AppletPanel APPLET_PANEL = new AppletPanel();

    private final SplashScreenPanel SPLASH_SCREEN_PANEL = new SplashScreenPanel();

    public void run() {
        APPLET_FRAME.initializeAppletFrame();
        SPLASH_SCREEN_PANEL.initializeSplashScreenPanel();
        APPLET_FRAME.add(SPLASH_SCREEN_PANEL);
        APPLET_FRAME.setVisible(true);


        if (GamepackManagement.needsGamepack())
            GamepackManagement.requestGamepack();

        try {
            final URLClassLoader CLASS_LOADER = new URLClassLoader(new URL[]{new File(DirectoryFolder.DATA.getDirectoryPath() + File.separator + DirectoryFolder.DATA.getFolderName() + File.separator + DirectoryFile.GAMEPACK.getFileName() + DirectoryFile.GAMEPACK.getFileExtension()).toURI().toURL()});
            final Class<?> CLIENT_CLASS = CLASS_LOADER.loadClass(GamepackManagement.getConfigParameters().get("initial_class").replace(".class", ""));
            final Applet APPLET = (Applet) CLIENT_CLASS.newInstance();
            final RSAppletStub APPLET_STUB = new RSAppletStub(GamepackManagement.getConfigParameters());
            APPLET_STUB.getAppletContext().setApplet(APPLET);
            APPLET.setStub(APPLET_STUB);
            APPLET.init();
            APPLET.setSize(Vars.get().APPLET_WIDTH, Vars.get().APPLET_HEIGHT);
            APPLET_STUB.setActive(true);

            APPLET_FRAME.remove(SPLASH_SCREEN_PANEL);
            APPLET_PANEL.initializeAppletPanel();
            APPLET_FRAME.add(APPLET_PANEL);
            APPLET_PANEL.add(APPLET);

            General.sleep(150);
            APPLET_FRAME.pack();
        } catch (IllegalAccessException | ClassNotFoundException | MalformedURLException | InstantiationException e) {
            e.printStackTrace();
        }
    }

}

