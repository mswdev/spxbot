package org.api.data_managment;

import org.data.enums.DirectoryFile;
import org.data.enums.DirectoryFolder;
import org.directory_managment.FileManagment;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * Created by Sphiinx on 3/26/17.
 */
public class DataManagment {

    private final static Properties file_properties = new Properties();

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

                    for (String property : directory_file.getProperties()) {
                        file_properties.put(property, "");
                    }
                    file_properties.store(new FileOutputStream(FILE), "settings");
                    file_properties.clear();
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}

