package view;

import Model.PhieuXuat;
import DAO.DAO_PhieuXuat;
import java.util.List;
import static com.microsoft.sqlserver.jdbc.StringUtils.isNumeric;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class formPhieuxuat extends javax.swing.JFrame {

    private DefaultTableModel tbl_Model;

    public formPhieuxuat() {
        initComponents();
        setLocationRelativeTo(null);
        tbl_Model = (DefaultTableModel) tbl_PhieuXuat.getModel();
        fillToTablePX();
    }

    public void clickHerePX() {
        int row = tbl_PhieuXuat.getSelectedRow(); // Lấy chỉ số dòng được chọn

        // Kiểm tra xem có dòng nào được chọn không
        if (row != -1) {
            try {
                // Lấy dữ liệu từ bảng và chuyển đổi kiểu dữ liệu tương ứng
                String maPhieuXuat = String.valueOf(tbl_PhieuXuat.getValueAt(row, 0)); // Lấy mã phiếu xuất (String)
                String maPhongBan = String.valueOf(tbl_PhieuXuat.getValueAt(row, 1)); // Lấy mã phòng ban (String)
                String maKho = String.valueOf(tbl_PhieuXuat.getValueAt(row, 2)); // Lấy mã kho (String)  
                String ngayXuat = String.valueOf(tbl_PhieuXuat.getValueAt(row, 3)); // Lấy ngày xuất (String)

                // Cập nhật các trường nhập liệu
                txt_MaPhieuXuat.setText(maPhieuXuat); // Hiển thị mã phiếu xuất dưới dạng chuỗi
                txt_MaPhongBan.setText(maPhongBan); // Hiển thị phòng ban dưới dạng chuỗi
                txt_MaKho.setText(maKho); // Hiển thị mã kho dưới dạng chuỗi
                txt_NgayXuat.setText(ngayXuat);// Hiển thị ngày xuất dưới dạng chuỗi

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi chuyển đổi dữ liệu: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng trong bảng!");
        }
    }

    private void fillToTablePX() {
        tbl_Model.setRowCount(0); // Xóa dữ liệu cũ
        List<PhieuXuat> list_PhieuXuat = DAO_PhieuXuat.getAllPhieuXuat();
        for (PhieuXuat px : list_PhieuXuat) {
            tbl_Model.addRow(new Object[]{px.getMaPhieuXuat(), px.getMaPhongBan(), px.getMaKho(), px.getNgayXuat()});
        }
    }

    private void addPhieuXuat() {
        if (txt_MaPhieuXuat.getText().isEmpty() || txt_MaPhongBan.getText().isEmpty()
                || txt_MaKho.getText().isEmpty() || txt_NgayXuat.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        try {
            PhieuXuat px = new PhieuXuat();
            px.setMaPhieuXuat(String.valueOf(txt_MaPhieuXuat.getText()));
            px.setMaPhongBan(String.valueOf(txt_MaPhongBan.getText()));
            px.setMaKho(String.valueOf(txt_MaKho.getText()));
            px.setNgayXuat(String.valueOf(txt_NgayXuat.getText()));

            boolean success = DAO_PhieuXuat.insertIntoPhieuXuat(px);
            if (success) {
                JOptionPane.showMessageDialog(this, "Thêm phiếu xuất thành công!");
                fillToTablePX(); // Cập nhật bảng
            } else {
                JOptionPane.showMessageDialog(this, "Thêm phiếu xuất thất bại, vui lòng kiểm tra lại thông tin nhập vào!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
//            JOptionPane.showMessageDialog(this, "Errol!!!");
        }
    }

    private void updatePhieuXuat() {
        if (txt_MaPhieuXuat.getText().isEmpty() || txt_MaPhongBan.getText().isEmpty()
                || txt_MaKho.getText().isEmpty() || txt_NgayXuat.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        try {
            PhieuXuat px = new PhieuXuat(
                    String.valueOf(txt_MaPhieuXuat.getText().trim()),
                    String.valueOf(txt_MaPhongBan.getText().trim()),
                    String.valueOf(txt_MaKho.getText().trim()),
                    String.valueOf(txt_NgayXuat.getText().trim())
            );

            DAO.DAO_PhieuXuat.updatePhieuXuat(px);
            JOptionPane.showMessageDialog(this, "Cập nhật phiếu xuất " + txt_MaPhieuXuat.getText() + " thành công!");
            fillToTablePX(); // Cập nhật bảng

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lỗi định dạng số: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }

    }

    private void deletePhieuXuat() {
        if (txt_MaPhieuXuat.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã phiếu xuất để xóa!");
            return;
        }

        try {
            String maPhieuXuat = String.valueOf(txt_MaPhieuXuat.getText());
            boolean success = DAO_PhieuXuat.deletePhieuXuat(maPhieuXuat);
            if (success) {
                JOptionPane.showMessageDialog(this, "Xóa phiếu xuất thành công!");
                fillToTablePX(); // Cập nhật bảng
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy mã phiếu để xóa.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }
    
    private void timPhieuXuat(){
        String maPhieuXuat = String.valueOf(txt_TimKiem.getText().trim());
        if (maPhieuXuat.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã phiếu xuất để xóa!");
            return;
        }
        PhieuXuat px = DAO_PhieuXuat.searchWithMaPhieuXuat(maPhieuXuat);
        if (px == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy phiếu xuất với mã: " + maPhieuXuat);
            // Xóa sạch các trường nhập liệu và bảng nếu không tìm thấy
            txt_MaPhieuXuat.setText("");
            txt_MaPhongBan.setText("");
            txt_MaKho.setText("");
            txt_NgayXuat.setText("");
            tbl_Model.setRowCount(0); // Xóa sạch bảng
        } else {
            // Hiển thị thông tin lên các trường nhập liệu
            txt_MaPhieuXuat.setText(px.getMaPhieuXuat());
            txt_MaPhongBan.setText(px.getMaPhongBan());
            txt_MaKho.setText(px.getMaKho());
            txt_NgayXuat.setText(px.getNgayXuat());
            // Cập nhật bảng chỉ với kết quả tìm kiếm
            tbl_Model.setRowCount(0); // Xóa dữ liệu cũ trong bảng
            tbl_Model.addRow(new Object[]{px.getMaPhieuXuat(), px.getMaPhongBan(), px.getMaKho(), px.getNgayXuat()});
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnPhieuXuat = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btn_Them = new javax.swing.JButton();
        btn_Sua = new javax.swing.JButton();
        btn_Xoa = new javax.swing.JButton();
        txt_NgayXuat = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_MaKho = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_MaPhongBan = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_MaPhieuXuat = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_PhieuXuat = new javax.swing.JTable();
        txt_TimKiem = new javax.swing.JTextField();
        btn_Tim = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        lbl_xuatchitiet = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        pnPhieuXuat.setPreferredSize(new java.awt.Dimension(960, 600));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        btn_Them.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_Them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon_add.png"))); // NOI18N
        btn_Them.setText("Thêm");
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
            }
        });

        btn_Sua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_Sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon_fix.png"))); // NOI18N
        btn_Sua.setText("Sửa");
        btn_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaActionPerformed(evt);
            }
        });

        btn_Xoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_Xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon_delete.png"))); // NOI18N
        btn_Xoa.setText("Xóa");
        btn_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaActionPerformed(evt);
            }
        });

        txt_NgayXuat.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Ngày Xuất :");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Mã Kho :");

        txt_MaKho.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Mã Phòng Ban :");

        txt_MaPhongBan.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Mã Phiếu Xuất :");

        txt_MaPhieuXuat.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Tìm Kiếm:");

        tbl_PhieuXuat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_PhieuXuat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Phiếu Xuất", "Mã Phòng Ban", "Mã Kho", "Ngày Xuất"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_PhieuXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_PhieuXuatMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_PhieuXuat);

        txt_TimKiem.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_Tim.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_Tim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon_restart.png"))); // NOI18N
        btn_Tim.setText("Tìm");
        btn_Tim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Source Sans Pro Black", 0, 48)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Phiếu Xuất");

        lbl_xuatchitiet.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lbl_xuatchitiet.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_xuatchitiet.setText("Phiếu Xuất Chi Tiết");
        lbl_xuatchitiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_xuatchitietMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(btn_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(btn_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(10, 10, 10))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_MaPhongBan, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txt_MaKho, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txt_MaPhieuXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt_NgayXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_TimKiem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_Tim, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)))
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_xuatchitiet, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Tim, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_MaPhieuXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_MaPhongBan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_MaKho, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_NgayXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(94, 94, 94)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(btn_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(60, 60, 60)
                .addComponent(lbl_xuatchitiet, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout pnPhieuXuatLayout = new javax.swing.GroupLayout(pnPhieuXuat);
        pnPhieuXuat.setLayout(pnPhieuXuatLayout);
        pnPhieuXuatLayout.setHorizontalGroup(
            pnPhieuXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnPhieuXuatLayout.setVerticalGroup(
            pnPhieuXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(pnPhieuXuat, "card3");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaActionPerformed
        deletePhieuXuat();
    }//GEN-LAST:event_btn_XoaActionPerformed

    private void btn_TimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimActionPerformed
        timPhieuXuat();
    }//GEN-LAST:event_btn_TimActionPerformed

    private void lbl_xuatchitietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_xuatchitietMouseClicked
        this.dispose();
        FormPhieuXuatCT formPXCT = new FormPhieuXuatCT();
        formPXCT.setVisible(true);
    }//GEN-LAST:event_lbl_xuatchitietMouseClicked

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        addPhieuXuat();
    }//GEN-LAST:event_btn_ThemActionPerformed

    private void btn_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaActionPerformed
        updatePhieuXuat();
    }//GEN-LAST:event_btn_SuaActionPerformed

    private void tbl_PhieuXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_PhieuXuatMouseClicked
        clickHerePX();
    }//GEN-LAST:event_tbl_PhieuXuatMouseClicked

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
            java.util.logging.Logger.getLogger(formPhieuxuat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formPhieuxuat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formPhieuxuat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formPhieuxuat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formPhieuxuat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_Them;
    private javax.swing.JButton btn_Tim;
    private javax.swing.JButton btn_Xoa;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_xuatchitiet;
    private javax.swing.JPanel pnPhieuXuat;
    private javax.swing.JTable tbl_PhieuXuat;
    private javax.swing.JTextField txt_MaKho;
    private javax.swing.JTextField txt_MaPhieuXuat;
    private javax.swing.JTextField txt_MaPhongBan;
    private javax.swing.JTextField txt_NgayXuat;
    private javax.swing.JTextField txt_TimKiem;
    // End of variables declaration//GEN-END:variables
}
