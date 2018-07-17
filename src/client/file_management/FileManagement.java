package client.file_management;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManagement {

    /**
     * Creates a directory at the specified path with the specified name.
     *
     * @param creation_path  The path to create the directory.
     * @param directory_name The name to name the directory.
     * @return True if successful; false otherwise.
     */
    public static boolean createDirectory(String creation_path, String directory_name) {
        final File directory = new File(creation_path, directory_name);
        return directory.mkdir();
    }

    /**
     * Creates a file at the specified path with the specified name and extension.
     *
     * @param creation_path  The path to create the file.
     * @param file_name      The name to name the file.
     * @param file_extension The extension of the file.
     * @return True if successful; false otherwise.
     */
    public static boolean createFile(String creation_path, String file_name, String file_extension) {
        final File file = new File(creation_path, file_name + file_extension);
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Gets the specified file name in the specified directory.
     *
     * @param file_name      The name of the file to get from the directory.
     * @param file_path      The directory to get the file from.
     * @param file_extension The file extension.
     * @return The specified file if found; null otherwise.
     */
    public static File getFileInDirectory(String file_path, String file_name, String file_extension) {
        final File file = new File(file_path);
        final File[] matching_files = file.listFiles((dir, name) -> name.equals(file_name + file_extension));
        if (matching_files == null)
            return null;

        return matching_files.length > 0 ? matching_files[0] : null;
    }

    /**
     * Gets the specified directory in the specified directory.
     *
     * @param file_name The name of the directory to get from the directory.
     * @param file_path The directory to get the directory from.
     * @return The specified directory if found; null otherwise.
     */
    public static File getDirectoryInDirectory(String file_path, String file_name) {
        return getFileInDirectory(file_path, file_name, "");
    }

    /**
     * Gets all of the files in the specified directory.
     *
     * @param directory_path The path to get the files from.
     * @return A File array if any files are present; null otherwise.
     */
    public static File[] getFilesInDirectory(String directory_path) {
        final File file = new File(directory_path);
        final File[] files = file.listFiles();
        if (files == null)
            return null;

        return files;
    }

    /**
     * Gets all of the file names in the specified directory.
     *
     * @param directory_path The path to get the files from.
     * @return A File array if any files are present; null otherwise.
     */
    public static String[] getFileNamesInDirectory(String directory_path) {
        final List<String> file_names = new ArrayList<>();
        final File[] files = getFilesInDirectory(directory_path);
        if (files == null)
            return null;

        for (File file : files) {
            if (file == null)
                continue;

            file_names.add(file.getName());
        }

        return file_names.toArray(new String[0]);
    }

}

