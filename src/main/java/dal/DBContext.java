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
            String url = "jdbc:sqlserver://localhost\\MSSQLSERVER:1433;databaseName=IS1903_Stupet;trustServerCertificate=true;";
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
    public boolean testConnection() {
        try {
            System.out.println((connection != null && !connection.isClosed())
                    ? "Kết nối thành công!"
                    : "Kết nối thất bại!");
            return connection != null && !connection.isClosed();
        } catch (SQLException ex) {
            return false;
        }
    }
    public static void main(String[] args) {
        DBContext<Object> db = new DBContext<>() {
            public ArrayList<Object> list() {
                return null;
            }

            public Object get(int id) {
                return null;
            }

            public void insert(Object model) {
            }

            public void update(Object model) {
            }

            public void delete(Object model) {
            }
        };

        db.testConnection();
    }
}
