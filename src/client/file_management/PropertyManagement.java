package client.file_management;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManagement {

    /**
     * File properties.
     */
    private static Properties file_properties = new Properties();

    /**
     * Gets the file properties.
     *
     * @return The file properties; null otherwise.
     */
    public static Properties getFileProperties() {
        return file_properties;
    }

    /**
     * Sets the file properties.
     *
     * @param properties_to_set The properties to use.
     */
    public static void setProperties(Properties properties_to_set) {
        file_properties = properties_to_set;
    }

    /**
     * Loads the properties from the specified file.
     *
     * @param file_path      The path to the file.
     * @param file_name      The name of the file.
     * @param file_extension The extension of the file.
     * @return True if the properties were successfully loaded; false otherwise.
     */
    public static boolean loadProperties(String file_path, String file_name, String file_extension) {
        if (file_name == null || file_path == null || file_extension == null)
            return false;

        FileInputStream FILE_INPUT_STREAM = null;
        try {
            FILE_INPUT_STREAM = new FileInputStream(file_path + file_name + file_extension);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (FILE_INPUT_STREAM == null)
            return false;

        try {
            file_properties.load(FILE_INPUT_STREAM);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Puts the specified property in the specified location.
     *
     * @param property_name   The property name to modified the value of.
     * @param property_to_put The property value to put.
     * @param file_path       The path to the file.
     * @param file_name       The name of the file.
     * @param file_extension  The extension of the file.
     */
    public static void putProperty(String property_name, String property_to_put, String file_path, String file_name, String file_extension) {
        if (file_name == null || file_path == null || file_extension == null)
            return;

        if (!loadProperties(file_path, file_name, file_extension))
            return;

        getFileProperties().put(property_name, String.valueOf(property_to_put));
        try {
            getFileProperties().store(new FileOutputStream(file_path + file_name + file_extension), "Settings");
        } catch (IOException e) {
            e.printStackTrace();
        }
        getFileProperties().clear();
    }

}

