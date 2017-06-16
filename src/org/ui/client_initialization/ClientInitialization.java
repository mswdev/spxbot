package org.ui.client_initialization;

import org.api.client.AssetManagment;
import org.api.client.DirectoryManagment;

/**
 * Created by Sphiinx on 6/16/2017.
 */
public class ClientInitialization {

    /**
     * The client initialization frame.
     */
    private ClientInitializationFrame CLIENT_INITIALIZATION_FRAME = new ClientInitializationFrame();

    /**
     * The client initialization panel.
     */
    private ClientInitializationPanel CLIENT_INITIALIZATION_PANEL = new ClientInitializationPanel();

    /**
     * Runs the client initialization GUI.
     **/
    public void run() {
        CLIENT_INITIALIZATION_FRAME.initializeClientInitializationFrame();
        CLIENT_INITIALIZATION_PANEL.initializeClientInitializationPanel();
        CLIENT_INITIALIZATION_FRAME.add(CLIENT_INITIALIZATION_PANEL);
        CLIENT_INITIALIZATION_FRAME.setVisible(true);

        AssetManagment.requestAssets();
        DirectoryManagment.createDirectoryFolders();
        DirectoryManagment.createDirectoryFiles();
        DirectoryManagment.createFileProperties();

        CLIENT_INITIALIZATION_FRAME.setVisible(false);
    }

}

