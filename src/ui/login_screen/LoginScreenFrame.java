package ui.login_screen;

import data.Vars;

import javax.swing.*;

/**
 * Created by Sphiinx on 6/15/2017.
 */
public class LoginScreenFrame extends JFrame {

    /**
     * The width for the login screen frame.
     */
    private final int LOGIN_SCREEN_FRAME_WIDTH = 250;

    /**
     * The height for the login screen frame.
     */
    private final int LOGIN_SCREEN_FRAME_HEIGHT = 295;

    /**
     * The String containing the login screen frame title.
     */
    private static final String LOGIN_SCREEN_FRAME_TITLE = " - Login";

    /**
     * Initializes the login screen frame.
     */
    public void initializeLoginScreenFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(Vars.get().APPLET_NAME + LOGIN_SCREEN_FRAME_TITLE);
        setSize(LOGIN_SCREEN_FRAME_WIDTH, LOGIN_SCREEN_FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
    }

}

