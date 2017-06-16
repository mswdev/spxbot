package org.thread;

import org.ui.client_initialization.ClientInitializationPanel;
import org.ui.rs_applet.SplashScreenPanel;
import org.util.Logging;
import org.util.Text;

/**
 * Created by Sphiinx on 6/9/2017.
 */
public class ScheduledThread implements Runnable {

    private String STATUS_TEXT;

    @Override
    public void run() {
        if (!Logging.getDebugStatus().contains("%"))
            STATUS_TEXT = Logging.getDebugStatus() + Text.getLoadingPeriods();
        else
            STATUS_TEXT = Logging.getDebugStatus();

        ClientInitializationPanel.getStatusTextLabel().setText(STATUS_TEXT);
        SplashScreenPanel.getStatusTextLabel().setText(STATUS_TEXT);
    }

}

