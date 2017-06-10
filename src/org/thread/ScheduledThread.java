package org.thread;

import org.ui.SplashScreen;

/**
 * Created by Sphiinx on 6/9/2017.
 */
public class ScheduledThread implements Runnable {

    @Override
    public void run() {
        SplashScreen.updateStatusText();
    }

}

