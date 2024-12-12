/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author doann
 */
public class PhieuXuatChiTiet {
    
    String maPhieuXuatChiTiet;
    String maPhieuXuat;
    String maVatTu;
    int soLuong;

    public PhieuXuatChiTiet() {
    }

    public PhieuXuatChiTiet(String maPhieuXuatChiTiet, String maPhieuXuat, String maVatTu, int soLuong) {
        this.maPhieuXuatChiTiet = maPhieuXuatChiTiet;
        this.maPhieuXuat = maPhieuXuat;
        this.maVatTu = maVatTu;
        this.soLuong = soLuong;
    }

    public String getMaPhieuXuatChiTiet() {
        return maPhieuXuatChiTiet;
    }

    public void setMaPhieuXuatChiTiet(String maPhieuXuatChiTiet) {
        this.maPhieuXuatChiTiet = maPhieuXuatChiTiet;
    }

    public String getMaPhieuXuat() {
        return maPhieuXuat;
    }

    public void setMaPhieuXuat(String maPhieuXuat) {
        this.maPhieuXuat = maPhieuXuat;
    }

    public String getMaVatTu() {
        return maVatTu;
    }

    public void setMaVatTu(String maVatTu) {
        this.maVatTu = maVatTu;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    

    

    

    
}
