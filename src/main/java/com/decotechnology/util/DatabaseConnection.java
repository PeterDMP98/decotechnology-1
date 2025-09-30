package com.decotechnology.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {


    // Datos para conectarnos a MySQL
    private static final String URL = "jdbc:mysql://localhost:3306/decotechnology_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "";
    private static final String PASSWORD = "";

    // Método para obtener la conexión
public static Connection getConnection() {
    Connection connection = null;
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("✅ Conexión exitosa a la base de datos");
    } catch (ClassNotFoundException e) {
        System.out.println("❌ Driver MySQL no encontrado");
        e.printStackTrace();
    } catch (SQLException e) {
        System.out.println("❌ Error al conectar a la BD");
        System.out.println("URL: " + URL + " | Usuario: " + USER);
        e.printStackTrace();
    }
    return connection;
}


    // Método main para probar la conexión
    public static void main(String[] args) {
        Connection conn = getConnection();
        if (conn != null) {
            System.out.println("Conexión exitosa");
        } else {
            System.out.println("No se pudo conectar a la base de datos");
        }
    }
}
