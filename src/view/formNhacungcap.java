package view;

import DAO.DAO_PhieuXuat;
import dao.DAO_NHACUNGCAP;
import Model.NhaCungCap;
import Model.PhieuXuat;
import static com.microsoft.sqlserver.jdbc.StringUtils.isNumeric;
import static dao.DAO_NHACUNGCAP.getAllNhaCungCap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class formNhacungcap extends javax.swing.JFrame {

    private DefaultTableModel tbl_Model;

    public formNhacungcap() {
        initComponents();
        setLocationRelativeTo(null);
        tbl_Model = (DefaultTableModel) tbl_NhaCungCap.getModel();
        fillToTableNCC();
    }

    private void fillToTableNCC() {
        tbl_Model.setRowCount(0); // Xóa dữ liệu cũ
        List<NhaCungCap> list_NhaCungCap = DAO_NHACUNGCAP.getAllNhaCungCap();
        for (NhaCungCap ncc : list_NhaCungCap) {
            tbl_Model.addRow(new Object[]{ncc.getMaNhaCungCap(), ncc.getTenNhaCungCap(), ncc.getSoDienThoai(), ncc.getEmail(), ncc.getDiaChi()});
        }
    }

    private void addNhaCungCap() {
        if (txt_MaNhaCungCap.getText().isEmpty() || txt_TenNhaCungCap.getText().isEmpty()
                || txt_SoDienThoai.getText().isEmpty() || txt_Email.getText().isEmpty() || txt_DiaChi.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        try {

            NhaCungCap ncc = new NhaCungCap();
            ncc.setMaNhaCungCap(txt_MaNhaCungCap.getText());
            ncc.setTenNhaCungCap(txt_TenNhaCungCap.getText());
            ncc.setSoDienThoai(txt_SoDienThoai.getText());
            ncc.setEmail(txt_Email.getText());
            ncc.setDiaChi(txt_DiaChi.getText());

            boolean success = DAO_NHACUNGCAP.insertNhaCungCap(ncc);
            if (success) {
                JOptionPane.showMessageDialog(this, "Thêm nhà cung cấp thành công!");
                fillToTableNCC(); // Cập nhật bảng
            } else {
                JOptionPane.showMessageDialog(this, "Thêm nhà cung cấp thất bại!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }

    private void timNhaCungCap() {
        // Lấy mã nhà cung cấp từ ô tìm kiếm
        String searchKeyword = txt_TimKiem.getText().trim(); // Giả sử bạn có một JTextField với tên là txtSearch

        // Kiểm tra nếu ô tìm kiếm trống
        if (searchKeyword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã nhà cung cấp cần tìm.");
            return;
        }

        String maNhaCungCap = String.valueOf(txt_TimKiem.getText().trim());
        if (maNhaCungCap.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã phiếu xuất để xóa!");
            return;
        }
        NhaCungCap ncc = DAO_NHACUNGCAP.searchWithMaNhaCungCap(maNhaCungCap);
        if (ncc == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy phiếu xuất với mã: " + maNhaCungCap);
            // Xóa sạch các trường nhập liệu và bảng nếu không tìm thấy
            txt_MaNhaCungCap.setText("");
            txt_TenNhaCungCap.setText("");
            txt_SoDienThoai.setText("");
            txt_Email.setText("");
            txt_DiaChi.setText("");
            tbl_Model.setRowCount(0); // Xóa sạch bảng
        } else {
            // Hiển thị thông tin lên các trường nhập liệu
            txt_MaNhaCungCap.setText(ncc.getMaNhaCungCap());
            txt_TenNhaCungCap.setText(ncc.getTenNhaCungCap());
            txt_SoDienThoai.setText(ncc.getSoDienThoai());
            txt_Email.setText(ncc.getEmail());
            txt_DiaChi.setText(ncc.getDiaChi());
            // Cập nhật bảng chỉ với kết quả tìm kiếm
            tbl_Model.setRowCount(0); // Xóa dữ liệu cũ trong bảng
            tbl_Model.addRow(new Object[]{ncc.getMaNhaCungCap(), ncc.getTenNhaCungCap(), ncc.getSoDienThoai(), ncc.getEmail(), ncc.getDiaChi()});
        }

    }

    private void updateNhaCungCap() {
        if (txt_MaNhaCungCap.getText().isEmpty() || txt_TenNhaCungCap.getText().isEmpty()
                || txt_SoDienThoai.getText().isEmpty() || txt_Email.getText().isEmpty() || txt_DiaChi.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        try {
            NhaCungCap ncc = new NhaCungCap();
            ncc.setMaNhaCungCap(txt_MaNhaCungCap.getText());
            ncc.setTenNhaCungCap(txt_TenNhaCungCap.getText());
            ncc.setSoDienThoai(txt_SoDienThoai.getText());
            ncc.setEmail(txt_Email.getText());
            ncc.setDiaChi(txt_DiaChi.getText());

            DAO_NHACUNGCAP.updateNhaCungCap(ncc);
            JOptionPane.showMessageDialog(this, "Cập nhật nhà cung cấp thành công!");
            fillToTableNCC(); // Cập nhật bảng
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lỗi định dạng số: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }

    private void deleteNhaCungCap() {
        if (txt_MaNhaCungCap.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã nhà cung cấp để xóa!");
            return;
        }

        try {
            String maNhaCungCap = txt_MaNhaCungCap.getText();

            // Kiểm tra xem nhà cung cấp có đang được tham chiếu không
            if (DAO_NHACUNGCAP.isNhaCungCapReferenced(maNhaCungCap)) {
                JOptionPane.showMessageDialog(this, "Không thể xóa nhà cung cấp vì đang được sử dụng trong Phiếu Nhập!");
                return;
            }

            boolean success = DAO_NHACUNGCAP.deleteNhaCungCap(maNhaCungCap);
            if (success) {
                JOptionPane.showMessageDialog(this, "Xóa nhà cung cấp thành công!");
                fillToTableNCC(); // Cập nhật bảng
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy mã nhà cung cấp để xóa.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Mã nhà cung cấp phải là số hợp lệ!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }

    public void clickHereNCC() {
        int row = tbl_NhaCungCap.getSelectedRow(); // Lấy chỉ số dòng được chọn

        // Kiểm tra xem có dòng nào được chọn không
        if (row != -1) {
            try {
                // Lấy dữ liệu từ bảng và chuyển đổi kiểu dữ liệu tương ứng
                String mancc = tbl_NhaCungCap.getValueAt(row, 0).toString(); // Lấy mã vật tư (int)
                String tenncc = tbl_NhaCungCap.getValueAt(row, 1).toString(); // Lấy tên vật tư (String)
                String sdt = tbl_NhaCungCap.getValueAt(row, 2).toString(); // Lấy đơn vị tính (String)
                String email = tbl_NhaCungCap.getValueAt(row, 3).toString();
                String diachi = tbl_NhaCungCap.getValueAt(row, 4).toString();

                // Cập nhật các trường nhập liệu
                txt_MaNhaCungCap.setText(mancc); // Hiển thị mã vật tư dưới dạng chuỗi
                txt_TenNhaCungCap.setText(tenncc);
                txt_SoDienThoai.setText(sdt);
                txt_Email.setText(email);
                txt_DiaChi.setText(diachi);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi chuyển đổi dữ liệu: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng trong bảng!");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btn_Add = new javax.swing.JButton();
        btn_Update = new javax.swing.JButton();
        btn_Delete = new javax.swing.JButton();
        txt_SoDienThoai = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_TenNhaCungCap = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_MaNhaCungCap = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_NhaCungCap = new javax.swing.JTable();
        txt_TimKiem = new javax.swing.JTextField();
        btn_Tim = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_Email = new javax.swing.JTextField();
        txt_DiaChi = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        btn_Add.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_Add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon_add.png"))); // NOI18N
        btn_Add.setText("Thêm");
        btn_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AddActionPerformed(evt);
            }
        });

        btn_Update.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_Update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon_fix.png"))); // NOI18N
        btn_Update.setText("Sửa");
        btn_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UpdateActionPerformed(evt);
            }
        });

        btn_Delete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_Delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon_delete.png"))); // NOI18N
        btn_Delete.setText("Xóa");
        btn_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DeleteActionPerformed(evt);
            }
        });

        txt_SoDienThoai.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Số Điện Thoại :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Tên Nhà Cung Cấp :");

        txt_TenNhaCungCap.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Mã Nhà Cung Cấp :");

        txt_MaNhaCungCap.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Tìm Kiếm:");

        tbl_NhaCungCap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_NhaCungCap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "MaNhaCungCap", "TenNhaCungCap", "SoDienThoai", "Email", "DiaChi"
            }
        ));
        tbl_NhaCungCap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_NhaCungCapMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_NhaCungCap);

        txt_TimKiem.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_Tim.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_Tim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon_restart.png"))); // NOI18N
        btn_Tim.setText("Tìm");
        btn_Tim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Source Sans Pro Black", 0, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nhà Cung Cấp");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Email :");

        txt_Email.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_DiaChi.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Địa Chỉ :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_TenNhaCungCap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_SoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_DiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(btn_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(btn_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_MaNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_TimKiem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_Tim, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(133, 133, 133)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_MaNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(txt_TenNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_SoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_DiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btn_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(btn_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Tim, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)))
                .addGap(114, 114, 114))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DeleteActionPerformed
        // TODO add your handling code here:
        deleteNhaCungCap();
    }//GEN-LAST:event_btn_DeleteActionPerformed

    private void btn_TimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimActionPerformed
        // TODO add your handling code here:
        timNhaCungCap();
    }//GEN-LAST:event_btn_TimActionPerformed

    private void btn_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AddActionPerformed
        // TODO add your handling code here:
        addNhaCungCap();
    }//GEN-LAST:event_btn_AddActionPerformed

    private void btn_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpdateActionPerformed
        // TODO add your handling code here:
        updateNhaCungCap();
    }//GEN-LAST:event_btn_UpdateActionPerformed

    private void tbl_NhaCungCapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_NhaCungCapMouseClicked
        // TODO add your handling code here:
        clickHereNCC();
    }//GEN-LAST:event_tbl_NhaCungCapMouseClicked

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
            java.util.logging.Logger.getLogger(formNhacungcap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formNhacungcap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formNhacungcap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formNhacungcap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formNhacungcap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Add;
    private javax.swing.JButton btn_Delete;
    private javax.swing.JButton btn_Tim;
    private javax.swing.JButton btn_Update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_NhaCungCap;
    private javax.swing.JTextField txt_DiaChi;
    private javax.swing.JTextField txt_Email;
    private javax.swing.JTextField txt_MaNhaCungCap;
    private javax.swing.JTextField txt_SoDienThoai;
    private javax.swing.JTextField txt_TenNhaCungCap;
    private javax.swing.JTextField txt_TimKiem;
    // End of variables declaration//GEN-END:variables
}
