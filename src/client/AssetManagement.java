package client;


import client.file_management.FileManagement;
import api.util.Logging;
import api.web.Request;
import data.enums.DirectoryFile;
import data.enums.DirectoryFolder;

import java.io.File;

/**
 * Created by Sphiinx on 6/16/2017.
 */
public class AssetManagement {

    /**
     * Requests the assets from the asset URL's. It will only requests the assets that we do not have.
     * */
    public static void requestAssets() {
        Logging.setDebugStatus("Requesting assets");
        final String DIRECTORY_PATH = DirectoryFolder.ASSETS.getDirectoryPath() + File.separator + DirectoryFolder.ASSETS.getFolderName();

        final DirectoryFolder DIRECTORY_FOLDER = DirectoryFolder.ASSETS;
        for (DirectoryFile directory_file : DIRECTORY_FOLDER.getDirectoryFiles()) {
            final File FILE = FileManagement.getDirectoryInDirectory(DIRECTORY_PATH, directory_file.getFileName() + directory_file.getFileExtension());
            if (FILE != null)
                continue;

            Request.getFile(directory_file.getFileURL(), DIRECTORY_PATH, directory_file.getFileName(), directory_file.getFileExtension(), true);
        }
    }

}

