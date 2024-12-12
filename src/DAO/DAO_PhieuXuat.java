package DAO;

import java.sql.*;
import Model.PhieuXuat;
import java.util.*;

public class DAO_PhieuXuat {

    private static final String url = "jdbc:sqlserver://localhost:1433;databaseName=QLVT;trustServerCertificate=true";
    private static final String username = "sa";
    private static final String password = "1";
    private static int rowsInserted;

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
        return DriverManager.getConnection(url, username, password);
    }

    public static boolean insertIntoPhieuXuat(PhieuXuat px) {
        String sql = "INSERT INTO PhieuXuat (MaPhieuXuat, MaPhongBan, MaKho, NgayXuat) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, px.getMaPhieuXuat());
            ps.setString(2, px.getMaPhongBan());
            ps.setString(3, px.getMaKho());
            ps.setString(4, px.getNgayXuat());

            rowsInserted = ps.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Thêm thành công!");
                return true;
            }
        } catch (SQLException e) {
//            System.err.println("Lỗi: " + e.getMessage());
//            e.printStackTrace();
            System.out.println("Thêm thất bại!");
        }
        return false;
    }

    // Cập nhật vật tư (khong dc update ma nen de ma o cuoi )
    public static void updatePhieuXuat(PhieuXuat px) throws SQLException {
        String sql = "UPDATE PhieuXuat SET MaPhongBan = ?, MaKho = ?, NgayXuat = ? WHERE MaPhieuXuat = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, px.getMaPhongBan());
            ps.setString(2, px.getMaKho());
            ps.setString(3, px.getNgayXuat());

            ps.setString(4, px.getMaPhieuXuat());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Không tìm thấy mã phiếu xuất: " + px.getMaPhieuXuat());
            }
        }
    }

    // Xóa vật tư
    public static boolean deletePhieuXuat(String maPhieuXuat) {
        String sql = "DELETE FROM PhieuXuat WHERE MaPhieuXuat = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maPhieuXuat);
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Xóa thành công!");
                return true;
            }
        } catch (SQLException e) {
//            System.err.println("Lỗi: " + e.getMessage());
//            e.printStackTrace();
        }
        return false;
    }

    // Lấy danh sách vật tư
    public static List<PhieuXuat> getAllPhieuXuat() {
        List<PhieuXuat> list_PhieuXuat = new ArrayList<>();
        String sql = "SELECT MaPhieuXuat, MaPhongBan, MaKho, NgayXuat FROM PhieuXuat";

        try (Connection conn = getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                PhieuXuat px = new PhieuXuat();
                px.setMaPhieuXuat(rs.getString("MaPhieuXuat")); // Lấy mã phiếu xuất
                px.setMaPhongBan(rs.getString("MaPhongBan")); // Lấy mã phòng ban
                px.setMaKho(rs.getString("MaKho")); // Lấy mã kho
                px.setNgayXuat(rs.getString("NgayXuat")); // Lấy ngày xuất

                list_PhieuXuat.add(px); // Thêm đối tượng vào danh sách
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách : " + e.getMessage());
            e.printStackTrace();
        }
        return list_PhieuXuat;
    }

    public static PhieuXuat searchWithMaPhieuXuat(String maPhieuXuat) {
        String sql = "SELECT MaPhieuXuat, MaPhongBan, MaKho, NgayXuat FROM PhieuXuat WHERE MaPhieuXuat = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maPhieuXuat);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new PhieuXuat(
                rs.getString("MaPhieuXuat"),
                rs.getString("MaPhongBan"),
                rs.getString("MaKho"),
                rs.getString("NgayXuat")
                );
            }
        } catch (SQLException ex) {
            System.err.println("Lỗi khi lấy danh sách : " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }
}


