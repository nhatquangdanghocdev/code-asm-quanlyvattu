package view;
import java.sql.*;
import dao.DAO_VATTU;
import static dao.DAO_VATTU.getConnection;
import Model.VatTu;
import static com.microsoft.sqlserver.jdbc.StringUtils.isNumeric;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



public class formVatTu extends javax.swing.JFrame {
   private DefaultTableModel tableModel; 
    public formVatTu() {
        initComponents();
        setLocationRelativeTo(null);
        tableModel = (DefaultTableModel) tbl_VatTu.getModel();
        fillToTableVT();
    }
    
    public void clickHereVT() {
    int row = tbl_VatTu.getSelectedRow(); // Lấy chỉ số dòng được chọn

    // Kiểm tra xem có dòng nào được chọn không
    if (row != -1) {
        try {
            // Lấy dữ liệu từ bảng và chuyển đổi kiểu dữ liệu tương ứng
            String maVatTu = tbl_VatTu.getValueAt(row, 0).toString(); // Lấy mã vật tư (int)
            String tenVatTu = tbl_VatTu.getValueAt(row, 1).toString(); // Lấy tên vật tư (String)
            String donViTinh = tbl_VatTu.getValueAt(row, 2).toString(); // Lấy đơn vị tính (String)
            double donGia = Double.parseDouble(tbl_VatTu.getValueAt(row, 3).toString()); // Lấy đơn giá (double)

            // Cập nhật các trường nhập liệu
            txt_Mavt.setText(String.valueOf(maVatTu)); // Hiển thị mã vật tư dưới dạng chuỗi
            txt_TenVT.setText(tenVatTu);
            txt_Donvitinh.setText(donViTinh);
            txt_Dongia.setText(String.valueOf(donGia)); // Hiển thị đơn giá dưới dạng chuỗi
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi chuyển đổi dữ liệu: " + e.getMessage());
        }
    } else {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng trong bảng!");
    }
}
    
    private void fillToTableVT() {
        tableModel.setRowCount(0); // Xóa dữ liệu cũ
        List<VatTu> vatTuList = DAO_VATTU.getAllVatTu();
        for (VatTu vt : vatTuList) {
            tableModel.addRow(new Object[]{vt.getMavattu(), vt.getTenVattu(), vt.getDonvitinh(), vt.getDongia()});
        }
    }

