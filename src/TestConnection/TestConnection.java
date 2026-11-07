import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) {
        Connection conn = DatabaseConnection.getConnection();
        
        if (conn != null) {
            String sql = "INSERT INTO accounts (account_code, account_name, balance) VALUES (?, ?, ?)";
            
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, "101");
                pstmt.setString(2, "Kas Utama");
                pstmt.setDouble(3, 5000000.00);
                
                int rowsInserted = pstmt.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Data akun berhasil disimpan ke database XAMPP!");
                }
            } catch (SQLException e) {
                System.out.println("Gagal menyimpan data: " + e.getMessage());
            }
        }
    }
}