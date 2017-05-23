package org.api;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Sphiinx on 3/25/17.
 */
public class Gamepack {

    /**
     * Gets the newest revision from the gamepack on the RuneScape website.
     *
     * @param previous_revision The local client revision.
     * @return The newest revision.
     * */
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

}

