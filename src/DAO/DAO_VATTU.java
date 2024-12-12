package dao;

import Model.VatTu;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO_VATTU {

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
    public static void insertVatTu(VatTu vt) {
        String sql = "INSERT INTO VatTu (MaVatTu, TenVatTu, DonViTinh, DonGia) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, vt.getMavattu());
            pstmt.setString(2, vt.getTenVattu());
            pstmt.setString(3, vt.getDonvitinh());
            pstmt.setDouble(4, vt.getDongia());

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Thêm vật tư thành công!");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm vật tư: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Cập nhật vật tư (khong dc update ma nen de ma o cuoi )
    public static void updateVatTu(VatTu vt) {
        String sql = "UPDATE VatTu SET TenVatTu = ?, DonViTinh = ?, DonGia = ? WHERE MaVatTu = ?";

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, vt.getTenVattu());  // Đặt TenVatTu
            pstmt.setString(2, vt.getDonvitinh()); // Đặt DonViTinh
            pstmt.setDouble(3, vt.getDongia());    // Đặt DonGia
            pstmt.setString(4, vt.getMavattu());      // Đặt MaVatTu

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cập nhật vật tư thành công!");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật vật tư: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Xóa vật tư
    public static boolean deleteVatTu(String maVatTu) {
        String sql = "DELETE FROM VatTu WHERE MaVatTu = ?";

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, maVatTu);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Xóa vật tư thành công!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa vật tư: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // Lấy danh sách vật tư
    public static List<VatTu> getAllVatTu() {
        List<VatTu> vatTuList = new ArrayList<>();
        String sql = "SELECT MaVatTu, TenVatTu, DonViTinh, DonGia FROM VatTu";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                VatTu vt = new VatTu(
                        rs.getString("MaVatTu"),
                        rs.getString("TenVatTu"),
                        rs.getString("DonViTinh"),
                        rs.getDouble("DonGia")
                );
                vatTuList.add(vt);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách vật tư: " + e.getMessage());
            e.printStackTrace();
        }
        return vatTuList;
    }

    public static VatTu searchVatTuByMa(String maVatTu) {
        String query = "SELECT * FROM VatTu WHERE MaVatTu = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, maVatTu);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new VatTu(
                        rs.getString("MaVatTu"),
                        rs.getString("TenVatTu"),
                        rs.getString("DonViTinh"),
                        rs.getDouble("DonGia")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy
    }

}
