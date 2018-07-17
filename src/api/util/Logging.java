package api.util;

public class Logging {

    /**
     * Debug status.
     */
    private static String debug_status;

    /**
     * Debug mode.
     */
    private static boolean is_debug;

    /**
     * Prints the specified message with the header "ERROR: ".
     *
     * @param message The message to print.
     */
    public static void error(String message) {
        System.out.println("[ERROR]: " + message);
    }

    /**
     * Prints the specified message with the header "WARNING: ".
     *
     * @param message The message to print.
     */
    public static void warning(String message) {
        System.out.println("[WARNING]: " + message);
    }

    /**
     * Prints the specified message with the header "STATUS: ".
     *
     * @param message The message to print.
     */
    public static void status(String message) {
        System.out.println("[STATUS]: " + message);
    }

    /**
     * Prints the specified message with the header "DEBUG: ". Debug messages will only be printed if the debug boolean
     * is true.
     *
     * @param message The message to print.
     */
    public static void debug(String message) {
        if (!is_debug)
            return;

        System.out.println("[DEBUG]: " + message);
    }

    /**
     * Sets the debug boolean to the specified value.
     *
     * @param set_debug True if to enable debug mode; false otherwise.
     */
    public static void setDebug(boolean set_debug) {
        is_debug = set_debug;
    }

    /**
     * Checks if the debug boolean is true or not.
     *
     * @return True if the debug boolean is true; false otherwise.
     */
    public static boolean isDebug() {
        return is_debug;
    }

    /**
     * Sets the debug status.
     *
     * @param status_to_set The status to set.
     */
    public static void setDebugStatus(String status_to_set) {
        debug_status = status_to_set;
    }

    /**
     * Gets the debug status.
     *
     * @return The debug status.
     */
    public static String getDebugStatus() {
        if (debug_status == null)
            return "[STATUS]: ";

        return "[STATUS]: " + debug_status;
    }

    /**
     * Checks whether the debug status is empty or not.
     *
     * @return True if the debug status is empty; false otherwise.
     */
    public static boolean isDebugStatusEmpty() {
        return getDebugStatus().equals("[STATUS]: ");
    }

}

