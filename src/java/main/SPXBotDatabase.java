package main;


import main.data.Vars;
import main.web.mysql.SQLDatabaseConnector;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Sphiinx on 6/17/2017.
 */
public class SPXBotDatabase extends SQLDatabaseConnector {

    private static final String DATABASE_URL = "jdbc:mysql://138.68.45.96:3306/spxbot";

    private static final String DATABASE_USERNAME = "spxbot";

    private static final String DATABASE_PASSWORD = "-3VP3&Awv#3nJ-YCezH@h2t%wNuhEa#!kRE49@DzCc?J^67L";

    /**
     * Establishes a connection with the SPXBot database.
     *
     * @return True if the connection was established; false otherwise.
     */
    public static boolean establishConnection() {
        try {
            setConnection(DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Gets the user account token.
     *
     * @param user_account_username The username of the account to get the token from.
     * @return The user account token.
     */
    public static String getUserAccountToken(String user_account_username) {
        try {
            String USER_ACCOUNT_TOKEN = String.valueOf(selectData("user_accounts", "username", user_account_username, 4, 1));
            if (USER_ACCOUNT_TOKEN == null)
                return "";

            return USER_ACCOUNT_TOKEN.substring(1, USER_ACCOUNT_TOKEN.length() - 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * Sets the client online status.
     */
    public static void setClientOnlineStatus() {
        try {
            final String CLIENT_ONLINE_STATUS = String.valueOf(selectData("client_information", "name", "spxbot_client", 4, 1));
            Vars.get().IS_CLIENT_ONLINE = Boolean.valueOf(CLIENT_ONLINE_STATUS.substring(1, CLIENT_ONLINE_STATUS.length() - 1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the client version.
     */
    public static void setClientVersion() {
        try {
            final String CLIENT_VERSION = String.valueOf(selectData("client_information", "name", "spxbot_client", 3, 1));
            Vars.get().CLIENT_VERSION = CLIENT_VERSION.substring(1, CLIENT_VERSION.length() - 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Increments the specified user account column value.
     *
     * @param user_account_username The user account username to target.
     * @param column_to_increment   The column to increment.
     */
    public static void incrementUserAccountValue(String user_account_username, String column_to_increment) {
        try {
            SQLDatabaseConnector.updateInt("user_accounts", column_to_increment, 1, "username", user_account_username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the specified user account column value.
     *
     * @param user_account_username The user account username to target.
     * @param column_to_update      The column to update.
     */
    public static void updateUserAccountValue(String user_account_username, String column_to_update, String update_text) {
        try {
            SQLDatabaseConnector.updateString("user_accounts", column_to_update, update_text, "username", user_account_username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the specified user account column date.
     *
     * @param user_account_username The user account username to target.
     * @param column_to_update      The column to update.
     */
    public static void updateUserAccountDate(String user_account_username, String column_to_update) {
        try {
            //final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
            final java.util.Date DATE = new java.util.Date();
            final Date SQL_DATE = new Date(DATE.getTime());
            SQLDatabaseConnector.updateDate("user_accounts", column_to_update, SQL_DATE, "username", user_account_username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

