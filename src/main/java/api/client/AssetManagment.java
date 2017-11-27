package org.api.client;

import org.data.enums.DirectoryFile;
import org.data.enums.DirectoryFolder;
import org.util.Logging;
import org.util.directory_managment.FileManagment;
import org.web.Request;

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

            Request.requestFile(directory_file.getFileURL(), DIRECTORY_PATH, directory_file.getFileName(), directory_file.getFileExtension(), true);
        }
    }

}

