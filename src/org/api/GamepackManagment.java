package org.api;

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
        final File CURRENT_GAMEPACK = FileManagment.getFileInDirectory(GAMEPACK_PATH, DirectoryFile.GAMEPACK.getFileName(), DirectoryFile.GAMEPACK.getFileExtension());
        if (CURRENT_GAMEPACK != null && CURRENT_GAMEPACK.length() == GAMEPACK_SIZE)
            return true;

        if (Request.requestFile(CONFIG_PARAMETERS.get("codebase") + CONFIG_PARAMETERS.get("initial_jar"), GAMEPACK_PATH, DirectoryFile.GAMEPACK.getFileName(), DirectoryFile.GAMEPACK.getFileExtension(), true)) {
            return true;
        } else {
            Logging.error("Unable to download the RuneScape gamepack.jar from the URL: " + CONFIG_PARAMETERS.get("codebase") + CONFIG_PARAMETERS.get("initial_jar"));
            Logging.error("Failed creation path: " + System.getProperty("user.home") + GAMEPACK_PATH);
            return false;
        }
    }

    /**
     * Determines whether we need a new RuneScape gamepack.
     *
     * @return True if we need a new RuneScape gamepack; false otherwise.
     * */
    public static boolean needsGamepack() {
        if (getLocalGamepackRevision() == -1)
            return true;

        return getLocalGamepackRevision() != getNewestRevision(getLocalGamepackRevision());
    }


    /**
     * Gets the locally stored RuneScape gamepack revision.
     *
     * @return The locally stored RuneScape gamepack revision; -1 otherwise.
     */
    public static int getLocalGamepackRevision() {
        if (!PropertyManagment.loadProperties(DirectoryFolder.DATA.getDirectoryPath() + File.separator + DirectoryFolder.DATA.getFolderName(), File.separator + DirectoryFile.CLIENT_BUILD.getFileName(), DirectoryFile.CLIENT_BUILD.getFileExtension()))
            return -1;

        int local_revision = -1;
        try {
            local_revision = Integer.parseInt(PropertyManagment.getFileProperties().getProperty("local_revision"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return local_revision;
    }

}

