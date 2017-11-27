package org.ui.login_screen;

import org.api.General;
import org.api.client.AssetManagment;
import org.api.client.DirectoryManagment;
import org.data.Vars;
import org.util.Logging;

/**
 * Created by Sphiinx on 6/16/2017.
 */
public class LoginScreen {

    /**
     * The login screen frame
     */
    private LoginScreenFrame LOGIN_SCREEN_FRAME = new LoginScreenFrame();

    /**
     * The login screen panel.
     */
    private LoginScreenPanel LOGIN_SCREEN_PANEL = new LoginScreenPanel();

    /**
     * Runs the login screen GUI.
     **/
    public void run() {
        LOGIN_SCREEN_FRAME.initializeLoginScreenFrame();
        LOGIN_SCREEN_PANEL.initializeLoginScreenPanel();
        LOGIN_SCREEN_FRAME.add(LOGIN_SCREEN_PANEL);
        LOGIN_SCREEN_FRAME.setVisible(true);

        while (!LOGIN_SCREEN_PANEL.hasLoggedIn())
            General.sleep(150);

        LOGIN_SCREEN_FRAME.setVisible(false);
    }

}

