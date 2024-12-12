package dao;

import dao.DAO_PHIEUNHAP;
import java.sql.*;
import Model.PhongBan;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.interfaces.PBEKey;

/**
 *
 * @author HP
 */
public class DAO_PHONGBAN {

    // Thông tin kết nối SQL Server
    private static final String JDBC_URL = "jdbc:sqlserver://localhost:1433;databaseName=QLVT;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "1";

    static {
        try {
            // Đăng ký driver SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Không thể tải driver SQL Server", e);
        }
    }

    // Phương thức tạo kết nối
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }

    // Thêm vật tư (có mã)
    public static void insertPhg(PhongBan pb) {
        String sql = "INSERT INTO PhongBan (MaPhongBan, TenPhongBan, DiaChi, SoDienThoai,Email) VALUES (?,?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, pb.getMaPB());
            pstmt.setString(2, pb.getTenPB());
            pstmt.setString(3, pb.getDiachi());
            pstmt.setString(4, pb.getSoDienThoai());
            pstmt.setString(5, pb.getEmail());

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Thêm Phong Ban thành công!");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm phong ban: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Cập nhật Phong Ban (khong dc update ma nen de ma o cuoi )
    public static void updatePhg(PhongBan pb) {
        String sql = "UPDATE PhongBan SET  TenPhongBan = ? , Diachi = ? , SoDienThoai = ? , Email = ? where MaPhongBan=?";

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, pb.getTenPB());  // Dat Ten Phong Ban
            pstmt.setString(2, pb.getDiachi()); // Dat dia chi 
            pstmt.setString(3, pb.getSoDienThoai());   // Dat so dien thoai 
            pstmt.setString(4, pb.getEmail());    // Dat email 
            pstmt.setString(5, pb.getMaPB()); // Dat Ma Phong ban 

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cập nhật Phong ban thành công!");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật Phong Ban : " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Xóa vật tư
    public static boolean deletePhog(String MaPB) {
        String sql = "DELETE FROM PhongBan WHERE MaPhongBan = ?";

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, MaPB);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Xóa Phong Ban thành công!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa Phong Ban: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isPhongBanReferenced(String maPhongBan) {
        String sql = "SELECT COUNT(*) FROM PhieuXuat WHERE MaPhongBan = ?";
        try (Connection conn = DAO_PHIEUNHAP.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maPhongBan);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Nếu COUNT > 0, nghĩa là đang được tham chiếu
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Lấy danh sách vật tư
    public static List<PhongBan> getAllPhongban() {
        List<PhongBan> PhogList = new ArrayList<>();
        String sql = "SELECT MaPhongBan, TenPhongBan, DiaChi, SoDienThoai,Email FROM PhongBan";

        try (Connection conn = getConnection(); java.sql.Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                PhongBan pb = new PhongBan(
                        rs.getString("MaPhongBan"),
                        rs.getString("TenPhongBan"),
                        rs.getString("DiaChi"),
                        rs.getString("SoDienThoai"),
                        rs.getString("Email")
                );
                PhogList.add(pb);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách Phong Ban: " + e.getMessage());
            e.printStackTrace();
        }
        return PhogList;
    }

    // Phương thức tìm phòng ban theo mã
    public static PhongBan searchPhongBanByMa(String maPhongBan) {
        String query = "SELECT * FROM PhongBan WHERE MaPhongBan = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, maPhongBan);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new PhongBan(
                    rs.getString("MaPhongBan"),
                    rs.getString("TenPhongBan"),
                    rs.getString("DiaChi"),
                    rs.getString("SoDienThoai"),
                    rs.getString("Email")
            
         );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Không tìm thấy
    }

}
