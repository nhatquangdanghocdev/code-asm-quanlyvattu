/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static DAO.DAO_PhieuXuat.getConnection;
import dao.DAO_PHIEUNHAP;
import static dao.DAO_VATTU.getConnection;
import Model.NhaCungCap;
import Model.PhieuXuat;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class DAO_NHACUNGCAP {

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

    public static boolean insertNhaCungCap(NhaCungCap ncc) {
        String sql = "INSERT INTO NhaCungCap (MaNhaCungCap, TenNhaCungCap, SoDienThoai, Email, DiaChi) VALUES (?, ?, ?, ? ,?)";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ncc.getMaNhaCungCap());
            ps.setString(2, ncc.getTenNhaCungCap());
            ps.setString(3, ncc.getSoDienThoai());
            ps.setString(4, ncc.getEmail());
            ps.setString(5, ncc.getDiaChi());

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Thêm nhà cung cấp thành công!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Thêm nhà cung cấp thất bại, vui lòng kiểm tra lại thông tin!");
//            System.err.println("Lỗi khi thêm nhà cung cấp " + e.getMessage());
//            e.printStackTrace();
        }
        return false;
    }

    public static void updateNhaCungCap(NhaCungCap ncc) {
        String sql = "UPDATE NhaCungCap SET TenNhaCungCap = ?, SoDienThoai = ?, Email = ?, DiaChi = ? WHERE MaNhaCungCap = ?";

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, ncc.getTenNhaCungCap());
            pstmt.setString(2, ncc.getSoDienThoai());
            pstmt.setString(3, ncc.getEmail());
            pstmt.setString(4, ncc.getDiaChi());
            pstmt.setString(5, ncc.getMaNhaCungCap());

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cập nhật nhà cung cấp thành công!");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật nhà cung cấp: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static boolean deleteNhaCungCap(String maNhaCungCap) {
        String sql = "DELETE FROM NhaCungCap WHERE MaNhaCungCap = ?";

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, maNhaCungCap);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Xóa nhà cung câp thành công!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa nhà cung cấp: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isNhaCungCapReferenced(String maNhaCungCap) {
        String sql = "SELECT COUNT(*) FROM PhieuNhapKho WHERE MaNhaCungCap = ?";
        try (Connection conn = DAO_NHACUNGCAP.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maNhaCungCap);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Nếu COUNT > 0, nghĩa là đang được tham chiếu
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<NhaCungCap> getAllNhaCungCap() {
        List<NhaCungCap> list_NhaCungCap = new ArrayList<>();
        String sql = "SELECT * FROM NhaCungCap";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                NhaCungCap ncc = new NhaCungCap(
                        rs.getString("MaNhaCungCap"),
                        rs.getString("TenNhaCungCap"),
                        rs.getString("Email"),
                        rs.getString("SoDienThoai"),
                        rs.getString("DiaChi")
                );
                list_NhaCungCap.add(ncc);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách nhà cung cấp: " + e.getMessage());
            e.printStackTrace();
        }
        return list_NhaCungCap;
    }
    
        public static NhaCungCap searchWithMaNhaCungCap(String maNhaCungCap) {
        String sql = "SELECT MaNhaCungCap, TenNhaCungCap, SoDienThoai, Email, DiaChi FROM NhaCungCap WHERE MaNhaCungCap = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maNhaCungCap);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new NhaCungCap(
                rs.getString("MaNhaCungCap"),
                rs.getString("TenNhaCungCap"),
                rs.getString("SoDienThoai"),
                rs.getString("Email"),
                rs.getString("DiaChi")
                );
            }
        } catch (SQLException ex) {
            System.err.println("Lỗi khi lấy danh sách : " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }
    
}
