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
public class DirectoryManagment {

    /**
     * **NEW FILE MANAGMENT**
     *
     * 1. Create each directory like normal
     * 2. Then go and create each file in the directorys
     * 3. Then go and store all the settings in each director.
     *
     * */

    /**
     * Creates all of the directory folders in the specified locations from the DirectoryFolder enum.
     * If it fails to create a directory, it will throw an error message showing which directory failed to be created and it's creation path.
     *
     * @return True if all of the directories were successfully created; false otherwise.
     */
    public static boolean createDirectoryFolders() {
        final DirectoryFolder[] DIRECTORY_FOLDERS = DirectoryFolder.values();
        final int TOTAL_DIRECTORIES = DIRECTORY_FOLDERS.length;
        int created_directories = 0;
        for (DirectoryFolder directory_folder : DIRECTORY_FOLDERS) {
            final String DIRECTORY_PATH = directory_folder.getDirectoryPath();

            final File FILE = FileManagment.getFileInDirectory(DIRECTORY_PATH, directory_folder.getFolderName());
            if (FILE != null) {
                created_directories++;
                continue;
            }

            if (FileManagment.createDirectory(DIRECTORY_PATH, directory_folder.getFolderName())) {
                created_directories++;
            } else {
                Logging.error("Unable to create directory folder: " + directory_folder.getFolderName());
                Logging.error("Failed creation path: " + DIRECTORY_PATH);
            }
        }

        return TOTAL_DIRECTORIES == created_directories;
    }

    /**
     * Creates the directory files in the current directory that is being created.
     *
     */
    public static boolean createDirectoryFiles() {
        final DirectoryFolder[] DIRECTORY_FOLDERS = DirectoryFolder.values();
        for (DirectoryFolder directory_folder : DIRECTORY_FOLDERS) {
            if (directory_folder.getDirectoryFiles() == null)
                continue;

            final DirectoryFile[] DIRECTORY_FILES = DirectoryFile.values();
            final int TOTAL_FILES = DIRECTORY_FILES.length;
            int created_files = 0;
            for (DirectoryFile directory_file : DIRECTORY_FILES) {
                if (!directory_file.shouldCreateFile()) {
                    created_files++;
                    continue;
                }

                final File FILE = FileManagment.getFileInDirectory(directory_folder.getDirectoryPath() + File.separator + directory_folder.getFolderName(), directory_file.getFileName() + directory_file.getFileExtension());
                if (FILE != null) {
                    created_files++;
                    continue;
                }

                if (FileManagment.createFile(directory_folder.getDirectoryPath() + File.separator + directory_folder.getFolderName(), directory_file.getFileName(), directory_file.getFileExtension())) {
                    created_files++;
                } else {
                    Logging.error("Unable to create directory file: " + directory_file.getFileName());
                    Logging.error("Failed creation path: " + directory_folder.getDirectoryPath() + File.separator + directory_folder.getFolderName());
                }
            }
            return TOTAL_FILES == created_files;
        }

        return false;
    }

    /**
     * Requests the RuneScape gamepack from the config url. If the RuneScape gamepack was previously incorrectly acquired, it will re-aquire it.
     * If it fails to request the RuneScape gamepack, it will throw an error message showing the failed url and creation path.
     * <p>
     * TODO Update the gamepack if it's out of date, or check if it automatically updates if it's out of date.
     *
     * @return True if the RuneScape gamepack was successfully acquired; false otherwise.
     */
    public static boolean requestGamepack() {
        final ConfigReader CONFIG_READER = new ConfigReader(Vars.get().JAVA_CONFIG_URL);
        final Map<String, String> CONFIG_PARAMETERS = CONFIG_READER.parseConfig();
        final int GAMEPACK_SIZE = Request.requestFileSize(CONFIG_PARAMETERS.get("codebase") + CONFIG_PARAMETERS.get("initial_jar"));

        final String GAMEPACK_PATH = DirectoryFolder.DATA.getDirectoryPath() + File.separator + DirectoryFolder.DATA.getFolderName();
        final File CURRENT_GAMEPACK = FileManagment.getFileInDirectory(GAMEPACK_PATH, DirectoryFile.GAMEPACK.getFileName() + DirectoryFile.GAMEPACK.getFileExtension());
        if (CURRENT_GAMEPACK != null && CURRENT_GAMEPACK.length() == GAMEPACK_SIZE)
            return true;

        if (Request.requestFile(CONFIG_PARAMETERS.get("codebase") + CONFIG_PARAMETERS.get("initial_jar"), GAMEPACK_PATH, DirectoryFile.GAMEPACK.getFileName() + DirectoryFile.GAMEPACK.getFileExtension())) {
            return true;
        } else {
            Logging.error("Unable to download the RuneScape gamepack.jar from the URL: " + CONFIG_PARAMETERS.get("codebase") + CONFIG_PARAMETERS.get("initial_jar"));
            Logging.error("Failed creation path: " + System.getProperty("user.home") + GAMEPACK_PATH);
            return false;
        }
    }

}

