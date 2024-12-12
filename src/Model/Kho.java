package Model;

import java.sql.Date;

public class Kho {

    private String maKho;
    private String maVatTu;
    private int soLuongNhap;
    private int soLuongTon;
    private java.sql.Date ngayNhap; // Sử dụng java.sql.Date

    public Kho(String maKho, String maVatTu, int soLuongNhap, int soLuongTon, Date ngayNhap) {
        this.maKho = maKho;
        this.maVatTu = maVatTu;
        this.soLuongNhap = soLuongNhap;
        this.soLuongTon = soLuongTon;
        this.ngayNhap = ngayNhap;
    }

    public Kho() {
    }

    public String getMaKho() {
        return maKho;
    }

    public void setMaKho(String maKho) {
        this.maKho = maKho;
    }

    public String getMaVatTu() {
        return maVatTu;
    }

    public void setMaVatTu(String maVatTu) {
        this.maVatTu = maVatTu;
    }

    public int getSoLuongNhap() {
        return soLuongNhap;
    }

    public void setSoLuongNhap(int soLuongNhap) {
        this.soLuongNhap = soLuongNhap;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

   
}
