package view;

import DAO.DAO_PhieuXuat;
import DAO.DAO_PhieuXuatChiTiet;
import Model.PhieuXuat;
import Model.PhieuXuatChiTiet;
import Model.VatTu;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class FormPhieuXuatCT extends javax.swing.JFrame {
    
    private DefaultTableModel tbl_Model;
    
    public FormPhieuXuatCT() {
        initComponents();
        setLocationRelativeTo(null);
        tbl_Model = (DefaultTableModel) tbl_PhieuXuatChiTiet.getModel();
        fillToTable();
    }
    
    public void clickHere() {
    int row = tbl_PhieuXuatChiTiet.getSelectedRow(); // Lấy chỉ số dòng được chọn

    // Kiểm tra xem có dòng nào được chọn không
    if (row > -1) {
        try {
            // Lấy dữ liệu từ bảng và chuyển đổi kiểu dữ liệu tương ứng
            String maPhieuXuatChiTiet = String.valueOf(tbl_PhieuXuatChiTiet.getValueAt(row, 0)); // Lấy mã phiếu mua chi tiết (String)
            String maPhieuXuat = String.valueOf(tbl_PhieuXuatChiTiet.getValueAt(row, 1)); // Lấy mã phiếu xuất (String)
            String maVatTu = String.valueOf(tbl_PhieuXuatChiTiet.getValueAt(row, 2)); // Lấy mã phiếu xuất (String)
            String soLuong = String.valueOf(tbl_PhieuXuatChiTiet.getValueAt(row, 3)); // Lấy số lượng (int)

            // Cập nhật các trường nhập liệu
            txt_MaPhieuXuatChiTiet.setText(maPhieuXuatChiTiet); // Hiển thị mã vật tư dưới dạng chuỗi
            txt_MaPhieuXuat.setText(maPhieuXuat);
            txt_MaVatTu.setText(soLuong);
            txt_SoLuong.setText(soLuong);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi chuyển đổi dữ liệu: " + e.getMessage());
        }
    } else {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng trong bảng!");
    }
    }
    
    
private void fillToTable() {
        tbl_Model.setRowCount(0); // Xóa dữ liệu cũ
        List<PhieuXuatChiTiet> list_PhieuXuatChiTiet = DAO_PhieuXuatChiTiet.getAllPhieuXuatChiTiet();
        for (PhieuXuatChiTiet pxct : list_PhieuXuatChiTiet) {
            tbl_Model.addRow(new Object[]{pxct.getMaPhieuXuatChiTiet(), pxct.getMaPhieuXuat(), pxct.getMaVatTu(), pxct.getSoLuong()});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lbl_MaPhieuXuatChiTiet = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_PhieuXuatChiTiet = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        txt_MaPhieuXuatChiTiet = new javax.swing.JTextField();
        txt_MaPhieuXuat = new javax.swing.JTextField();
        txt_MaVatTu = new javax.swing.JTextField();
        txt_SoLuong = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Source Sans Pro Black", 0, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PHIẾU XUẤT CHI TIẾT");

        jButton2.setText("X");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jButton2)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        lbl_MaPhieuXuatChiTiet.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_MaPhieuXuatChiTiet.setText("Mã Phiếu Xuất Chi Tiết :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Mã Vật Tư:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Mã Phiếu Xuất :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Số Lượng:");

        tbl_PhieuXuatChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Chi Tiết Phiếu Xuất", "Mã Phiếu Xuất", "Số Lượng", "Đơn Giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_PhieuXuatChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_PhieuXuatChiTietMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_PhieuXuatChiTiet);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Excel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txt_MaPhieuXuatChiTiet.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_MaPhieuXuat.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_MaVatTu.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_SoLuong.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_MaPhieuXuatChiTiet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_MaPhieuXuat)
                    .addComponent(txt_MaPhieuXuatChiTiet))
                .addGap(43, 43, 43)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_MaVatTu, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_SoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 635, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_MaPhieuXuatChiTiet)
                    .addComponent(jLabel3)
                    .addComponent(txt_MaPhieuXuatChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_MaVatTu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(txt_MaPhieuXuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_SoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        dispose();
    }//GEN-LAST:event_formWindowClosed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tbl_PhieuXuatChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_PhieuXuatChiTietMouseClicked
        clickHere();
    }//GEN-LAST:event_tbl_PhieuXuatChiTietMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       String title = "ChiTietPhieuXuat"; // Tiêu đề sheet trong Excel
        exportToExcel(tbl_PhieuXuatChiTiet, title);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormPhieuXuatCT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPhieuXuatCT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPhieuXuatCT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPhieuXuatCT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormPhieuXuatCT().setVisible(true);
            }
        });
    }
    
    
    public static void exportToExcel(JTable table, String title) {
        if (table == null) {
            JOptionPane.showMessageDialog(null, "Bảng dữ liệu chưa được khởi tạo.");
            return;
        }

        // Mở hộp thoại để chọn nơi lưu file
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home"));
        fileChooser.setDialogTitle("Lưu tệp Excel");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", "xls", "xlsx");
        fileChooser.setFileFilter(filter);

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();

            // Đảm bảo file có đuôi .xlsx
            if (!filePath.toLowerCase().endsWith(".xlsx")) {
                filePath += ".xlsx";
            }
            File file = new File(filePath);

            // Hỏi xác nhận nếu file đã tồn tại
            if (file.exists()) {
                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "File đã tồn tại. Bạn có muốn ghi đè không?",
                        "Xác nhận",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirm != JOptionPane.YES_OPTION) {
                    return;
                }
            }

            // Kiểm tra nếu JTable không có dữ liệu
            if (table.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Không có dữ liệu để xuất.");
                return;
            }

            try (XSSFWorkbook workbook = new XSSFWorkbook()) {
                // Tạo một sheet mới
                XSSFSheet sheet = workbook.createSheet("Dữ liệu");

                // Tạo tiêu đề chính
                Row titleRow = sheet.createRow(0); // Dòng đầu tiên
                Cell titleCell = titleRow.createCell(0); // Ô đầu tiên
                titleCell.setCellValue(title); // Nội dung tiêu đề

                // Định dạng cho tiêu đề chính
                CellStyle titleStyle = workbook.createCellStyle();
                XSSFFont titleFont = workbook.createFont();
                titleFont.setBold(true); // In đậm
                titleFont.setFontHeightInPoints((short) 18); // Cỡ chữ
                titleStyle.setFont(titleFont);
                titleStyle.setAlignment(HorizontalAlignment.CENTER); // Căn giữa
                titleCell.setCellStyle(titleStyle);

                // Gộp các ô để tiêu đề nằm giữa
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, table.getColumnCount() - 1));

                // Tạo tiêu đề cột (header)
                Row headerRow = sheet.createRow(1); // Dòng thứ hai
                CellStyle headerStyle = workbook.createCellStyle();
                XSSFFont headerFont = workbook.createFont();
                headerFont.setBold(true); // In đậm
                headerStyle.setFont(headerFont);

                // Ghi tên các cột
                for (int col = 0; col < table.getColumnCount(); col++) {
                    Cell headerCell = headerRow.createCell(col);
                    headerCell.setCellValue(table.getColumnName(col)); // Lấy tên cột từ JTable
                    headerCell.setCellStyle(headerStyle); // Áp dụng định dạng
                }

                // Ghi dữ liệu từ JTable vào các dòng Excel
                for (int row = 0; row < table.getRowCount(); row++) {
                    Row dataRow = sheet.createRow(row + 2); // Dòng bắt đầu từ thứ 3
                    for (int col = 0; col < table.getColumnCount(); col++) {
                        Cell cell = dataRow.createCell(col);
                        Object value = table.getValueAt(row, col); // Lấy giá trị từ JTable
                        if (value instanceof Number) {
                            cell.setCellValue(((Number) value).doubleValue()); // Nếu là số
                        } else {
                            cell.setCellValue(value != null ? value.toString() : ""); // Nếu là chuỗi
                        }
                    }
                }

                // Tự động điều chỉnh chiều rộng cột theo nội dung
                for (int col = 0; col < table.getColumnCount(); col++) {
                    sheet.autoSizeColumn(col);
                }

                // Lưu file ra đĩa
                try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                    workbook.write(fileOut);
                    JOptionPane.showMessageDialog(null, "Dữ liệu đã được xuất ra file Excel thành công!");
                }

            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi xuất file: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
    
    
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_MaPhieuXuatChiTiet;
    private javax.swing.JTable tbl_PhieuXuatChiTiet;
    private javax.swing.JTextField txt_MaPhieuXuat;
    private javax.swing.JTextField txt_MaPhieuXuatChiTiet;
    private javax.swing.JTextField txt_MaVatTu;
    private javax.swing.JTextField txt_SoLuong;
    // End of variables declaration//GEN-END:variables
}
