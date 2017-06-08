package org.api;

import org.data.enums.DirectoryFile;
import org.data.enums.DirectoryFolder;
import org.directory_managment.FileManagment;
import org.directory_managment.PropertyManagment;
import org.util.Logging;

import java.io.File;
import java.io.FileOutputStream;

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

            final File FILE = FileManagment.getDirectoryInDirectory(DIRECTORY_PATH, directory_folder.getFolderName());
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

                final File FILE = FileManagment.getFileInDirectory(directory_folder.getDirectoryPath() + File.separator + directory_folder.getFolderName(), directory_file.getFileName(),  directory_file.getFileExtension());
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
     * Creates the file properties for each file from the DirectoryFile enum.
     *
     * @return True if the file properties were successfully created; false otherwise.
     * */
    public static boolean createFileProperties() {
        try {
            final DirectoryFolder[] DIRECTORY_FOLDERS = DirectoryFolder.values();
            for (DirectoryFolder directory_folder : DIRECTORY_FOLDERS) {
                if (directory_folder.getDirectoryFiles() == null)
                    continue;

                final DirectoryFile[] DIRECTORY_FILES = DirectoryFile.values();
                for (DirectoryFile directory_file : DIRECTORY_FILES) {
                    if (!directory_file.shouldCreateFile())
                        continue;

                    final File FILE = FileManagment.getFileInDirectory(directory_folder.getDirectoryPath() + File.separator + directory_folder.getFolderName(), directory_file.getFileName(), directory_file.getFileExtension());
                    if (FILE == null)
                        continue;

                    if (!PropertyManagment.loadProperties(directory_folder.getDirectoryPath() + File.separator + directory_folder.getFolderName(), File.separator + directory_file.getFileName(), directory_file.getFileExtension()))
                        return false;

                    for (String property : directory_file.getProperties()) {
                        if (PropertyManagment.getFileProperties().getProperty(property) != null)
                            continue;

                        PropertyManagment.getFileProperties().put(property, "");
                    }

                    PropertyManagment.getFileProperties().store(new FileOutputStream(FILE), "settings");
                    PropertyManagment.getFileProperties().clear();
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}

