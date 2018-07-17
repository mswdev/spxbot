package thread;

import api.util.Logging;
import api.util.Text;
import ui.client_initialization.ClientInitializationPanel;
import ui.rs_applet.SplashScreenPanel;


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

