package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.Kho;

/**
 *
 * @author HP
 */
public class DAO_KHO {

    private static final String JDBC_URL = "jdbc:sqlserver://localhost:1433;databaseName=QLVT;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "1";
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
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }

    public static void insertKho(Kho kh) {
        String sql = "INSERT INTO Kho (MaKho,MaVatTu, SoLuongNhap, SoLuongTon, NgayNhap) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, kh.getMaKho());
            pstmt.setString(2, kh.getMaVatTu());
            pstmt.setInt(3, kh.getSoLuongNhap());
            pstmt.setInt(4, kh.getSoLuongTon());
            pstmt.setDate(5, kh.getNgayNhap()); // java.sql.Date
            // pstmt.setDate(5, new java.sql.Date(kh.getNgayNhap().getTime()));
            pstmt.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Thêm vật tư vào kho thành công!");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm vật tư vào kho: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Cập nhật vật tư (khong dc update ma nen de ma o cuoi )
    public static void updateKho(Kho kho) throws SQLException {
        String sql = "UPDATE Kho SET MaVatTu = ?, SoLuongNhap = ?, SoLuongTon = ?, NgayNhap = ? WHERE MaKho = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kho.getMaVatTu());
            ps.setInt(2, kho.getSoLuongNhap());
            ps.setInt(3, kho.getSoLuongTon());
            ps.setDate(4, kho.getNgayNhap()); // java.sql.Date
            ps.setString(5, kho.getMaKho());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Không tìm thấy vật tư với mã kho: " + kho.getMaKho());
            }
        }
    }

    // Xóa vật tư
    public static boolean deleteKho(String maKho) {
        String sql = "DELETE FROM Kho WHERE MaKho = ?";

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, maKho);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Xóa vật tư trong kho thành công!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa vật tư trong kho: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isKhoReferenced(String maKho) {
        String sql = "SELECT COUNT(*) FROM PhieuXuat WHERE MaKho = ?";
        try (Connection conn = DAO_PHIEUNHAP.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maKho);
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
    public static List<Kho> getAllKho() {
        List<Kho> list = new ArrayList<>();
        String sql = "SELECT MaKho, MaVatTu, SoLuongNhap, SoLuongTon, NgayNhap FROM Kho";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Kho kh = new Kho();
                kh.setMaKho(rs.getString("MaKho")); // Lấy mã kho
                kh.setMaVatTu(rs.getString("MaVatTu")); // Lấy mã vật tư
                kh.setSoLuongNhap(rs.getInt("SoLuongNhap")); // Lấy số lượng nhập
                kh.setSoLuongTon(rs.getInt("SoLuongTon")); // Lấy số lượng tồn
                kh.setNgayNhap(rs.getDate("NgayNhap")); // Lấy ngày nhập (dùng java.sql.Date)

                list.add(kh); // Thêm đối tượng vào danh sách
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách vật tư trong kho: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }
    
    // Phương thức tìm kho theo mã
    public static Kho searchKhoByMa(String maKho) {
        String query = "SELECT * FROM Kho WHERE MaKho = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, maKho);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Kho(
                    rs.getString("maKho"),
                    rs.getString("maVatTu"),
                    rs.getInt("soLuongNhap"),
                    rs.getInt("soLuongTon"),
                    rs.getDate("ngayNhap")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Không tìm thấy
    }

}
