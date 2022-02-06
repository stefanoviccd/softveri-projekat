/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.biblioteka.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import rs.ac.bg.fon.ps.biblioteka.constants.ServerConstants;

/**
 *
 * @author Cartman
 */
public class DbConnectionFactory {

    private Connection connection;
    private static DbConnectionFactory instance;

    private DbConnectionFactory() {

    }

    public static DbConnectionFactory getInstance() {
        if (instance == null) {
            instance = new DbConnectionFactory();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException, FileNotFoundException, IOException {

        if (connection == null || connection.isClosed()) {
            try {
                Properties properties = new Properties();
                properties.load(new FileInputStream(ServerConstants.DB_CONFIG_FILE_PATH));
                String url = properties.getProperty(ServerConstants.DB_CONFIG_URL);
                String user = properties.getProperty(ServerConstants.DB_CONFIG_USERNAME);
                String password = properties.getProperty(ServerConstants.DB_CONFIG_PASSWORD);
                connection = DriverManager.getConnection(url, user, password);
                connection.setAutoCommit(false);
                connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            } catch (SQLException ex) {
                System.out.println("Neuspesno uspostavljanje konekcije!\n" + ex.getMessage());
                throw ex;
            }
        }
        return connection;
    }
}
