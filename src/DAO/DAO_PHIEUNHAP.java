package dao;

import Model.PhieuNhap;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAO_PHIEUNHAP {

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

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }

    // Thêm vật tư (có mã)
    public static void insertPhieuNhap(PhieuNhap pn) {
        String sql = "INSERT INTO PhieuNhapKho (MaPhieuNhapKho, NgayNhap, MaNhaCungCap) VALUES (?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, pn.getMaPhieuNhapKho());
            pstmt.setString(2, pn.getNgayNhap());
            pstmt.setString(3, pn.getMaNhaCungCap());

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Thêm phiếu nhập thành công!");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm phiếu nhập: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void updatePhieuNhap(PhieuNhap pn) {
        String sql = "UPDATE PhieuNhapKho SET NgayNhap = ?, MaNhaCungCap = ? WHERE MaPhieuNhapKho = ?";

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, pn.getNgayNhap());
            pstmt.setString(2, pn.getMaNhaCungCap());
            pstmt.setString(3, pn.getMaPhieuNhapKho());

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cập nhật phiếu nhập thành công!");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật phiếu nhập: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static boolean deletePhieuNhap(String maPhieuNhap) {
        String sql = "DELETE FROM PhieuNhapKho WHERE MaPhieuNhapKho = ?";

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, maPhieuNhap);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Xóa phiếu nhập thành công!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa phiếu nhập: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public static List<PhieuNhap> getAllPhieuNhap() {
        List<PhieuNhap> phieuNhapList = new ArrayList<>();
        String sql = "SELECT * FROM PhieuNhapKho";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                PhieuNhap pn = new PhieuNhap(
                        rs.getString("MaPhieuNhapKho"),
                        rs.getString("NgayNhap"),
                        rs.getString("MaNhaCungCap")
                );
                phieuNhapList.add(pn);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách phiếu nhập: " + e.getMessage());
            e.printStackTrace();
        }
        return phieuNhapList;
    }

    public static PhieuNhap searchWithMaPhieuNhap(String maPhieuXuat) {
        String query = "SELECT * FROM PhieuNhapKho WHERE MaPhieuNhapKho = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, maPhieuXuat);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new PhieuNhap(
                        rs.getString("MaPhieuNhapKho"),
                        rs.getString("NgayNhap"),
                        rs.getString("MaNhaCungCap")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Không tìm thấy
    }
}
