package ui.client_initialization;

import data.Vars;

import javax.swing.*;

/**
 * Created by Sphiinx on 6/16/2017.
 */
public class ClientInitializationFrame extends JFrame {

    /**
     * The width for the client initialization frame.
     */
    private final int CLIENT_INITIALIZATION_FRAME_WIDTH = 290;

    /**
     * The height for the client initialization frame.
     */
    private final int CLIENT_INITIALIZATION_FRAME_HEIGHT = 150;

    /**
     * The String containing the client initialization frame title.
     */
    private static final String CLIENT_INITIALIZATION_FRAME_TITLE = " - Client Initialization";

    /**
     * Initializes the client initialization frame.
     */
    public void initializeClientInitializationFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(Vars.get().APPLET_NAME + CLIENT_INITIALIZATION_FRAME_TITLE);
        setSize(CLIENT_INITIALIZATION_FRAME_WIDTH, CLIENT_INITIALIZATION_FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
    }

}

