
package DAO;

import static DAO.DAO_PhieuXuat.getConnection;
import static DAO.DAO_PhieuXuatChiTiet.getConnection;
import Model.PhieuXuat;
import Model.PhieuXuatChiTiet;
import java.sql.*;
import java.util.*;

public class DAO_PhieuXuatChiTiet {
    private static final String url = "jdbc:sqlserver://localhost:1433;databaseName=QLVT;trustServerCertificate=true";
    private static final String username = "sa";
    private static final String password = "1";

    static {
        try {
            // Đăng ký driver SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Không thể tải driver SQL Server", e);
        }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
    public static List<PhieuXuatChiTiet> getAllPhieuXuatChiTiet() {
        List<PhieuXuatChiTiet> list_PhieuXuatChiTiet = new ArrayList<>();
        String sql = "SELECT * FROM PhieuXuatChiTiet";

        try (Connection conn = getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                PhieuXuatChiTiet pxct = new PhieuXuatChiTiet(
                    rs.getString("MaPhieuXuatChiTiet"),
                    rs.getString("MaPhieuXuat"),
                    rs.getString("MaVatTu"),
                    rs.getInt("SoLuong")
                );
                list_PhieuXuatChiTiet.add(pxct);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
        return list_PhieuXuatChiTiet;
    }
    
}
