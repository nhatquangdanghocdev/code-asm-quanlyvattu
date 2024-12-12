
package dao;

import static dao.DAO_CTPHIEUNHAP.getConnection;
import Model.ChiTietPhieuNhapKho;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asus
 */
public class DAO_CTPHIEUNHAP {
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
    public static List<ChiTietPhieuNhapKho> getAllCtphieunhap() {
        List<ChiTietPhieuNhapKho> cTPhieuNhapList = new ArrayList<>();
        String sql = "SELECT * FROM ChiTietPhieuNhapKho";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ChiTietPhieuNhapKho ctpnk = new ChiTietPhieuNhapKho(
                    rs.getString("MaChiTietPhieuNhapKho"),
                    rs.getString("MaPhieuNhapKho"),
                    rs.getString("MaVatTu"),
                    rs.getInt("SoLuong")
                );
                cTPhieuNhapList.add(ctpnk);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách vật tư: " + e.getMessage());
            e.printStackTrace();
        }
        return cTPhieuNhapList;
    }
}
