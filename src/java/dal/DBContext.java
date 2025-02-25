/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sonnt-local
 */
public abstract class DBContext<T> {

    protected Connection connection;

    public DBContext() {
        try {
            String user = "sa";
            String pass = "sa";
            String url = "jdbc:sqlserver://localhost\\MSSQLSERVER:1433;databaseName=IS1903;trustServerCertificate=true; [sa on SA]";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public abstract ArrayList<T> list();

    public abstract T get(int id);

    public abstract void insert(T model);

    public abstract void update(T model);

    public abstract void delete(T model);

    public class DBConnectionTest {

        public static void main(String[] args) {
            DBContext<Object> testConnection = new DBContext<Object>() {
                public java.util.ArrayList<Object> list() {
                    return null; // Not needed for connection test
                }

                public Object get(int id) {
                    return null; // Not needed for connection test
                }

                public void insert(Object model) {
                    // Not needed for connection test
                }

                public void update(Object model) {
                    // Not needed for connection test
                }

                public void delete(Object model) {
                    // Not needed for connection test
                }
            };

            // If no exception is thrown, the connection was likely successful
            if (testConnection.connection != null) {
                System.out.println("Database connection successful!");
            } else {
                System.out.println("Database connection failed!");
            }

            // Optionally, close the connection (though it's not strictly necessary in this case)
            try {
                if (testConnection.connection != null && !testConnection.connection.isClosed()) {
                    testConnection.connection.close();
                }
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
