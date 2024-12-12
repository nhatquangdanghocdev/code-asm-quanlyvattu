package Menu;

import view.Login;
import demourl.SendMail;
import view.FormPhieuNhapCT;
import view.FormPhieuXuatCT;
import dao.DAO_PHIEUNHAP;
import dao.DAO_VATTU;
import dao.DAO_KHO;
import dao.DAO_PHONGBAN;
import dao.DAO_NHACUNGCAP;
import DAO.DAO_PhieuXuat;
import static dao.DAO_KHO.getConnection;
import static dao.DAO_VATTU.getConnection;
import Model.VatTu;
import Model.Kho;
import Model.PhongBan;
import Model.NhaCungCap;
import Model.PhieuNhap;
import Model.PhieuXuat;
import static com.microsoft.sqlserver.jdbc.StringUtils.isNumeric;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MenuPart2 extends javax.swing.JFrame {

    private DefaultTableModel tableModelVatTu;
    private DefaultTableModel tableModelKho;
    private DefaultTableModel tableModelPhongBan;
    private DefaultTableModel tableModelNhaCungCap;
    private DefaultTableModel tableModelPhieuNhap;
    private DefaultTableModel tableModelPhieuXuat;

    public MenuPart2() {
        initComponents();
        setLocationRelativeTo(null);

        // Khởi tạo model cho từng bảng riêng biệt
        tableModelVatTu = (DefaultTableModel) tbl_VatTu.getModel();
        tableModelKho = (DefaultTableModel) tbl_Kho.getModel();
        tableModelPhongBan = (DefaultTableModel) tbl_Phongban.getModel();
        tableModelNhaCungCap = (DefaultTableModel) tbl_NhaCungCap.getModel();
        tableModelPhieuNhap = (DefaultTableModel) tbl_phieuNhap.getModel();
        tableModelPhieuXuat = (DefaultTableModel) tbl_PhieuXuat.getModel();

        // Gọi phương thức để điền dữ liệu vào bảng
        fillToTableVT();
        fillToTableKho();
        fillToTablePB();
        fillToTableNCC();
        fillToTablePN();
        fillToTablePX();
    }

//--------------------------------
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
    //-----------------------------------

    public void clickHereKho() {
        int row = tbl_Kho.getSelectedRow(); // Lấy chỉ số dòng được chọn

        if (row != -1) {
            try {
                // Lấy dữ liệu từ bảng và chuyển đổi kiểu dữ liệu tương ứng
                String Makho = tbl_Kho.getValueAt(row, 0).toString(); // Lấy mã vật tư (int)
                String maVatTu = tbl_Kho.getValueAt(row, 1).toString(); // Lấy tên vật tư (String)
                String SoLuongNhap = tbl_Kho.getValueAt(row, 2).toString(); // Lấy đơn vị tính (String)
                String SoLuongTon = tbl_Kho.getValueAt(row, 3).toString();
                String NgayNhap = (tbl_Kho.getValueAt(row, 4).toString());
                txt_makho.setText(String.valueOf(Makho)); // Hiển thị mã vật tư dưới dạng chuỗi
                txt_mavattu.setText(String.valueOf(maVatTu));
                txt_soluongnhap.setText(String.valueOf(SoLuongNhap));
                txt_soluongton.setText(String.valueOf(SoLuongTon));
                txt_ngaynhap.setText(String.valueOf(NgayNhap));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi chuyển đổi dữ liệu: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng trong bảng!");
        }
    }

    public void clickHerePB() {
        int row = tbl_Phongban.getSelectedRow(); // Lấy chỉ số dòng được chọn

        // Kiểm tra xem có dòng nào được chọn không
        if (row != -1) {
            try {
                // Lấy dữ liệu từ bảng và chuyển đổi kiểu dữ liệu tương ứng
                String mapb = tbl_Phongban.getValueAt(row, 0).toString();
                String tenpb = tbl_Phongban.getValueAt(row, 1).toString();
                String diachi = tbl_Phongban.getValueAt(row, 2).toString();
                String sdt = tbl_Phongban.getValueAt(row, 3).toString();
                String email = tbl_Phongban.getValueAt(row, 4).toString();

                // Cập nhật các trường nhập liệu
                txt_maPb.setText(String.valueOf(mapb)); // Hiển thị mã vật tư dưới dạng chuỗi
                txt_Tenpb.setText(tenpb);
                txt_diachi.setText(diachi);
                txt_sdt.setText(sdt);
                txt_email.setText(email);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi chuyển đổi dữ liệu: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng trong bảng!");
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

    //---------------------------
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
    //------------------------------

    //-------------------------------
    private void fillToTableVT() {
        tableModelVatTu.setRowCount(0); // Xóa dữ liệu cũ
        List<VatTu> vatTuList = DAO_VATTU.getAllVatTu();
        for (VatTu vt : vatTuList) {
            tableModelVatTu.addRow(new Object[]{vt.getMavattu(), vt.getTenVattu(), vt.getDonvitinh(), vt.getDongia()});
        }
    }
    //--------------------------------

    private void fillToTableKho() {
        tableModelKho.setRowCount(0); // Xóa dữ liệu cũ
        List<Kho> khoList = DAO_KHO.getAllKho();
        for (Kho kh : khoList) {
            tableModelKho.addRow(new Object[]{kh.getMaKho(), kh.getMaVatTu(), kh.getSoLuongNhap(), kh.getSoLuongTon(), kh.getNgayNhap()});

        }
    }

    //----------------------------
    private void fillToTablePB() {
        tableModelPhongBan.setRowCount(0); // Xóa dữ liệu cũ
        List<PhongBan> PhongList = DAO_PHONGBAN.getAllPhongban();
        for (PhongBan pb : PhongList) {
            tableModelPhongBan.addRow(new Object[]{pb.getMaPB(), pb.getTenPB(), pb.getDiachi(), pb.getSoDienThoai(), pb.getEmail()});
        }
    }
    //----------------------------

    private void fillToTableNCC() {
        tableModelNhaCungCap.setRowCount(0); // Xóa dữ liệu cũ
        List<NhaCungCap> list_NhaCungCap = DAO_NHACUNGCAP.getAllNhaCungCap();
        for (NhaCungCap ncc : list_NhaCungCap) {
            tableModelNhaCungCap.addRow(new Object[]{ncc.getMaNhaCungCap(), ncc.getTenNhaCungCap(), ncc.getSoDienThoai(), ncc.getEmail(), ncc.getDiaChi()});
        }
    }

    //----------------------------
    private void fillToTablePN() {
        tableModelPhieuNhap.setRowCount(0); // Xóa dữ liệu cũ
        List<PhieuNhap> phieuNhapList = DAO_PHIEUNHAP.getAllPhieuNhap();
        for (PhieuNhap pn : phieuNhapList) {
            tableModelPhieuNhap.addRow(new Object[]{pn.getMaPhieuNhapKho(), pn.getNgayNhap(), pn.getMaNhaCungCap()});
        }
    }
    //----------------------------

    // Phương thức thêm vật tư -------------
    private void addVatTu() {
        if (txt_Mavt.getText().isEmpty() || txt_TenVT.getText().isEmpty()
                || txt_Donvitinh.getText().isEmpty() || txt_Dongia.getText().isEmpty()) {
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
    //------------------------

    //----------------------------
    private void addPhog() {
        if (txt_maPb.getText().isEmpty() || txt_Tenpb.getText().isEmpty()
                || txt_diachi.getText().isEmpty() || txt_sdt.getText().isEmpty() || txt_email.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        try {
            PhongBan pb = new PhongBan(
                    txt_maPb.getText(),
                    txt_Tenpb.getText(),
                    txt_diachi.getText(),
                    txt_sdt.getText(),
                    txt_email.getText()
            );
            DAO_PHONGBAN.insertPhg(pb);
            JOptionPane.showMessageDialog(this, "Thêm Phong Ban thành công!");
            fillToTablePB(); // Cập nhật bảng
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }
    //-------------------------------

    //-------------------------------
    private void addKho() {
        if (txt_makho.getText().isEmpty() || txt_mavattu.getText().isEmpty()
                || txt_soluongnhap.getText().isEmpty() || txt_soluongton.getText().isEmpty()
                || txt_ngaynhap.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        try {
            Kho kh = new Kho();
            kh.setMaKho(String.valueOf(txt_makho.getText().trim()));
            kh.setMaVatTu(String.valueOf(txt_mavattu.getText().trim()));
            kh.setSoLuongNhap(Integer.parseInt(txt_soluongnhap.getText().trim()));
            kh.setSoLuongTon(Integer.parseInt(txt_soluongton.getText().trim()));

            // Chuyển đổi ngày nhập
            String inputDate = txt_ngaynhap.getText().trim(); // Người dùng nhập dd/MM/yyyy
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date utilDate = sdf.parse(inputDate);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            kh.setNgayNhap(sqlDate);

            // Gọi DAO để thêm vào cơ sở dữ liệu
            DAO_KHO.insertKho(kh);
            JOptionPane.showMessageDialog(this, "Thêm vật tư vào kho thành công!");
            fillToTableKho(); // Cập nhật bảng
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lỗi định dạng số: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }
    //-------------------------------

    //-------------------------------
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
            JOptionPane.showMessageDialog(this, "Thêm PN thành công!");
            fillToTablePN(); // Cập nhật bảng
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }//--------------------------------

    // Phương thức cập nhật vật tư -----------------------
    private void updateVatTu() {
        if (txt_Mavt.getText().isEmpty() || txt_TenVT.getText().isEmpty()
                || txt_Donvitinh.getText().isEmpty() || txt_Dongia.getText().isEmpty()) {
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
    //----------------------------

    //----------------------------
    private void updatePhog() {
        if (txt_maPb.getText().isEmpty() || txt_Tenpb.getText().isEmpty()
                || txt_diachi.getText().isEmpty() || txt_sdt.getText().isEmpty() || txt_email.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        try {
            PhongBan pb = new PhongBan(
                    txt_maPb.getText().trim(),
                    txt_Tenpb.getText(),
                    txt_diachi.getText(),
                    txt_sdt.getText(),
                    txt_email.getText()
            );
            DAO_PHONGBAN.updatePhg(pb);
            JOptionPane.showMessageDialog(this, "Cập nhật Phong ban thành công!");
            fillToTablePB(); // Cập nhật bảng
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lỗi định dạng số: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }
    //-------------------------------

    //-------------------------------
    private void updateKho() {
        if (txt_makho.getText().isEmpty() || txt_mavattu.getText().isEmpty()
                || txt_soluongnhap.getText().isEmpty() || txt_soluongton.getText().isEmpty()
                || txt_ngaynhap.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        try {
            // Tạo đối tượng Kho và gán các giá trị từ giao diện
            Kho kh = new Kho();
            kh.setMaKho(String.valueOf(txt_makho.getText().trim()));
            kh.setMaVatTu(String.valueOf(txt_mavattu.getText().trim()));
            kh.setSoLuongNhap(Integer.parseInt(txt_soluongnhap.getText().trim()));
            kh.setSoLuongTon(Integer.parseInt(txt_soluongton.getText().trim()));

            // Chuyển đổi ngày từ chuỗi nhập vào thành java.sql.Date
            String inputDate = txt_ngaynhap.getText().trim(); // Giả sử người dùng nhập dd/MM/yyyy
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date utilDate = sdf.parse(inputDate);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            kh.setNgayNhap(sqlDate);

            // Gọi DAO để cập nhật dữ liệu
            DAO_KHO.updateKho(kh);
            JOptionPane.showMessageDialog(this, "Cập nhật vật tư thành công!");
            fillToTableKho(); // Cập nhật bảng
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lỗi định dạng số: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }
    //-----------------------------------------

    //-----------------------------------------
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
    //--------------------------------------------

    // Phương thức xóa vật tư -------------------
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
    //---------------------------

    //----------------------------
    private void deletePhog() {
        if (txt_maPb.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã phòng ban để xóa!");
            return;
        }

        try {
            String MaPB = txt_maPb.getText();

            // Kiểm tra ràng buộc tham chiếu
            if (DAO_PHONGBAN.isPhongBanReferenced(MaPB)) {
                JOptionPane.showMessageDialog(this, "Không thể xóa phòng ban vì đang được sử dụng trong Phiếu Xuất!");
                return;
            }

            boolean success = DAO_PHONGBAN.deletePhog(MaPB);
            if (success) {
                JOptionPane.showMessageDialog(this, "Xóa phòng ban thành công!");
                fillToTablePB(); // Cập nhật bảng
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy mã phòng ban để xóa.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Mã phòng ban phải là số hợp lệ!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }
    //----------------------------------

    //---------------------------------
    private void deletekho() {
        if (txt_makho.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã kho để xóa!");
            return;
        }

        try {
            String maKho = txt_makho.getText();

            // Kiểm tra ràng buộc tham chiếu
            if (DAO_KHO.isKhoReferenced(maKho)) {
                JOptionPane.showMessageDialog(this, "Không thể xóa kho vì đang được tham chiếu trong Phiếu Xuất!");
                return;
            }

            boolean success = DAO_KHO.deleteKho(maKho);
            if (success) {
                JOptionPane.showMessageDialog(this, "Xóa kho thành công!");
                fillToTableKho(); // Cập nhật bảng
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy mã kho để xóa.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Mã kho phải là số hợp lệ!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }
    //-------------------------------

    //-------------------------------
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

    //-------------------------------
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
        tableModelPhieuXuat.setRowCount(0); // Xóa dữ liệu cũ
        List<PhieuXuat> list_PhieuXuat = DAO_PhieuXuat.getAllPhieuXuat();
        for (PhieuXuat px : list_PhieuXuat) {
            tableModelPhieuXuat.addRow(new Object[]{px.getMaPhieuXuat(), px.getMaPhongBan(), px.getMaKho(), px.getNgayXuat()});
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

    private void timPhieuXuat() {
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
            tableModelPhieuXuat.setRowCount(0); // Xóa sạch bảng
        } else {
            // Hiển thị thông tin lên các trường nhập liệu
            txt_MaPhieuXuat.setText(px.getMaPhieuXuat());
            txt_MaPhongBan.setText(px.getMaPhongBan());
            txt_MaKho.setText(px.getMaKho());
            txt_NgayXuat.setText(px.getNgayXuat());
            // Cập nhật bảng chỉ với kết quả tìm kiếm
            tableModelPhieuXuat.setRowCount(0); // Xóa dữ liệu cũ trong bảng
            tableModelPhieuXuat.addRow(new Object[]{px.getMaPhieuXuat(), px.getMaPhongBan(), px.getMaKho(), px.getNgayXuat()});
        }

    }
    
    private void timPhieuNhap() {
    String maPhieuNhap = txt_timkiemPN.getText().trim();
    if (maPhieuNhap.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập mã phiếu xuất để tìm kiếm!");
        return;
    }

    PhieuNhap pn = DAO_PHIEUNHAP.searchWithMaPhieuNhap(maPhieuNhap); // Gọi phương thức DAO
    if (pn == null) {
        JOptionPane.showMessageDialog(this, "Không tìm thấy phiếu xuất với mã: " + maPhieuNhap);
        // Giữ dữ liệu cũ trên bảng, nhưng xóa các trường nhập liệu
        txt_MaPhieuNhap.setText("");
        txt_NgayNhapPN.setText("");
        txt_NhaCungCapPN.setText("");
        
    } else {
        // Hiển thị thông tin lên các trường nhập liệu
        txt_MaPhieuNhap.setText(pn.getMaPhieuNhapKho());
        txt_NgayNhapPN.setText(pn.getNgayNhap());
        txt_NhaCungCapPN.setText(pn.getMaNhaCungCap());
        

        // Cập nhật bảng chỉ với kết quả tìm kiếm
        tableModelPhieuNhap.setRowCount(0); // Xóa dữ liệu cũ trong bảng
        tableModelPhieuNhap.addRow(new Object[]{pn.getMaPhieuNhapKho(), pn.getNgayNhap(), pn.getMaNhaCungCap() });

        // Tự động chọn dòng đầu tiên trong bảng
        tbl_PhieuXuat.setRowSelectionInterval(0, 0);
    }
}
    
    //----------------------------
    
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
        String searchKeyword = txt_TimKiem1.getText().trim(); // Giả sử bạn có một JTextField với tên là txtSearch

        // Kiểm tra nếu ô tìm kiếm trống
        if (searchKeyword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã nhà cung cấp cần tìm.");
            return;
        }

        String maNhaCungCap = String.valueOf(txt_TimKiem1.getText().trim());
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
            tableModelNhaCungCap.setRowCount(0); // Xóa sạch bảng
        } else {
            // Hiển thị thông tin lên các trường nhập liệu
            txt_MaNhaCungCap.setText(ncc.getMaNhaCungCap());
            txt_TenNhaCungCap.setText(ncc.getTenNhaCungCap());
            txt_SoDienThoai.setText(ncc.getSoDienThoai());
            txt_Email.setText(ncc.getEmail());
            txt_DiaChi.setText(ncc.getDiaChi());
            // Cập nhật bảng chỉ với kết quả tìm kiếm
            tableModelNhaCungCap.setRowCount(0); // Xóa dữ liệu cũ trong bảng
            tableModelNhaCungCap.addRow(new Object[]{ncc.getMaNhaCungCap(), ncc.getTenNhaCungCap(), ncc.getSoDienThoai(), ncc.getEmail(), ncc.getDiaChi()});
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



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnQuanLyVatTu = new javax.swing.JPanel();
        pnMenu = new javax.swing.JPanel();
        lbl_Vattu = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbl_Kho = new javax.swing.JLabel();
        lbl_nhacungcap = new javax.swing.JLabel();
        lbl_phieuxuat = new javax.swing.JLabel();
        lbl_phieunhap = new javax.swing.JLabel();
        lbl_Phongban = new javax.swing.JLabel();
        lbl_Thongbaottin = new javax.swing.JLabel();
        lbl_dangxuat = new javax.swing.JLabel();
        lbl_TrangChu = new javax.swing.JLabel();
        pnMain = new javax.swing.JPanel();
        pnTrangChu = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        pnVattu = new javax.swing.JPanel();
        btn_Them = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btn_Sua = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        btn_XOA = new javax.swing.JButton();
        jLabel49 = new javax.swing.JLabel();
        txt_HienTimKiem = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btn_TIm = new javax.swing.JButton();
        txt_TenVT = new javax.swing.JTextField();
        txt_Mavt = new javax.swing.JTextField();
        txt_Donvitinh = new javax.swing.JTextField();
        txt_Dongia = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_VatTu = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        pnNhaCP = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_NhaCungCap = new javax.swing.JTable();
        btn_Add = new javax.swing.JButton();
        btn_Update = new javax.swing.JButton();
        txt_TimKiem1 = new javax.swing.JTextField();
        btn_Delete = new javax.swing.JButton();
        btn_Tim1 = new javax.swing.JButton();
        txt_SoDienThoai = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        txt_Email = new javax.swing.JTextField();
        txt_TenNhaCungCap = new javax.swing.JTextField();
        txt_DiaChi = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        txt_MaNhaCungCap = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        pnKho = new javax.swing.JPanel();
        txt_soluongnhap = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_mavattu = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_makho = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        txt_timkho = new javax.swing.JTextField();
        btn_TimKho = new javax.swing.JButton();
        btn_xoaKho = new javax.swing.JButton();
        btn_sua = new javax.swing.JButton();
        btn_them = new javax.swing.JButton();
        jLabel65 = new javax.swing.JLabel();
        txt_ngaynhap = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        txt_soluongton = new javax.swing.JTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        tbl_Kho = new javax.swing.JTable();
        jLabel67 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        pnPhieuxuat = new javax.swing.JPanel();
        pnPhieuXuat1 = new javax.swing.JPanel();
        pnPhieuXuat2 = new javax.swing.JPanel();
        lbl_xuatchitiet = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_MaPhongBan = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txt_MaPhieuXuat = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_PhieuXuat = new javax.swing.JTable();
        txt_TimKiem = new javax.swing.JTextField();
        btn_Tim = new javax.swing.JButton();
        btn_Them2 = new javax.swing.JButton();
        btn_Sua2 = new javax.swing.JButton();
        btn_Xoa = new javax.swing.JButton();
        txt_NgayXuat = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_MaKho = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        pnPhieunhap = new javax.swing.JPanel();
        pnPhieuNhap1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lbl_nhapchitiet1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_MaPhieuNhap = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_phieuNhap = new javax.swing.JTable();
        txt_timkiemPN = new javax.swing.JTextField();
        btn_timkiemPN = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        btn_ThemNhap = new javax.swing.JButton();
        btn_SuaNhap = new javax.swing.JButton();
        btn_XoaNhap = new javax.swing.JButton();
        txt_NhaCungCapPN = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txt_NgayNhapPN = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        pnPhongban = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txt_diachi = new javax.swing.JTextField();
        btn_sua1 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_Phongban = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        btn_xoa = new javax.swing.JButton();
        txt_Tenpb = new javax.swing.JTextField();
        txt_sdt = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txt_timpb = new javax.swing.JTextField();
        txt_maPb = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        btn_them1 = new javax.swing.JButton();
        btn_timpb = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        pnThongtin = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        pnQuanLyVatTu.setBackground(new java.awt.Color(255, 255, 255));

        pnMenu.setBackground(new java.awt.Color(153, 153, 153));

        lbl_Vattu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_Vattu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_Vattu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/iconVattu.png"))); // NOI18N
        lbl_Vattu.setText("Vật Tư");
        lbl_Vattu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_VattuMouseClicked(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/iconLogin.png"))); // NOI18N

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lbl_Kho.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_Kho.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_Kho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/iconKho.png"))); // NOI18N
        lbl_Kho.setText("Kho");
        lbl_Kho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_KhoMouseClicked(evt);
            }
        });

        lbl_nhacungcap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_nhacungcap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_nhacungcap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/iconNhacungcap.png"))); // NOI18N
        lbl_nhacungcap.setText("Nhà Cung Cấp");
        lbl_nhacungcap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_nhacungcapMouseClicked(evt);
            }
        });

        lbl_phieuxuat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_phieuxuat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_phieuxuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/iconxuat.png"))); // NOI18N
        lbl_phieuxuat.setText("Phiếu Xuất");
        lbl_phieuxuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_phieuxuatMouseClicked(evt);
            }
        });

        lbl_phieunhap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_phieunhap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_phieunhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/iconNhap.png"))); // NOI18N
        lbl_phieunhap.setText("Phiếu Nhập");
        lbl_phieunhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_phieunhapMouseClicked(evt);
            }
        });

        lbl_Phongban.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_Phongban.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_Phongban.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/iconCOso.png"))); // NOI18N
        lbl_Phongban.setText("Phong Ban");
        lbl_Phongban.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_PhongbanMouseClicked(evt);
            }
        });

        lbl_Thongbaottin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_Thongbaottin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_Thongbaottin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/iconNotification.png"))); // NOI18N
        lbl_Thongbaottin.setText("Hỗ Trợ");
        lbl_Thongbaottin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_ThongbaottinMouseClicked(evt);
            }
        });

        lbl_dangxuat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_dangxuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/iconOut.png"))); // NOI18N
        lbl_dangxuat.setText("Đăng Xuất");
        lbl_dangxuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_dangxuatMouseClicked(evt);
            }
        });

        lbl_TrangChu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_TrangChu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_TrangChu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/iconTrangChu.png"))); // NOI18N
        lbl_TrangChu.setText("Trang Chủ");
        lbl_TrangChu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_TrangChuMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnMenuLayout = new javax.swing.GroupLayout(pnMenu);
        pnMenu.setLayout(pnMenuLayout);
        pnMenuLayout.setHorizontalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_Vattu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnMenuLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addContainerGap(31, Short.MAX_VALUE))
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbl_Kho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbl_nhacungcap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbl_phieuxuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbl_phieunhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbl_Phongban, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbl_Thongbaottin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbl_dangxuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbl_TrangChu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnMenuLayout.setVerticalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMenuLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbl_TrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(lbl_Vattu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_Kho, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_nhacungcap, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_phieuxuat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_phieunhap, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_Phongban, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_Thongbaottin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbl_dangxuat, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnMain.setLayout(new java.awt.CardLayout());

        pnTrangChu.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel12.setFont(new java.awt.Font("Segoe UI Black", 1, 48)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Quản Lý Vật Tư ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/coleicon.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jLabel25)
                .addContainerGap(74, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/iconAdmin.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/moleticon.png"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel26)
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel26)
                .addGap(65, 65, 65))
        );

        jLabel27.setFont(new java.awt.Font("Segoe UI Black", 1, 48)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Ngũ Đại AI");

        javax.swing.GroupLayout pnTrangChuLayout = new javax.swing.GroupLayout(pnTrangChu);
        pnTrangChu.setLayout(pnTrangChuLayout);
        pnTrangChuLayout.setHorizontalGroup(
            pnTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnTrangChuLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pnTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnTrangChuLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );
        pnTrangChuLayout.setVerticalGroup(
            pnTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTrangChuLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(pnTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(60, 60, 60)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        pnMain.add(pnTrangChu, "card11");

        pnVattu.setBackground(new java.awt.Color(204, 204, 204));

        btn_Them.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_Them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon_add.png"))); // NOI18N
        btn_Them.setText("Thêm");
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Source Sans Pro Black", 0, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Vật Tư");

        btn_Sua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_Sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon_fix.png"))); // NOI18N
        btn_Sua.setText("Sửa");
        btn_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Mã Vật Tư:");

        btn_XOA.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_XOA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon_delete.png"))); // NOI18N
        btn_XOA.setText("Xóa");
        btn_XOA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XOAActionPerformed(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel49.setText("Tên Vật Tư:");

        txt_HienTimKiem.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Đơn Vị Tính:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Tìm Kiếm:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Đơn Giá:");

        btn_TIm.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_TIm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon_restart.png"))); // NOI18N
        btn_TIm.setText("Tìm");
        btn_TIm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TImActionPerformed(evt);
            }
        });

        txt_TenVT.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_Mavt.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setText("Làm Mới");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnVattuLayout = new javax.swing.GroupLayout(pnVattu);
        pnVattu.setLayout(pnVattuLayout);
        pnVattuLayout.setHorizontalGroup(
            pnVattuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnVattuLayout.createSequentialGroup()
                .addGroup(pnVattuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnVattuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_Mavt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addContainerGap(221, Short.MAX_VALUE))
                    .addGroup(pnVattuLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(pnVattuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_HienTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_TIm, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62))))
        );

        pnMain.add(pnVattu, "card10");

        pnNhaCP.setBackground(new java.awt.Color(204, 204, 204));

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel35.setText("Tìm Kiếm:");

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
        jScrollPane5.setViewportView(tbl_NhaCungCap);

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

        txt_TimKiem1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_Delete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_Delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon_delete.png"))); // NOI18N
        btn_Delete.setText("Xóa");
        btn_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DeleteActionPerformed(evt);
            }
        });

        btn_Tim1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_Tim1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon_restart.png"))); // NOI18N
        btn_Tim1.setText("Tìm");
        btn_Tim1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Tim1ActionPerformed(evt);
            }
        });

        txt_SoDienThoai.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel36.setFont(new java.awt.Font("Source Sans Pro Black", 0, 48)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("Nhà Cung Cấp");

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel37.setText("Số Điện Thoại :");

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel38.setText("Email :");

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel39.setText("Tên Nhà Cung Cấp :");

        txt_Email.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_TenNhaCungCap.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_DiaChi.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel40.setText("Mã Nhà Cung Cấp :");

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel41.setText("Địa Chỉ :");

        txt_MaNhaCungCap.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton5.setText("Làm Mới");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnNhaCPLayout = new javax.swing.GroupLayout(pnNhaCP);
        pnNhaCP.setLayout(pnNhaCPLayout);
        pnNhaCPLayout.setHorizontalGroup(
            pnNhaCPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnNhaCPLayout.createSequentialGroup()
                .addGroup(pnNhaCPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnNhaCPLayout.createSequentialGroup()
                        .addGroup(pnNhaCPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(pnNhaCPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_TenNhaCungCap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_SoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_DiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnNhaCPLayout.createSequentialGroup()
                        .addGroup(pnNhaCPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnNhaCPLayout.createSequentialGroup()
                                .addComponent(btn_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(btn_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(btn_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnNhaCPLayout.createSequentialGroup()
                                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_MaNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(pnNhaCPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnNhaCPLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_TimKiem1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_Tim1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)))
            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnNhaCPLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addContainerGap())
        );
        pnNhaCPLayout.setVerticalGroup(
            pnNhaCPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnNhaCPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel36)
                .addGroup(pnNhaCPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnNhaCPLayout.createSequentialGroup()
                        .addGroup(pnNhaCPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnNhaCPLayout.createSequentialGroup()
                                .addGap(133, 133, 133)
                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnNhaCPLayout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addGroup(pnNhaCPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_MaNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(txt_TenNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_SoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_DiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(40, 40, 40)
                        .addGroup(pnNhaCPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnNhaCPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btn_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnNhaCPLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(btn_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnNhaCPLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(pnNhaCPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_TimKiem1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Tim1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );

        pnMain.add(pnNhaCP, "card4");

        pnKho.setBackground(new java.awt.Color(204, 204, 204));

        txt_soluongnhap.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Mã Vật Tư :");

        txt_mavattu.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Mã Kho :");

        txt_makho.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel63.setFont(new java.awt.Font("Source Sans Pro Black", 0, 48)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(255, 255, 255));
        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel63.setText("Kho");

        jLabel64.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel64.setText("Tìm Kiếm:");

        txt_timkho.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_TimKho.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_TimKho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon_restart.png"))); // NOI18N
        btn_TimKho.setText("Tìm");
        btn_TimKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKhoActionPerformed(evt);
            }
        });

        btn_xoaKho.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_xoaKho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon_delete.png"))); // NOI18N
        btn_xoaKho.setText("Xóa");
        btn_xoaKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaKhoActionPerformed(evt);
            }
        });

        btn_sua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon_fix.png"))); // NOI18N
        btn_sua.setText("Sửa");
        btn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaActionPerformed(evt);
            }
        });

        btn_them.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon_add.png"))); // NOI18N
        btn_them.setText("Thêm");
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });

        jLabel65.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel65.setText("Ngày Nhập :");

        txt_ngaynhap.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel66.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel66.setText("Số Lượng Tồn :");

        txt_soluongton.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbl_Kho.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_Kho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Kho", "Mã Vật Tư", "Số Lượng Nhập", "Số Lượng Tồn", "Ngày Nhập"
            }
        ));
        tbl_Kho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_KhoMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tbl_Kho);

        jLabel67.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel67.setText("Số Lượng Nhập :");

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setText("Làm Mới");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnKhoLayout = new javax.swing.GroupLayout(pnKho);
        pnKho.setLayout(pnKhoLayout);
        pnKhoLayout.setHorizontalGroup(
            pnKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnKhoLayout.createSequentialGroup()
                .addGroup(pnKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnKhoLayout.createSequentialGroup()
                        .addGroup(pnKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                            .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(pnKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_mavattu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                                .addComponent(txt_soluongnhap, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txt_makho))
                            .addComponent(txt_soluongton, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_ngaynhap, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnKhoLayout.createSequentialGroup()
                        .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(btn_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(btn_xoaKho, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(pnKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnKhoLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_timkho)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_TimKho, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)))
            .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnKhoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );
        pnKhoLayout.setVerticalGroup(
            pnKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnKhoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel63)
                .addGroup(pnKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnKhoLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(pnKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnKhoLayout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnKhoLayout.createSequentialGroup()
                                .addComponent(txt_makho, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_mavattu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_soluongnhap, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_soluongton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_ngaynhap, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(60, 60, 60)
                        .addGroup(pnKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnKhoLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(btn_xoaKho, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnKhoLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(pnKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_timkho, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_TimKho, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );

        pnMain.add(pnKho, "card3");

        pnPhieuxuat.setLayout(new java.awt.CardLayout());

        pnPhieuXuat2.setBackground(new java.awt.Color(204, 204, 204));
        pnPhieuXuat2.setPreferredSize(new java.awt.Dimension(960, 600));

        lbl_xuatchitiet.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lbl_xuatchitiet.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_xuatchitiet.setText("Phiếu Xuất Chi Tiết");
        lbl_xuatchitiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_xuatchitietMouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Mã Phòng Ban :");

        txt_MaPhongBan.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel28.setText("Mã Phiếu Xuất :");

        txt_MaPhieuXuat.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel29.setText("Tìm Kiếm:");

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
        jScrollPane3.setViewportView(tbl_PhieuXuat);

        txt_TimKiem.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_Tim.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_Tim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon_restart.png"))); // NOI18N
        btn_Tim.setText("Tìm");
        btn_Tim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimActionPerformed(evt);
            }
        });

        btn_Them2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_Them2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon_add.png"))); // NOI18N
        btn_Them2.setText("Thêm");
        btn_Them2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Them2ActionPerformed(evt);
            }
        });

        btn_Sua2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_Sua2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon_fix.png"))); // NOI18N
        btn_Sua2.setText("Sửa");
        btn_Sua2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Sua2ActionPerformed(evt);
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

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel30.setText("Ngày Xuất :");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Mã Kho :");

        jLabel14.setFont(new java.awt.Font("Source Sans Pro Black", 0, 48)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Phiếu Xuất");

        txt_MaKho.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton6.setText("Làm Mới");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnPhieuXuat2Layout = new javax.swing.GroupLayout(pnPhieuXuat2);
        pnPhieuXuat2.setLayout(pnPhieuXuat2Layout);
        pnPhieuXuat2Layout.setHorizontalGroup(
            pnPhieuXuat2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPhieuXuat2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnPhieuXuat2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnPhieuXuat2Layout.createSequentialGroup()
                        .addGroup(pnPhieuXuat2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnPhieuXuat2Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(pnPhieuXuat2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnPhieuXuat2Layout.createSequentialGroup()
                                        .addComponent(btn_Them2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(45, 45, 45)
                                        .addComponent(btn_Sua2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(45, 45, 45)
                                        .addComponent(btn_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnPhieuXuat2Layout.createSequentialGroup()
                                        .addGroup(pnPhieuXuat2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(pnPhieuXuat2Layout.createSequentialGroup()
                                                .addGroup(pnPhieuXuat2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                                                .addGap(10, 10, 10))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnPhieuXuat2Layout.createSequentialGroup()
                                                .addGap(27, 27, 27)
                                                .addComponent(jLabel30)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                        .addGroup(pnPhieuXuat2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnPhieuXuat2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txt_MaPhongBan, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(txt_MaKho, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(txt_MaPhieuXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txt_NgayXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18)
                                .addGroup(pnPhieuXuat2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnPhieuXuat2Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txt_TimKiem)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_Tim, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)))
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnPhieuXuat2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnPhieuXuat2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnPhieuXuat2Layout.createSequentialGroup()
                                .addComponent(lbl_xuatchitiet, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnPhieuXuat2Layout.createSequentialGroup()
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
        );
        pnPhieuXuat2Layout.setVerticalGroup(
            pnPhieuXuat2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPhieuXuat2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addGap(29, 29, 29)
                .addGroup(pnPhieuXuat2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Tim, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(pnPhieuXuat2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnPhieuXuat2Layout.createSequentialGroup()
                        .addGroup(pnPhieuXuat2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnPhieuXuat2Layout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnPhieuXuat2Layout.createSequentialGroup()
                                .addComponent(txt_MaPhieuXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_MaPhongBan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_MaKho, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_NgayXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(94, 94, 94)
                        .addGroup(pnPhieuXuat2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnPhieuXuat2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btn_Them2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_Sua2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnPhieuXuat2Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(btn_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(lbl_xuatchitiet, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        javax.swing.GroupLayout pnPhieuXuat1Layout = new javax.swing.GroupLayout(pnPhieuXuat1);
        pnPhieuXuat1.setLayout(pnPhieuXuat1Layout);
        pnPhieuXuat1Layout.setHorizontalGroup(
            pnPhieuXuat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 972, Short.MAX_VALUE)
            .addGroup(pnPhieuXuat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnPhieuXuat2, javax.swing.GroupLayout.DEFAULT_SIZE, 972, Short.MAX_VALUE))
        );
        pnPhieuXuat1Layout.setVerticalGroup(
            pnPhieuXuat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 626, Short.MAX_VALUE)
            .addGroup(pnPhieuXuat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnPhieuXuat2, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE))
        );

        pnPhieuxuat.add(pnPhieuXuat1, "card2");

        pnMain.add(pnPhieuxuat, "card5");

        pnPhieunhap.setLayout(new java.awt.CardLayout());

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));

        lbl_nhapchitiet1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lbl_nhapchitiet1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_nhapchitiet1.setText("Phiếu Nhập Chi Tiết");
        lbl_nhapchitiet1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_nhapchitiet1MouseClicked(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Mã Phiếu Nhập :");

        txt_MaPhieuNhap.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel31.setText("Tìm Kiếm:");

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
        jScrollPane4.setViewportView(tbl_phieuNhap);

        txt_timkiemPN.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_timkiemPN.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_timkiemPN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon_restart.png"))); // NOI18N
        btn_timkiemPN.setText("Tìm");
        btn_timkiemPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timkiemPNActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Source Sans Pro Black", 0, 48)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("Phiếu Nhập");

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

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel33.setText("Mã Nhà Cung Cấp :");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel34.setText("Ngày Nhập :");

        txt_NgayNhapPN.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton4.setText("Làm Mới");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_ThemNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(btn_SuaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(btn_XoaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_MaPhieuNhap)
                            .addComponent(txt_NgayNhapPN)
                            .addComponent(txt_NhaCungCapPN, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_timkiemPN)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_timkiemPN, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)))
            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(lbl_nhapchitiet1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel32)
                .addGap(29, 29, 29)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_timkiemPN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_timkiemPN, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_MaPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txt_NgayNhapPN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_NhaCungCapPN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(100, 100, 100)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btn_ThemNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_SuaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(btn_XoaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 40, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbl_nhapchitiet1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout pnPhieuNhap1Layout = new javax.swing.GroupLayout(pnPhieuNhap1);
        pnPhieuNhap1.setLayout(pnPhieuNhap1Layout);
        pnPhieuNhap1Layout.setHorizontalGroup(
            pnPhieuNhap1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnPhieuNhap1Layout.setVerticalGroup(
            pnPhieuNhap1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnPhieunhap.add(pnPhieuNhap1, "card2");

        pnMain.add(pnPhieunhap, "card6");

        pnPhongban.setBackground(new java.awt.Color(204, 204, 204));

        jLabel18.setFont(new java.awt.Font("Source Sans Pro Black", 0, 48)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Phòng Ban");

        txt_diachi.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_sua1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_sua1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon_fix.png"))); // NOI18N
        btn_sua1.setText("Sửa");
        btn_sua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sua1ActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Email :");

        tbl_Phongban.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_Phongban.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Phòng Ban", "Tên Phòng Ban", "Địa Chỉ", "SDT", "Email"
            }
        ));
        tbl_Phongban.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_PhongbanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_Phongban);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("Tên Phòng Ban :");

        txt_email.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_xoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon_delete.png"))); // NOI18N
        btn_xoa.setText("Xóa");
        btn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaActionPerformed(evt);
            }
        });

        txt_Tenpb.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_sdt.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("Mã Phòng Ban :");

        txt_timpb.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_maPb.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("Số Điện Thoại :");

        btn_them1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_them1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon_add.png"))); // NOI18N
        btn_them1.setText("Thêm");
        btn_them1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_them1ActionPerformed(evt);
            }
        });

        btn_timpb.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_timpb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon_restart.png"))); // NOI18N
        btn_timpb.setText("Tìm");
        btn_timpb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timpbActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setText("Tìm Kiếm:");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("Địa Chỉ :");

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton3.setText("Làm Mới");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnPhongbanLayout = new javax.swing.GroupLayout(pnPhongban);
        pnPhongban.setLayout(pnPhongbanLayout);
        pnPhongbanLayout.setHorizontalGroup(
            pnPhongbanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPhongbanLayout.createSequentialGroup()
                .addGroup(pnPhongbanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnPhongbanLayout.createSequentialGroup()
                        .addGroup(pnPhongbanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(pnPhongbanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnPhongbanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_Tenpb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                                .addComponent(txt_diachi, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txt_maPb))
                            .addComponent(txt_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnPhongbanLayout.createSequentialGroup()
                        .addComponent(btn_them1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(btn_sua1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(btn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(pnPhongbanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnPhongbanLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_timpb)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_timpb, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)))
            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnPhongbanLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnPhongbanLayout.setVerticalGroup(
            pnPhongbanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPhongbanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addGroup(pnPhongbanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnPhongbanLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(pnPhongbanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnPhongbanLayout.createSequentialGroup()
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnPhongbanLayout.createSequentialGroup()
                                .addComponent(txt_maPb, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_Tenpb, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_diachi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(55, 55, 55)
                        .addGroup(pnPhongbanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnPhongbanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btn_them1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_sua1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnPhongbanLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(btn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnPhongbanLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(pnPhongbanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_timpb, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_timpb, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );

        pnMain.add(pnPhongban, "card9");

        javax.swing.GroupLayout pnThongtinLayout = new javax.swing.GroupLayout(pnThongtin);
        pnThongtin.setLayout(pnThongtinLayout);
        pnThongtinLayout.setHorizontalGroup(
            pnThongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 960, Short.MAX_VALUE)
        );
        pnThongtinLayout.setVerticalGroup(
            pnThongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 626, Short.MAX_VALUE)
        );

        pnMain.add(pnThongtin, "card10");

        javax.swing.GroupLayout pnQuanLyVatTuLayout = new javax.swing.GroupLayout(pnQuanLyVatTu);
        pnQuanLyVatTu.setLayout(pnQuanLyVatTuLayout);
        pnQuanLyVatTuLayout.setHorizontalGroup(
            pnQuanLyVatTuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnQuanLyVatTuLayout.createSequentialGroup()
                .addComponent(pnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnQuanLyVatTuLayout.setVerticalGroup(
            pnQuanLyVatTuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnQuanLyVatTu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnQuanLyVatTu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_dangxuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_dangxuatMouseClicked
        Login dnt = new Login();
        dnt.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lbl_dangxuatMouseClicked

    private void lbl_VattuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_VattuMouseClicked
        // TODO add your handling code here:
        pnVattu.setVisible(true);
        pnKho.setVisible(false);
        pnNhaCP.setVisible(false);
        pnPhieunhap.setVisible(false);
        pnPhieuxuat.setVisible(false);
        pnPhongban.setVisible(false);
        
        pnTrangChu.setVisible(false);
    }//GEN-LAST:event_lbl_VattuMouseClicked

    private void lbl_KhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_KhoMouseClicked
        // TODO add your handling code here:
        pnVattu.setVisible(false);
        pnKho.setVisible(true);
        pnNhaCP.setVisible(false);
        pnPhieunhap.setVisible(false);
        pnPhieuxuat.setVisible(false);
        pnPhongban.setVisible(false);
        pnThongtin.setVisible(false);
        pnTrangChu.setVisible(false);
    }//GEN-LAST:event_lbl_KhoMouseClicked

    private void lbl_nhacungcapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_nhacungcapMouseClicked
        // TODO add your handling code here:
        pnVattu.setVisible(false);
        pnKho.setVisible(false);
        pnNhaCP.setVisible(true);
        pnPhieunhap.setVisible(false);
        pnPhieuxuat.setVisible(false);
        pnPhongban.setVisible(false);
        pnThongtin.setVisible(false);
        pnTrangChu.setVisible(false);
    }//GEN-LAST:event_lbl_nhacungcapMouseClicked

    private void lbl_phieuxuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_phieuxuatMouseClicked
        // TODO add your handling code here:
        pnVattu.setVisible(false);
        pnKho.setVisible(false);
        pnNhaCP.setVisible(false);
        pnPhieunhap.setVisible(false);
        pnPhieuxuat.setVisible(true);
        pnPhongban.setVisible(false);
        pnThongtin.setVisible(false);
        pnTrangChu.setVisible(false);
    }//GEN-LAST:event_lbl_phieuxuatMouseClicked

    private void lbl_phieunhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_phieunhapMouseClicked
        // TODO add your handling code here:
        pnVattu.setVisible(false);
        pnKho.setVisible(false);
        pnNhaCP.setVisible(false);
        pnPhieunhap.setVisible(true);
        pnPhieuxuat.setVisible(false);
        pnPhongban.setVisible(false);
        pnThongtin.setVisible(false);
        pnTrangChu.setVisible(false);
    }//GEN-LAST:event_lbl_phieunhapMouseClicked

    private void lbl_PhongbanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_PhongbanMouseClicked
        // TODO add your handling code here:
        pnVattu.setVisible(false);
        pnKho.setVisible(false);
        pnNhaCP.setVisible(false);
        pnPhieunhap.setVisible(false);
        pnPhieuxuat.setVisible(false);
        pnPhongban.setVisible(true);
        pnThongtin.setVisible(false);
        pnTrangChu.setVisible(false);
    }//GEN-LAST:event_lbl_PhongbanMouseClicked

    private void lbl_ThongbaottinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_ThongbaottinMouseClicked
        // TODO add your handling code here:
        pnVattu.setVisible(false);
        pnKho.setVisible(false);
        pnNhaCP.setVisible(false);
        pnPhieunhap.setVisible(false);
        pnPhieuxuat.setVisible(false);
        pnPhongban.setVisible(false);
        pnThongtin.setVisible(true);
        pnTrangChu.setVisible(false);
        SendMail sm = new SendMail();
        sm.setVisible(true);

    }//GEN-LAST:event_lbl_ThongbaottinMouseClicked

    private void lbl_TrangChuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_TrangChuMouseClicked
        pnVattu.setVisible(false);
        pnKho.setVisible(false);
        pnNhaCP.setVisible(false);
        pnPhieunhap.setVisible(false);
        pnPhieuxuat.setVisible(false);
        pnPhongban.setVisible(false);
        pnThongtin.setVisible(false);
        pnTrangChu.setVisible(true);
    }//GEN-LAST:event_lbl_TrangChuMouseClicked

    private void lbl_nhapchitiet1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_nhapchitiet1MouseClicked
        FormPhieuNhapCT nct = new FormPhieuNhapCT();
        nct.setVisible(true);
    }//GEN-LAST:event_lbl_nhapchitiet1MouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        this.dispose();
    }//GEN-LAST:event_formWindowClosed

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        addVatTu();
    }//GEN-LAST:event_btn_ThemActionPerformed

    private void btn_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaActionPerformed
        updateVatTu();
    }//GEN-LAST:event_btn_SuaActionPerformed

    private void btn_XOAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XOAActionPerformed
        deleteVatTu();
    }//GEN-LAST:event_btn_XOAActionPerformed

    private void btn_TImActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TImActionPerformed
        String maVatTu = txt_HienTimKiem.getText().trim();
        if (maVatTu.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã vật tư để tìm kiếm!");
            return;
        }

        VatTu vt = DAO_VATTU.searchVatTuByMa(maVatTu);
        if (vt == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy vật tư với mã: " + maVatTu);
            // Xóa sạch các trường nhập liệu và bảng nếu không tìm thấy
            txt_Mavt.setText("");
            txt_TenVT.setText("");
            txt_Donvitinh.setText("");
            txt_Dongia.setText("");
            tableModelVatTu.setRowCount(0); // Xóa sạch dữ liệu trong bảng
        } else {
            // Hiển thị thông tin lên các ô nhập liệu
            txt_Mavt.setText(vt.getMavattu());
            txt_TenVT.setText(vt.getTenVattu());
            txt_Donvitinh.setText(vt.getDonvitinh());
            txt_Dongia.setText(String.valueOf(vt.getDongia()));

            // Cập nhật bảng chỉ với kết quả tìm kiếm
            tableModelVatTu.setRowCount(0); // Xóa dữ liệu cũ trong bảng
            tableModelVatTu.addRow(new Object[]{vt.getMavattu(), vt.getTenVattu(), vt.getDonvitinh(), vt.getDongia()});
        }
    }//GEN-LAST:event_btn_TImActionPerformed

    private void tbl_VatTuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_VatTuMouseClicked
        clickHereVT();
    }//GEN-LAST:event_tbl_VatTuMouseClicked

    private void btn_TimKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKhoActionPerformed
        String maKho = txt_timkho.getText().trim(); // Lấy mã kho từ trường nhập liệu
        if (maKho.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã kho để tìm kiếm!");
            return;
        }

        Kho kho = DAO_KHO.searchKhoByMa(maKho); // Gọi phương thức tìm kiếm trong DAO
        if (kho == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy kho với mã: " + maKho);
            // Xóa sạch các trường nhập liệu và bảng nếu không tìm thấy
            txt_makho.setText("");
            txt_mavattu.setText("");
            txt_soluongnhap.setText("");
            txt_soluongton.setText("");
            txt_ngaynhap.setText("");
            tableModelKho.setRowCount(0); // Xóa sạch bảng
        } else {
            // Hiển thị thông tin lên các trường nhập liệu
            txt_makho.setText(kho.getMaKho());
            txt_mavattu.setText(kho.getMaVatTu());
            txt_soluongnhap.setText(String.valueOf(kho.getSoLuongNhap()));
            txt_soluongton.setText(String.valueOf(kho.getSoLuongTon()));
            txt_ngaynhap.setText(kho.getNgayNhap().toString());

            // Cập nhật bảng chỉ với kết quả tìm kiếm
            tableModelKho.setRowCount(0); // Xóa dữ liệu cũ trong bảng
            tableModelKho.addRow(new Object[]{
                kho.getMaKho(),
                kho.getMaVatTu(),
                kho.getSoLuongNhap(),
                kho.getSoLuongTon(),
                kho.getNgayNhap()
            });
        }
    }//GEN-LAST:event_btn_TimKhoActionPerformed

    private void btn_xoaKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaKhoActionPerformed
        // TODO add your handling code here:
        deletekho();
    }//GEN-LAST:event_btn_xoaKhoActionPerformed

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
        // TODO add your handling code here:
        updateKho();
    }//GEN-LAST:event_btn_suaActionPerformed

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        // TODO add your handling code here:
        addKho();
    }//GEN-LAST:event_btn_themActionPerformed

    private void tbl_KhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_KhoMouseClicked
        // TODO add your handling code here:
        clickHereKho();
    }//GEN-LAST:event_tbl_KhoMouseClicked

    private void btn_sua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sua1ActionPerformed
        // TODO add your handling code here:
        updatePhog();
    }//GEN-LAST:event_btn_sua1ActionPerformed

    private void tbl_PhongbanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_PhongbanMouseClicked
        // TODO add your handling code here:
        clickHerePB();
    }//GEN-LAST:event_tbl_PhongbanMouseClicked

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed
        // TODO add your handling code here:
        deletePhog();
    }//GEN-LAST:event_btn_xoaActionPerformed

    private void btn_them1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_them1ActionPerformed
        // TODO add your handling code here:
        addPhog();
    }//GEN-LAST:event_btn_them1ActionPerformed

    private void btn_timpbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timpbActionPerformed
        String maPhongBan = txt_timpb.getText().trim();
        if (maPhongBan.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã phòng ban để tìm kiếm!");
            return;
        }
        PhongBan pb = DAO_PHONGBAN.searchPhongBanByMa(maPhongBan);
        if (pb == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy phòng ban với mã: " + maPhongBan);
            // Xóa sạch các trường nhập liệu và bảng nếu không tìm thấy
            txt_maPb.setText("");
            txt_Tenpb.setText("");
            txt_diachi.setText("");
            txt_sdt.setText("");
            txt_email.setText("");
            tableModelPhongBan.setRowCount(0); // Xóa sạch bảng
        } else {
            // Hiển thị thông tin lên các trường nhập liệu
            txt_maPb.setText(pb.getMaPB());
            txt_Tenpb.setText(pb.getTenPB());
            txt_diachi.setText(pb.getDiachi());
            txt_sdt.setText(pb.getSoDienThoai());
            txt_email.setText(pb.getEmail());
            // Cập nhật bảng chỉ với kết quả tìm kiếm
            tableModelPhongBan.setRowCount(0); // Xóa dữ liệu cũ trong bảng
            tableModelPhongBan.addRow(new Object[]{pb.getMaPB(), pb.getTenPB(), pb.getDiachi(), pb.getSoDienThoai(), pb.getEmail()});
        }
    }//GEN-LAST:event_btn_timpbActionPerformed

    private void lbl_xuatchitietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_xuatchitietMouseClicked
        FormPhieuXuatCT nct = new FormPhieuXuatCT();
        nct.setVisible(true);
    }//GEN-LAST:event_lbl_xuatchitietMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        fillToTableVT();
        txt_Mavt.setText("");
        txt_HienTimKiem.setText("");
        txt_TenVT.setText("");
        txt_Dongia.setText("");
        txt_Donvitinh.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        fillToTableKho();
        txt_makho.setText("");
        txt_mavattu.setText("");
        txt_soluongnhap.setText("");
        txt_soluongton.setText("");
        txt_timkho.setText("");
        txt_ngaynhap.setText("");

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        fillToTablePB();
        txt_maPb.setText("");
        txt_timpb.setText("");
        txt_diachi.setText("");
        txt_email.setText("");
        txt_Tenpb.setText("");
        txt_sdt.setText("");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tbl_PhieuXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_PhieuXuatMouseClicked
        clickHerePX();
    }//GEN-LAST:event_tbl_PhieuXuatMouseClicked

    private void btn_TimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimActionPerformed
        timPhieuXuat();
    }//GEN-LAST:event_btn_TimActionPerformed

    private void btn_Them2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Them2ActionPerformed
        addPhieuXuat();
    }//GEN-LAST:event_btn_Them2ActionPerformed

    private void btn_Sua2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Sua2ActionPerformed
        updatePhieuXuat();
    }//GEN-LAST:event_btn_Sua2ActionPerformed

    private void btn_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaActionPerformed
        deletePhieuXuat();
    }//GEN-LAST:event_btn_XoaActionPerformed

    private void tbl_phieuNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_phieuNhapMouseClicked
        // TODO add your handling code here:
        clickHerePN();
    }//GEN-LAST:event_tbl_phieuNhapMouseClicked

    private void btn_timkiemPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timkiemPNActionPerformed
        // TODO add your handling code here:
        timPhieuNhap();
    }//GEN-LAST:event_btn_timkiemPNActionPerformed

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

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        fillToTablePN();
        txt_MaPhieuNhap.setText("");
        txt_timkiemPN.setText("");
        txt_NgayNhapPN.setText("");
        txt_NhaCungCapPN.setText("");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tbl_NhaCungCapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_NhaCungCapMouseClicked
        // TODO add your handling code here:
        clickHereNCC();
    }//GEN-LAST:event_tbl_NhaCungCapMouseClicked

    private void btn_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AddActionPerformed
        // TODO add your handling code here:
        addNhaCungCap();
    }//GEN-LAST:event_btn_AddActionPerformed

    private void btn_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpdateActionPerformed
        // TODO add your handling code here:
        updateNhaCungCap();
    }//GEN-LAST:event_btn_UpdateActionPerformed

    private void btn_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DeleteActionPerformed
        // TODO add your handling code here:
        deleteNhaCungCap();
    }//GEN-LAST:event_btn_DeleteActionPerformed

    private void btn_Tim1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Tim1ActionPerformed
        // TODO add your handling code here:
        timNhaCungCap();
    }//GEN-LAST:event_btn_Tim1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        fillToTableNCC();
        txt_TimKiem1.setText("");
        txt_MaNhaCungCap.setText("");
        txt_TenNhaCungCap.setText("");
        txt_SoDienThoai.setText("");
        txt_Email.setText("");
        txt_DiaChi.setText("");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        fillToTablePX();
        txt_MaPhieuXuat.setText("");
        txt_MaPhongBan.setText("");
        txt_MaKho.setText("");
        txt_NgayXuat.setText("");
        txt_TimKiem.setText("");
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(MenuPart2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPart2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPart2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPart2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPart2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Add;
    private javax.swing.JButton btn_Delete;
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_Sua2;
    private javax.swing.JButton btn_SuaNhap;
    private javax.swing.JButton btn_TIm;
    private javax.swing.JButton btn_Them;
    private javax.swing.JButton btn_Them2;
    private javax.swing.JButton btn_ThemNhap;
    private javax.swing.JButton btn_Tim;
    private javax.swing.JButton btn_Tim1;
    private javax.swing.JButton btn_TimKho;
    private javax.swing.JButton btn_Update;
    private javax.swing.JButton btn_XOA;
    private javax.swing.JButton btn_Xoa;
    private javax.swing.JButton btn_XoaNhap;
    private javax.swing.JButton btn_sua;
    private javax.swing.JButton btn_sua1;
    private javax.swing.JButton btn_them;
    private javax.swing.JButton btn_them1;
    private javax.swing.JButton btn_timkiemPN;
    private javax.swing.JButton btn_timpb;
    private javax.swing.JButton btn_xoa;
    private javax.swing.JButton btn_xoaKho;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lbl_Kho;
    private javax.swing.JLabel lbl_Phongban;
    private javax.swing.JLabel lbl_Thongbaottin;
    private javax.swing.JLabel lbl_TrangChu;
    private javax.swing.JLabel lbl_Vattu;
    private javax.swing.JLabel lbl_dangxuat;
    private javax.swing.JLabel lbl_nhacungcap;
    private javax.swing.JLabel lbl_nhapchitiet1;
    private javax.swing.JLabel lbl_phieunhap;
    private javax.swing.JLabel lbl_phieuxuat;
    private javax.swing.JLabel lbl_xuatchitiet;
    private javax.swing.JPanel pnKho;
    private javax.swing.JPanel pnMain;
    private javax.swing.JPanel pnMenu;
    private javax.swing.JPanel pnNhaCP;
    private javax.swing.JPanel pnPhieuNhap1;
    private javax.swing.JPanel pnPhieuXuat1;
    private javax.swing.JPanel pnPhieuXuat2;
    private javax.swing.JPanel pnPhieunhap;
    private javax.swing.JPanel pnPhieuxuat;
    private javax.swing.JPanel pnPhongban;
    private javax.swing.JPanel pnQuanLyVatTu;
    private javax.swing.JPanel pnThongtin;
    private javax.swing.JPanel pnTrangChu;
    private javax.swing.JPanel pnVattu;
    private javax.swing.JTable tbl_Kho;
    private javax.swing.JTable tbl_NhaCungCap;
    private javax.swing.JTable tbl_PhieuXuat;
    private javax.swing.JTable tbl_Phongban;
    private javax.swing.JTable tbl_VatTu;
    private javax.swing.JTable tbl_phieuNhap;
    private javax.swing.JTextField txt_DiaChi;
    private javax.swing.JTextField txt_Dongia;
    private javax.swing.JTextField txt_Donvitinh;
    private javax.swing.JTextField txt_Email;
    private javax.swing.JTextField txt_HienTimKiem;
    private javax.swing.JTextField txt_MaKho;
    private javax.swing.JTextField txt_MaNhaCungCap;
    private javax.swing.JTextField txt_MaPhieuNhap;
    private javax.swing.JTextField txt_MaPhieuXuat;
    private javax.swing.JTextField txt_MaPhongBan;
    private javax.swing.JTextField txt_Mavt;
    private javax.swing.JTextField txt_NgayNhapPN;
    private javax.swing.JTextField txt_NgayXuat;
    private javax.swing.JTextField txt_NhaCungCapPN;
    private javax.swing.JTextField txt_SoDienThoai;
    private javax.swing.JTextField txt_TenNhaCungCap;
    private javax.swing.JTextField txt_TenVT;
    private javax.swing.JTextField txt_Tenpb;
    private javax.swing.JTextField txt_TimKiem;
    private javax.swing.JTextField txt_TimKiem1;
    private javax.swing.JTextField txt_diachi;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_maPb;
    private javax.swing.JTextField txt_makho;
    private javax.swing.JTextField txt_mavattu;
    private javax.swing.JTextField txt_ngaynhap;
    private javax.swing.JTextField txt_sdt;
    private javax.swing.JTextField txt_soluongnhap;
    private javax.swing.JTextField txt_soluongton;
    private javax.swing.JTextField txt_timkho;
    private javax.swing.JTextField txt_timkiemPN;
    private javax.swing.JTextField txt_timpb;
    // End of variables declaration//GEN-END:variables
}
