package org.api.client;

import org.ConfigReader;
import org.data.Vars;
import org.data.enums.DirectoryFile;
import org.data.enums.DirectoryFolder;
import org.directory_managment.FileManagment;
import org.directory_managment.PropertyManagment;
import org.util.Logging;
import org.web.Request;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;

/**
 * Created by Sphiinx on 3/25/17.
 */
public class GamepackManagment {

    /**
     * Parses the config parameters from the RuneScape config URL.
     *
     * @return A Map containing the RuneScape config parameters.
     */
    public static Map<String, String> getConfigParameters() {
        final ConfigReader CONFIG_READER = new ConfigReader(Vars.get().JAVA_CONFIG_URL);
        return CONFIG_READER.parseConfig();
    }

    /**
     * Gets the newest revision from the gamepack on the RuneScape website.
     *
     * @param previous_revision The local client revision.
     * @return The newest revision; -1 if we're unable to get it.
     */
    public static int getNewestRevision(int previous_revision) {
        try {
            int possible_revision = previous_revision;
            while (true) {
                final Socket OLDSCHOOL_SOCKET;
                OLDSCHOOL_SOCKET = new Socket("oldschool1.runescape.com", 43594);
                final DataOutputStream OUTPUT_STREAM = new DataOutputStream(OLDSCHOOL_SOCKET.getOutputStream());

                OUTPUT_STREAM.writeByte(15);
                OUTPUT_STREAM.writeInt(possible_revision);
                OUTPUT_STREAM.flush();

                if (OLDSCHOOL_SOCKET.getInputStream().read() == 0) {
                    OLDSCHOOL_SOCKET.close();
                    return possible_revision;
                }

                OLDSCHOOL_SOCKET.close();
                possible_revision++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return -1;
    }

    /**
     * Gets the locally stored RuneScape gamepack revision.
     *
     * @return The locally stored RuneScape gamepack revision; -1 otherwise.
     */
    public static int getLocalRevision() {
        if (!PropertyManagment.loadProperties(DirectoryFolder.DATA.getDirectoryPath() + File.separator + DirectoryFolder.DATA.getFolderName(), File.separator + DirectoryFile.CLIENT_BUILD.getFileName(), DirectoryFile.CLIENT_BUILD.getFileExtension()))
            return -1;

        if (!PropertyManagment.getFileProperties().getProperty("local_revision").isEmpty()) {
            try {
                return Integer.parseInt(PropertyManagment.getFileProperties().getProperty("local_revision"));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        return -1;
    }

    /**
     * Requests the RuneScape gamepack from the config url.
     */
    public static void requestGamepack() {
        Logging.setDebugStatus("Acquiring the gamepack");
        final String GAMEPACK_PATH = DirectoryFolder.DATA.getDirectoryPath() + File.separator + DirectoryFolder.DATA.getFolderName();

        if (Request.requestFile(getConfigParameters().get("codebase") + getConfigParameters().get("initial_jar"), GAMEPACK_PATH, DirectoryFile.GAMEPACK.getFileName(), DirectoryFile.GAMEPACK.getFileExtension(), true))
            putRevision();
    }

    /**
     * Determines whether we need a new RuneScape gamepack. If the RuneScape gamepack was previously incorrectly acquired, it will re-aquire it.
     *
     * @return True if we need a new RuneScape gamepack; false otherwise.
     */
    public static boolean needsGamepack() {
        Logging.setDebugStatus("Verifying the gamepack");
        final int GAMEPACK_SIZE = Request.requestFileSize(getConfigParameters().get("codebase") + getConfigParameters().get("initial_jar"));

        final String GAMEPACK_PATH = DirectoryFolder.DATA.getDirectoryPath() + File.separator + DirectoryFolder.DATA.getFolderName();
        final File CURRENT_GAMEPACK = FileManagment.getFileInDirectory(GAMEPACK_PATH, DirectoryFile.GAMEPACK.getFileName(), DirectoryFile.GAMEPACK.getFileExtension());
        if (CURRENT_GAMEPACK == null)
            return true;

        if (CURRENT_GAMEPACK.length() != GAMEPACK_SIZE)
            return true;

        if (getLocalRevision() == -1)
            return true;

        return getLocalRevision() != getNewestRevision(getLocalRevision());
    }

    /**
     * Puts the current local revision in the client build data file.
     */
    public static void putRevision() {
        final String PROPERTY_NAME = "local_revision";
        final String REVISION = Integer.toString(getNewestRevision(Vars.get().LAST_KNOWN_REVISION));
        final String FILE_PATH = DirectoryFolder.DATA.getDirectoryPath() + File.separator + DirectoryFolder.DATA.getFolderName();
        final String FILE_NAME = File.separator + DirectoryFile.CLIENT_BUILD.getFileName();
        final String FILE_EXTENSION = DirectoryFile.CLIENT_BUILD.getFileExtension();
        PropertyManagment.putProperty(PROPERTY_NAME, REVISION, FILE_PATH, FILE_NAME, FILE_EXTENSION);
    }

}

