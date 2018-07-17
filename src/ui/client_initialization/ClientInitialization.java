package ui.client_initialization;


import client.file_management.AssetManagement;
import client.file_management.DirectoryManagement;
import database.DBManager;

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

        DBManager.establishConnection();
        DBManager.setClientOnlineStatus();
        DBManager.setClientVersion();

        DirectoryManagement.createDirectoryFolders();
        DirectoryManagement.createDirectoryFiles();
        DirectoryManagement.createFileProperties();

        AssetManagement.requestAssets();

        CLIENT_INITIALIZATION_FRAME.setVisible(false);
    }

}

