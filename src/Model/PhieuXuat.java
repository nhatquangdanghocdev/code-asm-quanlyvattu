
package Model;

import java.sql.Date;

public class PhieuXuat {
    String maPhieuXuat;
    String maPhongBan;
    String maKho;
    String ngayXuat;


    public PhieuXuat() {
    }

    public PhieuXuat(String maPhieuXuat, String maPhongBan, String maKho, String ngayXuat) {
        this.maPhieuXuat = maPhieuXuat;
        this.maPhongBan = maPhongBan;
        this.maKho = maKho;
        this.ngayXuat = ngayXuat;
    }

    public String getMaPhieuXuat() {
        return maPhieuXuat;
    }

    public void setMaPhieuXuat(String maPhieuXuat) {
        this.maPhieuXuat = maPhieuXuat;
    }

    public String getMaPhongBan() {
        return maPhongBan;
    }

    public void setMaPhongBan(String maPhongBan) {
        this.maPhongBan = maPhongBan;
    }

    public String getMaKho() {
        return maKho;
    }

    public void setMaKho(String maKho) {
        this.maKho = maKho;
    }

    public String getNgayXuat() {
        return ngayXuat;
    }

    public void setNgayXuat(String ngayXuat) {
        this.ngayXuat = ngayXuat;
    }

    

    

    

    
    
}
