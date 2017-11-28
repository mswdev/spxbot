package main.api.client;


import main.data.enums.DirectoryFile;
import main.data.enums.DirectoryFolder;
import main.util.Logging;
import main.util.directory_managment.FileManagment;
import main.web.Request;

import java.io.File;

/**
 * Created by Sphiinx on 6/16/2017.
 */
public class AssetManagment {

    /**
     * Requests the assets from the asset URL's. It will only requests the assets that we do not have.
     * */
    public static void requestAssets() {
        Logging.setDebugStatus("Requesting assets");
        final String DIRECTORY_PATH = DirectoryFolder.ASSETS.getDirectoryPath() + File.separator + DirectoryFolder.ASSETS.getFolderName();

        final DirectoryFolder DIRECTORY_FOLDER = DirectoryFolder.ASSETS;
        for (DirectoryFile directory_file : DIRECTORY_FOLDER.getDirectoryFiles()) {
            final File FILE = FileManagment.getDirectoryInDirectory(DIRECTORY_PATH, directory_file.getFileName() + directory_file.getFileExtension());
            if (FILE != null)
                continue;

            Request.getFile(directory_file.getFileURL(), DIRECTORY_PATH, directory_file.getFileName(), directory_file.getFileExtension(), true);
        }
    }

}

