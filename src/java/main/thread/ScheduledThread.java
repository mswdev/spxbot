package main.thread;

import main.ui.client_initialization.ClientInitializationPanel;
import main.ui.rs_applet.SplashScreenPanel;
import main.util.Logging;
import main.util.Text;

/**
 * Created by Sphiinx on 6/9/2017.
 */
public class ScheduledThread implements Runnable {

    private String STATUS_TEXT;

    @Override
    public void run() {
        System.out.println("Status: " + Logging.getDebugStatus());
        System.out.println("Status 2: " + Logging.isDebugStatusEmpty());
        if (!Logging.getDebugStatus().contains("%") && !Logging.isDebugStatusEmpty()) {
            STATUS_TEXT = Logging.getDebugStatus() + Text.getLoadingPeriods();
            System.out.println("Here 1");
        } else {
            STATUS_TEXT = Logging.getDebugStatus();
            System.out.println("Here 2");
        }

        ClientInitializationPanel.getStatusTextLabel().setText(STATUS_TEXT);
        SplashScreenPanel.getStatusTextLabel().setText(STATUS_TEXT);
    }

}

