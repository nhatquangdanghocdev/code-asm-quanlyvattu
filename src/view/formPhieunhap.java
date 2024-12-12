package view;

import dao.DAO_PHIEUNHAP;
import Model.PhieuNhap;
import static com.microsoft.sqlserver.jdbc.StringUtils.isNumeric;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class formPhieunhap extends javax.swing.JFrame {

    private DefaultTableModel tableModel;

    public formPhieunhap() {
        initComponents();
        setLocationRelativeTo(null);
        tableModel = (DefaultTableModel) tbl_phieuNhap.getModel();
        fillToTablePN();
    }

    public void clickHerePN() {
        int row = tbl_phieuNhap.getSelectedRow(); // Lấy chỉ số dòng được chọn

        // Kiểm tra xem có dòng nào được chọn không
        if (row != -1) {
            try {
                // Lấy dữ liệu từ bảng và chuyển đổi kiểu dữ liệu tương ứng
                String maPhieuNhap = tbl_phieuNhap.getValueAt(row, 0).toString(); // Lấy mã vật tư (int)
                String ngayNhap = tbl_phieuNhap.getValueAt(row, 1).toString(); // Lấy tên vật tư (String)
                String maNhaCungCap = tbl_phieuNhap.getValueAt(row, 2).toString(); // Lấy đơn vị tính (String)

                // Cập nhật các trường nhập liệu
                txt_MaPhieuNhap.setText(maPhieuNhap); // Hiển thị mã vật tư dưới dạng chuỗi
                txt_NgayNhapPN.setText(ngayNhap);
                txt_NhaCungCapPN.setText(maNhaCungCap);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi chuyển đổi dữ liệu: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng trong bảng!");
        }
    }

    private void fillToTablePN() {
        tableModel.setRowCount(0); // Xóa dữ liệu cũ
        List<PhieuNhap> phieuNhapList = DAO_PHIEUNHAP.getAllPhieuNhap();
        for (PhieuNhap pn : phieuNhapList) {
            tableModel.addRow(new Object[]{pn.getMaPhieuNhapKho(), pn.getNgayNhap(), pn.getMaNhaCungCap()});
        }
    }

    private void addPhieuNhap() {
        if (txt_MaPhieuNhap.getText().isEmpty() || txt_NgayNhapPN.getText().isEmpty()
                || txt_NhaCungCapPN.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        try {
            PhieuNhap pn = new PhieuNhap(
                    txt_MaPhieuNhap.getText(),
                    txt_NgayNhapPN.getText(),
                    txt_NhaCungCapPN.getText()
            );
            DAO_PHIEUNHAP.insertPhieuNhap(pn);
            JOptionPane.showMessageDialog(this, "Thêm vật tư thành công!");
            fillToTablePN(); // Cập nhật bảng
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }

    private void updatePhieuNhap() {
        if (txt_MaPhieuNhap.getText().isEmpty() || txt_NgayNhapPN.getText().isEmpty()
                || txt_NhaCungCapPN.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        try {
            PhieuNhap pn = new PhieuNhap(
                    txt_MaPhieuNhap.getText(),
                    txt_NgayNhapPN.getText(),
                    txt_NhaCungCapPN.getText()
            );
            DAO_PHIEUNHAP.updatePhieuNhap(pn);
            JOptionPane.showMessageDialog(this, "Cập nhật phiếu nhập thành công!");
            fillToTablePN(); // Cập nhật bảng
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lỗi định dạng số: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }

    private void deletePhieuNhap() {
        if (txt_MaPhieuNhap.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã Phiếu Nhập để xóa!");
            return;
        }

        try {
            String maPhieuNhap = txt_MaPhieuNhap.getText();
            boolean success = DAO_PHIEUNHAP.deletePhieuNhap(maPhieuNhap);
            if (success) {
                JOptionPane.showMessageDialog(this, "Xóa phiếu nhập thành công!");
                fillToTablePN(); // Cập nhật bảng
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy mã phiếu để xóa.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnPhieuNhap2 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btn_ThemNhap = new javax.swing.JButton();
        btn_SuaNhap = new javax.swing.JButton();
        btn_XoaNhap = new javax.swing.JButton();
        txt_NhaCungCapPN = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_NgayNhapPN = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_MaPhieuNhap = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_phieuNhap = new javax.swing.JTable();
        txt_timkiemPN = new javax.swing.JTextField();
        btn_timkiemPN = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        lbl_nhapchitiet = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        pnPhieuNhap2.setPreferredSize(new java.awt.Dimension(960, 600));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        btn_ThemNhap.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_ThemNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon_add.png"))); // NOI18N
        btn_ThemNhap.setText("Thêm");
        btn_ThemNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemNhapActionPerformed(evt);
            }
        });

        btn_SuaNhap.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_SuaNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon_fix.png"))); // NOI18N
        btn_SuaNhap.setText("Sửa");
        btn_SuaNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaNhapActionPerformed(evt);
            }
        });

        btn_XoaNhap.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_XoaNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon_delete.png"))); // NOI18N
        btn_XoaNhap.setText("Xóa");
        btn_XoaNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaNhapActionPerformed(evt);
            }
        });

        txt_NhaCungCapPN.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Mã Nhà Cung Cấp :");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Ngày Nhập :");

        txt_NgayNhapPN.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Mã Phiếu Nhập :");

        txt_MaPhieuNhap.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Tìm Kiếm:");

        tbl_phieuNhap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_phieuNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Ma Phieu Nhap", "Ngay Nhap", "Ma Nha Cung Cap"
            }
        ));
        tbl_phieuNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_phieuNhapMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_phieuNhap);

        txt_timkiemPN.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_timkiemPN.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_timkiemPN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon_restart.png"))); // NOI18N
        btn_timkiemPN.setText("Làm mới");
        btn_timkiemPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timkiemPNActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Source Sans Pro Black", 0, 48)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Phiếu Nhập");

        lbl_nhapchitiet.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lbl_nhapchitiet.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_nhapchitiet.setText("Phiếu Nhập Chi Tiết");
        lbl_nhapchitiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_nhapchitietMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(36, Short.MAX_VALUE)
                        .addComponent(btn_ThemNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(btn_SuaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(btn_XoaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_MaPhieuNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                            .addComponent(txt_NgayNhapPN)
                            .addComponent(txt_NhaCungCapPN))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_timkiemPN)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_timkiemPN, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)))
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_nhapchitiet, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_timkiemPN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_timkiemPN, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_MaPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txt_NgayNhapPN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_NhaCungCapPN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(100, 100, 100)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btn_ThemNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_SuaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(btn_XoaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 40, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(60, 60, 60)
                .addComponent(lbl_nhapchitiet, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout pnPhieuNhap2Layout = new javax.swing.GroupLayout(pnPhieuNhap2);
        pnPhieuNhap2.setLayout(pnPhieuNhap2Layout);
        pnPhieuNhap2Layout.setHorizontalGroup(
            pnPhieuNhap2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnPhieuNhap2Layout.setVerticalGroup(
            pnPhieuNhap2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(pnPhieuNhap2, "card3");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
        deletePhieuNhap();
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_timkiemPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timkiemPNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_timkiemPNActionPerformed

    private void lbl_nhapchitietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_nhapchitietMouseClicked
        FormPhieuNhapCT nct = new FormPhieuNhapCT();
        nct.setVisible(true);

    }//GEN-LAST:event_lbl_nhapchitietMouseClicked

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        // TODO add your handling code here:
        addPhieuNhap();

    }//GEN-LAST:event_btn_addActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
        updatePhieuNhap();
    }//GEN-LAST:event_btn_updateActionPerformed

    private void tbl_phieunhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_phieunhapMouseClicked
        // TODO add your handling code here:
        clickHerePN();
    }//GEN-LAST:event_tbl_phieunhapMouseClicked

    private void tbl_phieuNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_phieuNhapMouseClicked
        // TODO add your handling code here:
        clickHerePN();
    }//GEN-LAST:event_tbl_phieuNhapMouseClicked

    private void btn_ThemNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemNhapActionPerformed
        addPhieuNhap();
    }//GEN-LAST:event_btn_ThemNhapActionPerformed

    private void btn_SuaNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaNhapActionPerformed
        updatePhieuNhap();
    }//GEN-LAST:event_btn_SuaNhapActionPerformed

    private void btn_XoaNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaNhapActionPerformed
        // TODO add your handling code here:
        deletePhieuNhap();
    }//GEN-LAST:event_btn_XoaNhapActionPerformed

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
            java.util.logging.Logger.getLogger(formPhieunhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formPhieunhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formPhieunhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formPhieunhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formPhieunhap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_SuaNhap;
    private javax.swing.JButton btn_ThemNhap;
    private javax.swing.JButton btn_XoaNhap;
    private javax.swing.JButton btn_timkiemPN;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_nhapchitiet;
    private javax.swing.JPanel pnPhieuNhap2;
    private javax.swing.JTable tbl_phieuNhap;
    private javax.swing.JTextField txt_MaPhieuNhap;
    private javax.swing.JTextField txt_NgayNhapPN;
    private javax.swing.JTextField txt_NhaCungCapPN;
    private javax.swing.JTextField txt_timkiemPN;
    // End of variables declaration//GEN-END:variables
}
