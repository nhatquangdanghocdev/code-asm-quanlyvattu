package Model;

public class ChiTietPhieuNhapKho {

    String MaChiTietPhieuNhapKho;
    String MaPhieuNhap;
    String MaVatTu;
    int SoLuong;

    public ChiTietPhieuNhapKho(String MaChiTietPhieuNhapKho, String MaPhieuNhap, String MaVatTu, int SoLuong) {
        this.MaChiTietPhieuNhapKho = MaChiTietPhieuNhapKho;
        this.MaPhieuNhap = MaPhieuNhap;
        this.MaVatTu = MaVatTu;
        this.SoLuong = SoLuong;
    }

    public String getMaChiTietPhieuNhapKho() {
        return MaChiTietPhieuNhapKho;
    }

    public void setMaChiTietPhieuNhapKho(String MaChiTietPhieuNhapKho) {
        this.MaChiTietPhieuNhapKho = MaChiTietPhieuNhapKho;
    }

    public String getMaPhieuNhap() {
        return MaPhieuNhap;
    }

    public void setMaPhieuNhap(String MaPhieuNhap) {
        this.MaPhieuNhap = MaPhieuNhap;
    }

    public String getMaVatTu() {
        return MaVatTu;
    }

    public void setMaVatTu(String MaVatTu) {
        this.MaVatTu = MaVatTu;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

}
