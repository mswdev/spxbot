package org;

import org.api.DirectoryManagment;
import org.api.GamepackManagment;
import org.api.General;
import org.data.Vars;
import org.data.enums.DirectoryFile;
import org.data.enums.DirectoryFolder;
import org.thread.ScheduledThread;
import org.ui.*;
import org.ui.SplashScreen;
import org.util.Logging;

import java.applet.Applet;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sphiinx on 3/23/17.
 */
public class Main {

    private static final AppletFrame APPLET_FRAME = new AppletFrame();

    private static final AppletPanel APPLET_PANEL = new AppletPanel();

    private static final SplashScreen SPLASH_SCREEN = new SplashScreen();

    private static final ScheduledThreadPoolExecutor THREAD_POOL_EXECUTOR = new ScheduledThreadPoolExecutor(3);

    public static void main(String[] args) {
        Logging.setDebug(true);

        APPLET_FRAME.initializeAppletFrame();
        SPLASH_SCREEN.initializeSplashScreen();
        APPLET_FRAME.getAppletFrame().setVisible(true);

        THREAD_POOL_EXECUTOR.scheduleAtFixedRate(new ScheduledThread(), 0, 100, TimeUnit.MILLISECONDS);

        DirectoryManagment.createDirectoryFolders();
        DirectoryManagment.createDirectoryFiles();
        DirectoryManagment.createFileProperties();
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


            APPLET_FRAME.getAppletFrame().remove(SPLASH_SCREEN.getSplashScreenPanel());
            APPLET_PANEL.initializeAppletPanel();
            APPLET_PANEL.getAppletPanel().add(APPLET);

            General.sleep(150);
            APPLET_FRAME.getAppletFrame().pack();
        } catch (IllegalAccessException | ClassNotFoundException | MalformedURLException | InstantiationException e) {
            e.printStackTrace();
        }
    }


}

