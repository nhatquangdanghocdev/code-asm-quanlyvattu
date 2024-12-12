/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author asus
 */
public class PhieuNhap {
   String MaPhieuNhapKho ;
   String NgayNhap ;
   String MaNhaCungCap ;

    public PhieuNhap() {
    }

    public PhieuNhap(String MaPhieuNhapKho, String NgayNhap, String MaNhaCungCap) {
        this.MaPhieuNhapKho = MaPhieuNhapKho;
        this.NgayNhap = NgayNhap;
        this.MaNhaCungCap = MaNhaCungCap;
    }

    public String getMaPhieuNhapKho() {
        return MaPhieuNhapKho;
    }

    public void setMaPhieuNhapKho(String MaPhieuNhapKho) {
        this.MaPhieuNhapKho = MaPhieuNhapKho;
    }

    public String getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(String NgayNhap) {
        this.NgayNhap = NgayNhap;
    }

    public String getMaNhaCungCap() {
        return MaNhaCungCap;
    }

    public void setMaNhaCungCap(String MaNhaCungCap) {
        this.MaNhaCungCap = MaNhaCungCap;
    }
   
    
}
