package org.ui.rs_applet;

import org.RSAppletStub;
import org.api.General;
import org.api.client.DirectoryManagment;
import org.api.client.GamepackManagment;
import org.data.Vars;
import org.data.enums.DirectoryFile;
import org.data.enums.DirectoryFolder;
import org.thread.ScheduledThread;
import org.util.Logging;

import java.applet.Applet;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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


        if (GamepackManagment.needsGamepack())
            GamepackManagment.requestGamepack();

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