    // Phương thức thêm vật tư
    private void addVatTu() {
        if (txt_Mavt.getText().isEmpty() || txt_TenVT.getText().isEmpty() ||
            txt_Donvitinh.getText().isEmpty() || txt_Dongia.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        try {
            VatTu vt = new VatTu(
                txt_Mavt.getText(),
                txt_TenVT.getText(),
                txt_Donvitinh.getText(),
                Double.parseDouble(txt_Dongia.getText())
            );
            DAO_VATTU.insertVatTu(vt);
            JOptionPane.showMessageDialog(this, "Thêm vật tư thành công!");
            fillToTableVT(); // Cập nhật bảng
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }

    // Phương thức cập nhật vật tư
   private void updateVatTu() {
    if (txt_Mavt.getText().isEmpty() || txt_TenVT.getText().isEmpty() ||
        txt_Donvitinh.getText().isEmpty() || txt_Dongia.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
        return;
    }

    String dongia = txt_Dongia.getText().trim();

    if (!isNumeric(dongia)) {
        JOptionPane.showMessageDialog(this, "Đơn giá phải là số hợp lệ!");
        return;
    }

    try {
        VatTu vt = new VatTu(
            txt_Mavt.getText().trim(),
            txt_TenVT.getText().trim(),
            txt_Donvitinh.getText().trim(),
            Double.parseDouble(dongia)
        );
        DAO_VATTU.updateVatTu(vt);
        JOptionPane.showMessageDialog(this, "Cập nhật vật tư thành công!");
        fillToTableVT(); // Cập nhật bảng
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Lỗi định dạng số: " + e.getMessage());
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
    }
}


    // Phương thức xóa vật tư
    private void deleteVatTu() {
        if (txt_Mavt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã vật tư để xóa!");
            return;
        }

        try {
            String maVatTu = txt_Mavt.getText();
            boolean success = DAO_VATTU.deleteVatTu(maVatTu);
            if (success) {
                JOptionPane.showMessageDialog(this, "Xóa vật tư thành công!");
                fillToTableVT(); // Cập nhật bảng
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy mã vật tư để xóa.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }
    
   


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnVattu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_TenVT = new javax.swing.JTextField();
        txt_Donvitinh = new javax.swing.JTextField();
        txt_Dongia = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_VatTu = new javax.swing.JTable();
        btn_Them = new javax.swing.JButton();
        btn_Sua = new javax.swing.JButton();
        btn_XOA = new javax.swing.JButton();
        txt_HienTimKiem = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btn_TIm = new javax.swing.JButton();
        txt_Mavt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        pnVattu.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Source Sans Pro Black", 0, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Vật Tư");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Mã Vật Tư:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Tên Vật Tư:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Đơn Vị Tính:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Đơn Giá:");

        txt_TenVT.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_Donvitinh.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_Dongia.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbl_VatTu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_VatTu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Vật Tư", "Tên Vật Tư", "Đơn Vị Tính", "Đơn Giá"
            }
        ));
        tbl_VatTu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_VatTuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_VatTu);

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

        btn_XOA.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_XOA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon_delete.png"))); // NOI18N
        btn_XOA.setText("Xóa");
        btn_XOA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XOAActionPerformed(evt);
            }
        });

        txt_HienTimKiem.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Tìm Kiếm:");

        btn_TIm.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_TIm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon_restart.png"))); // NOI18N
        btn_TIm.setText("Tìm");
        btn_TIm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TImActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnVattuLayout = new javax.swing.GroupLayout(pnVattu);
        pnVattu.setLayout(pnVattuLayout);
        pnVattuLayout.setHorizontalGroup(
            pnVattuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnVattuLayout.createSequentialGroup()
                .addGroup(pnVattuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnVattuLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(pnVattuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnVattuLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(btn_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addComponent(btn_XOA, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnVattuLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnVattuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_TenVT, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                            .addComponent(txt_Donvitinh)
                            .addComponent(txt_Dongia)
                            .addComponent(txt_Mavt))))
                .addGap(25, 25, 25)
                .addGroup(pnVattuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnVattuLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_HienTimKiem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_TIm, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnVattuLayout.setVerticalGroup(
            pnVattuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnVattuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(pnVattuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnVattuLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(pnVattuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnVattuLayout.createSequentialGroup()
                                .addGroup(pnVattuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_Mavt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(79, 79, 79)
                                .addGroup(pnVattuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnVattuLayout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(txt_TenVT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_Donvitinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_Dongia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(81, 81, 81)
                                .addComponent(btn_XOA, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(195, Short.MAX_VALUE))
                    .addGroup(pnVattuLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(pnVattuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_HienTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_TIm, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                        .addGap(114, 114, 114))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnVattu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnVattu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_XOAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XOAActionPerformed
       deleteVatTu();
    }//GEN-LAST:event_btn_XOAActionPerformed

    private void btn_TImActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TImActionPerformed
        
    }//GEN-LAST:event_btn_TImActionPerformed

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        addVatTu();
    }//GEN-LAST:event_btn_ThemActionPerformed

    private void btn_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaActionPerformed
        updateVatTu();
    }//GEN-LAST:event_btn_SuaActionPerformed

    private void tbl_VatTuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_VatTuMouseClicked
        clickHereVT();
    }//GEN-LAST:event_tbl_VatTuMouseClicked

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
            java.util.logging.Logger.getLogger(formVatTu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formVatTu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formVatTu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formVatTu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formVatTu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_TIm;
    private javax.swing.JButton btn_Them;
    private javax.swing.JButton btn_XOA;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnVattu;
    private javax.swing.JTable tbl_VatTu;
    private javax.swing.JTextField txt_Dongia;
    private javax.swing.JTextField txt_Donvitinh;
    private javax.swing.JTextField txt_HienTimKiem;
    private javax.swing.JTextField txt_Mavt;
    private javax.swing.JTextField txt_TenVT;
    // End of variables declaration//GEN-END:variables

   
}
