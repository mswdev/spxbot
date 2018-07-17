package api.web.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SQLDatabaseConnector {

    /**
     * The database connection.
     */
    protected static Connection DATABASE_CONNECTION;

    /**
     * Sets the database connection
     *
     * @param database_connection The database connection to set.
     */
    public static void setConnection(Connection database_connection) {
        DATABASE_CONNECTION = database_connection;
    }

    /**
     * Gets the database connection.
     *
     * @return The database connection.
     **/
    public Connection getConnection() {
        return DATABASE_CONNECTION;
    }

    /**
     * Queries the specified data then gets the specified column data with the specified limit and column index.
     *
     * @param database_table        The database table.
     * @param database_column       The database column.
     * @param database_column_data  The data to search for in the column.
     * @param database_column_index The column index to get.
     * @param query_limit           The limit to select for.
     * @return An object list containing the queried data.
     */
    public static List<Object> selectData(String database_table, String database_column, String database_column_data, int database_column_index, int query_limit) throws SQLException {
        if (DATABASE_CONNECTION == null)
            return null;

        final List<Object> QUERIED_DATA = new ArrayList<>();
        final ResultSet RESULT_SET = SQLDatabaseConnector.select(database_table, database_column, database_column_data, query_limit);
        if (RESULT_SET == null)
            return null;

        while (RESULT_SET.next())
            QUERIED_DATA.add(RESULT_SET.getObject(database_column_index));

        return QUERIED_DATA;
    }

    /**
     * Queries the specified database table for the specified column data. (Protects against SQL Injection)
     *
     * @param database_table       The database table.
     * @param database_column      The database column.
     * @param database_column_data The data to search for in the column.
     * @param query_limit          The limit to select for.
     * @return The ResultSet from the select; null otherwise.
     */
    public static ResultSet select(String database_table, String database_column, String database_column_data, int query_limit) throws SQLException {
        if (DATABASE_CONNECTION == null)
            return null;

        final String QUERY = "SELECT * FROM " + database_table + " WHERE " + database_column + " = ? LIMIT " + query_limit;
        final PreparedStatement PREPARED_STATEMENT = DATABASE_CONNECTION.prepareStatement(QUERY);
        PREPARED_STATEMENT.setString(1, database_column_data);
        return PREPARED_STATEMENT.executeQuery();
    }

    /**
     * Updates the specified database table with the specified string column data. (Protects against SQL Injection)
     *
     * @param database_table       The database table.
     * @param column_to_update     The database column to update.
     * @param value_to_set         The value to set.
     * @param database_column      The database column to target.
     * @param database_column_data The data to search for in the column.
     * @return 1 if the update was successful; -1 otherwise.
     */
    public static boolean updateString(String database_table, String column_to_update, String value_to_set, String database_column, String database_column_data) throws SQLException {
        if (DATABASE_CONNECTION == null)
            return false;

        final String QUERY = "UPDATE " + database_table + " SET " + column_to_update + " = ? WHERE " + database_column + " = ?";
        final PreparedStatement PREPARED_STATEMENT = DATABASE_CONNECTION.prepareStatement(QUERY);
        PREPARED_STATEMENT.setString(1, value_to_set);
        PREPARED_STATEMENT.setString(2, database_column_data);
        return PREPARED_STATEMENT.executeUpdate() == 1;
    }

    /**
     * Updates the specified database table with the specified int column data. (Protects against SQL Injection)
     *
     * @param database_table       The database table.
     * @param column_to_update     The database column to update.
     * @param value_to_set         The value to set.
     * @param database_column      The database column to target.
     * @param database_column_data The data to search for in the column.
     * @return True if the update was successful; false otherwise.
     */
    public static boolean updateInt(String database_table, String column_to_update, int value_to_set, String database_column, String database_column_data) throws SQLException {
        if (DATABASE_CONNECTION == null)
            return false;

        final String QUERY = "UPDATE " + database_table + " SET " + column_to_update + " = " + column_to_update + " + ? WHERE " + database_column + " = ?";
        final PreparedStatement PREPARED_STATEMENT = DATABASE_CONNECTION.prepareStatement(QUERY);
        PREPARED_STATEMENT.setString(1, String.valueOf(value_to_set));
        PREPARED_STATEMENT.setString(2, database_column_data);
        return PREPARED_STATEMENT.executeUpdate() == 1;
    }

    /**
     * Updates the specified database table with the specified date column data. (Protects against SQL Injection)
     *
     * @param database_table       The database table.
     * @param column_to_update     The database column to update.
     * @param date_to_set          The date to set.
     * @param database_column      The database column to target.
     * @param database_column_data The data to search for in the column.
     * @return True if the update was successful; false otherwise.
     */
    public static boolean updateDate(String database_table, String column_to_update, Date date_to_set, String database_column, String database_column_data) throws SQLException {
        if (DATABASE_CONNECTION == null)
            return false;

        final String QUERY = "UPDATE " + database_table + " SET " + column_to_update + " = ? WHERE " + database_column + " = ?";
        final PreparedStatement PREPARED_STATEMENT = DATABASE_CONNECTION.prepareStatement(QUERY);
        PREPARED_STATEMENT.setString(1, String.valueOf(date_to_set));
        PREPARED_STATEMENT.setString(2, database_column_data);
        return PREPARED_STATEMENT.executeUpdate() == 1;
    }

}

