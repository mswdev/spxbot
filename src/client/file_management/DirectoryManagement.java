package client.file_management;

import api.util.Logging;
import data.enums.DirectoryFile;
import data.enums.DirectoryFolder;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Sphiinx on 3/24/17.
 */
public class DirectoryManagement {

    /**
     * Creates all of the directory folders in the specified locations from the DirectoryFolder enum.
     * If it fails to create a directory, it will throw an error message showing which directory failed to be created and it's creation path.
     *
     * @return True if all of the directories were successfully created; false otherwise.
     */
    public static void createDirectoryFolders() {
        Logging.setDebugStatus("Creating directory folders");
        final DirectoryFolder[] DIRECTORY_FOLDERS = DirectoryFolder.values();
        for (DirectoryFolder directory_folder : DIRECTORY_FOLDERS) {
            final String DIRECTORY_PATH = directory_folder.getDirectoryPath();

            final File FILE = FileManagement.getDirectoryInDirectory(DIRECTORY_PATH, directory_folder.getFolderName());
            if (FILE != null)
                continue;

            if (!FileManagement.createDirectory(DIRECTORY_PATH, directory_folder.getFolderName())) {
                Logging.error("Unable to create directory folder: " + directory_folder.getFolderName());
                Logging.error("Failed creation path: " + DIRECTORY_PATH);
            }
        }
    }

    /**
     * Creates the directory files in the current directory that is being created.
     */
    public static void createDirectoryFiles() {
        Logging.setDebugStatus("Creating directory files");
        final DirectoryFolder[] DIRECTORY_FOLDERS = DirectoryFolder.values();
        for (DirectoryFolder directory_folder : DIRECTORY_FOLDERS) {
            if (directory_folder.getDirectoryFiles() == null)
                continue;

            final DirectoryFile[] DIRECTORY_FILES = directory_folder.getDirectoryFiles();
            for (DirectoryFile directory_file : DIRECTORY_FILES) {
                if (!directory_file.shouldCreateFile())
                    continue;

                final File FILE = FileManagement.getFileInDirectory(directory_folder.getDirectoryPath() + File.separator + directory_folder.getFolderName(), directory_file.getFileName(), directory_file.getFileExtension());
                if (FILE != null)
                    continue;

                if (!FileManagement.createFile(directory_folder.getDirectoryPath() + File.separator + directory_folder.getFolderName(), directory_file.getFileName(), directory_file.getFileExtension())) {
                    Logging.error("Unable to create directory file: " + directory_file.getFileName());
                    Logging.error("Failed creation path: " + directory_folder.getDirectoryPath() + File.separator + directory_folder.getFolderName());
                }
            }
        }
    }

    /**
     * Creates the file properties for each file from the DirectoryFile enum.
     */
    public static void createFileProperties() {
        Logging.setDebugStatus("Creating file properties");
        try {
            final DirectoryFolder[] DIRECTORY_FOLDERS = DirectoryFolder.values();
            for (DirectoryFolder directory_folder : DIRECTORY_FOLDERS) {
                if (directory_folder.getDirectoryFiles() == null)
                    continue;

                final DirectoryFile[] DIRECTORY_FILES = DirectoryFile.values();
                for (DirectoryFile directory_file : DIRECTORY_FILES) {
                    if (!directory_file.shouldCreateFile())
                        continue;

                    final File FILE = FileManagement.getFileInDirectory(directory_folder.getDirectoryPath() + File.separator + directory_folder.getFolderName(), directory_file.getFileName(), directory_file.getFileExtension());
                    if (FILE == null)
                        continue;

                    if (!PropertyManagement.loadProperties(directory_folder.getDirectoryPath() + File.separator + directory_folder.getFolderName(), File.separator + directory_file.getFileName(), directory_file.getFileExtension()))
                        return;

                    for (String property : directory_file.getProperties()) {
                        if (PropertyManagement.getFileProperties().getProperty(property) != null)
                            continue;

                        PropertyManagement.getFileProperties().put(property, "");
                    }

                    PropertyManagement.getFileProperties().store(new FileOutputStream(FILE), "settings");
                    PropertyManagement.getFileProperties().clear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

