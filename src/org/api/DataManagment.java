package org.api;

import org.ConfigReader;
import org.data.Vars;
import org.data.enums.DirectoryFile;
import org.data.enums.DirectoryFolder;
import org.filemanagment.FileManagment;
import org.util.Logging;
import org.web.Request;

import java.io.File;
import java.util.Map;

/**
 * Created by Sphiinx on 3/24/17.
 */
public class DataManagment {

    /**
     * Creates all of the directory folders in the specified locations from the DirectoryFolder enum.
     * If it fails to create a directory, it will throw an exception while noting which directory failed to be created and it's creation path.
     */
    public static void createDirectoryFolders() {
        final DirectoryFolder[] directory_folders = DirectoryFolder.values();
        for (DirectoryFolder directory_folder : directory_folders) {
            String DIRECTORY_PATH = System.getProperty("user.home") + File.separator;
            if (directory_folder.getParentDirectory() != null)
                DIRECTORY_PATH = System.getProperty("user.home") + File.separator + directory_folder.getParentDirectory().getFolderName() + File.separator;

            final File file = FileManagment.getFileInDirectory(DIRECTORY_PATH, directory_folder.getFolderName());
            if (file != null)
                continue;

            try {
                FileManagment.createDirectory(DIRECTORY_PATH, directory_folder.getFolderName());
            } catch (Exception e) {
                Logging.error("Unable to create directory folder: " + directory_folder.getFolderName());
                Logging.error("Failed creation path: " + DIRECTORY_PATH);
                e.printStackTrace();
            }
        }
    }

    /**
     * Requests the RuneScape gamepack from the config url. If the RuneScape gamepack was previously incorrectly aquired, it will re-aquire it.
     * If it fails to request the RuneScape gamepack, it will throw an exception while noting the failed url and creation path.
     *
     * TODO Update the gamepack if it's out of date, or check if it automatically updates if it's out of date.
     *
     * @return True if the RuneScape gamepack was successfully aquired; false otherwise.
     * */
    public static boolean requestGamepack() {
        final ConfigReader CONFIG_READER = new ConfigReader(Vars.get().JAVA_CONFIG_URL);
        final Map<String, String> CONFIG_PARAMETERS = CONFIG_READER.parseConfig();
        final int GAMEPACK_SIZE = Request.requestFileSize(CONFIG_PARAMETERS.get("codebase") + CONFIG_PARAMETERS.get("initial_jar"));

        final String GAMEPACK_PATH = System.getProperty("user.home") + File.separator + DirectoryFolder.DATA.getParentDirectory().getFolderName() + File.separator + DirectoryFolder.DATA.getFolderName() + File.separator;
        final File CURRENT_GAMEPACK = FileManagment.getFileInDirectory(GAMEPACK_PATH, DirectoryFile.GAMEPACK.getFileName() + DirectoryFile.GAMEPACK.getFileExtension());
        if (CURRENT_GAMEPACK != null && CURRENT_GAMEPACK.length() == GAMEPACK_SIZE)
            return true;

        try {
            Request.requestFile(CONFIG_PARAMETERS.get("codebase") + CONFIG_PARAMETERS.get("initial_jar"), GAMEPACK_PATH, DirectoryFile.GAMEPACK.getFileName() + DirectoryFile.GAMEPACK.getFileExtension());
            final File DOWNLOADED_GAMEPACK = FileManagment.getFileInDirectory(GAMEPACK_PATH, DirectoryFile.GAMEPACK.getFileName() + DirectoryFile.GAMEPACK.getFileExtension());
            if (DOWNLOADED_GAMEPACK == null)
                return false;

            if (DOWNLOADED_GAMEPACK.length() == GAMEPACK_SIZE)
                return true;
        } catch (Exception e) {
            Logging.error("Unable to download the RuneScape gamepack.jar from the URL: " + CONFIG_PARAMETERS.get("codebase") + CONFIG_PARAMETERS.get("initial_jar"));
            Logging.error("Failed creation path: " + System.getProperty("user.home") + GAMEPACK_PATH);
            e.printStackTrace();
        }

        return false;
    }

}

