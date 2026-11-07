package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Konfigurasi koneksi ke database XAMPP Anda
    private static final String URL = "jdbc:mysql://localhost:3306/accounTrace_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Default XAMPP tanpa password

    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Memuat driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Membuka koneksi
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Koneksi ke database XAMPP berhasil!");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC tidak ditemukan: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Koneksi database gagal: " + e.getMessage());
        }
        return conn;
    }
}