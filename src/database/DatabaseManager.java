package database;
import java.sql.*;
import java.util.Collection;

import entity.Balance;
import entity.account.AbsMultipleCurrencyAccount;
import entity.account.AbsSingleCurrencyAccount;

/**
 * in charge of database direct, connect
 */
public class DatabaseManager {

    private static DatabaseManager instance   = null;
    private        Connection      connection;
    private final  String          dbName     = "test.db";
    private DatabaseManager()
    {
        connect(dbName);
    }

    public static DatabaseManager getInstance()
    {
        if(instance == null)
        {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public void finalize()
    {
        try {
            if (this.getConnection() != null) {
                this.getConnection().close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Connect to a sample database
     */
    private void connect(String fileName) {
        try {
            // db parameters
            String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "/" +fileName;
            System.out.println("db URL : " + url);
            // create a connection to the database
            this.setConnection(DriverManager.getConnection(url));

            System.out.println("Connection to SQLite has been established. database name : " + fileName);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection()
    {
        try
        {
            if(connection.isClosed())
            {
                this.connect(this.dbName);
            }
        } catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public void setConnection(Connection connection)
    {
        this.connection = connection;
    }
}
